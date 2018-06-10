package org.tomn.concurrent;


/**
 * build immutable object
 * error : the final must be init by construction
 * solution:  1. factory method
 *            2. construction mode
 */

public class ImmutableObj {
  private final String author;
  private final String updateText;

  // set the construction to be private
  private ImmutableObj(Builder builder_) {
    author = builder_.author;
    updateText = builder_.updateText;
  }

  // for test
  public static void main(String[] args) {
    Builder builder = new Builder();
    ImmutableObj obj = builder.setAuthor("tomn").setUpdateText("for test").build();
  }

  // must be the inner static class
  public static class Builder implements ObjBuilder<ImmutableObj> {
    private String author;
    private String updateText;

    // return the Builder in the chain
    public Builder setAuthor(String author_) {
      author = author_;
      return this;
    }

    public Builder setUpdateText(String updateText_) {
      updateText = updateText_;
      return this;
    }

    @Override
    public ImmutableObj build() {
      return new ImmutableObj(this);
    }

  }
}

// construction interface
interface ObjBuilder<T> {
  T build();
}


