package org.tomn.algorithm;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LIS {
  public static void main(String[] args) {
    LIS lis = new LIS();
    List<Record> records = lis.generateRecords();
    List<Indicator> results = lis.getContiousSequence(records, 0.1);
    System.out.println(results);
  }

  // generate the test data
  public List<Record> generateRecords() {
    Random random = new Random(System.currentTimeMillis());
    List<Record> records = new ArrayList<>();
    for (int i = 1; i <= 30; i++) {
      records.add(new Record("" + i, random.nextInt(100) + 1));
    }
    for (int i = 31; i <= 30 + 4 * 60; i++) {
      records.add(new Record("" + i, 50 + random.nextInt(5)));
    }
    for (int i = 271; i <= 270 + 30; i++) {
      records.add(new Record("" + i, random.nextInt(100) + 1));
    }
    for (int i = 301; i <= 300 + 4 * 60; i++) {
      records.add(new Record("" + i, 100 ));
    }
    for (int i = 1; i <= 30; i++) {
      records.add(new Record("" + i, random.nextInt(100) + 1));
    }
    return records;
  }

  // set the sequences to max value
  private void resetRecords(List<Record> records, int index, int len) {
    Random random = new Random(System.currentTimeMillis());
    int number = 10000;
    for (int i = index; i < index + len; i++) {
      records.get(i).setThreadCount(number + random.nextInt(number * 10));
    }
  }

  public  List<Indicator> getContiousSequence(List<Record> records, double threshold) {
    List<Indicator> result = new ArrayList<>();
    if (isValidRecords(records) && records != null && records.size() > 0) {
      List<Record> data = new ArrayList<>(records);
      int seqLen, currentSeqLen;
      do {
        // find max continuous sequences
        int startIndex = 0;
        seqLen = 1;
        currentSeqLen = 1;
        for (int i = 1; i < data.size(); i++) {
          if (isMatchRule(data, i - 1, i, threshold)) {
            currentSeqLen++;
          } else {
            if (currentSeqLen > seqLen) {
              startIndex = i - currentSeqLen;
              seqLen = currentSeqLen;
            }
            currentSeqLen = 1;
          }
        }
        if (seqLen >= minSeqLen()) {
          Indicator indicator = new Indicator(data.get(startIndex).getTime(), data.get(startIndex + seqLen - 1).getTime(), data, seqLen);
          result.add(indicator);
          resetRecords(data, startIndex, seqLen);
        }
      } while (seqLen >= minSeqLen());
    }
    return result;
  }

  private int minSeqLen() {
    // 3 minutes
    return 3 * 60;
  }

  private boolean isValidRecords(List<Record> records) {
    return true;
  }

  private boolean isMatchRule(List<Record> records, int index1, int index2, double threshold) {
    int threadCount1 = records.get(index1).getThreadCount();
    int threadCount2 = records.get(index2).getThreadCount();
    return Math.abs((double) (threadCount2 - threadCount1) / threadCount2) >= threshold ? false : true;
  }
}

// store the record
class Record {
  private static final String TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
  private static final SimpleDateFormat SDF = new SimpleDateFormat(TIME_FORMAT);
  private String time;
  private int threadCount;

  public Record(String time, int threadCount) {
    this.time = time;
    this.threadCount = threadCount;
  }

  public String getTime() {
    return time;
  }

  public String getTimeFormat() {
    return SDF.format(time);
  }

  public int getThreadCount() {
    return threadCount;
  }

  public void setThreadCount(int threadCount) {
    this.threadCount = threadCount;
  }
}


// store the result
class Indicator {
  private String startTime;
  private String endTime;
  // if the threadCount = -1, the result is invalid
  private int threadCount;

  public Indicator(String startTime, String endTime, List<Record> records, int seqLen) {
    this.startTime = startTime;
    this.endTime = endTime;
    this.threadCount = computeThreadCount(records, seqLen);
  }

  // get the number occurs more than half in the array
  private int computeThreadCount(List<Record> records, int seqLen) {
    int number = records.get(0).getThreadCount(), numberTimes = 1;
    for (int i = 1; i < records.size(); i++) {
      if (numberTimes == 0) {
        number = records.get(i).getThreadCount();
        numberTimes = 1;
      }
      if (records.get(i).getThreadCount() == number) {
        numberTimes++;
      } else {
        numberTimes--;
        if (numberTimes == 0) {
          number = records.get(i).getThreadCount();
          numberTimes = 1;
        }
      }
    }
    int count = 0;
    for (int i = 0; i < records.size(); i++) {
      if (number == records.get(i).getThreadCount()) {
        count++;
      }
    }
    return count * 2 >= seqLen ? number : -1;
  }

  public boolean isValid() {
    // TODO : verification the result is valid
    return true;
  }

  @Override
  public String toString() {
    return "Indicator{" +
        "startTime='" + startTime + '\'' +
        ", endTime='" + endTime + '\'' +
        ", threadCount=" + threadCount +
        '}';
  }
}
