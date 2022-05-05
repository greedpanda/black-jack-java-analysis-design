package model.rules;

/**
 * To be implemented by any class that wants to accept Visitor objects.
 */
public interface Visitable {

  void accept(Visitor v);

}
