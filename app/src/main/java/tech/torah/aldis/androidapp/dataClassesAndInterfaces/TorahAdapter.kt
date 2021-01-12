package tech.torah.aldis.androidapp.dataClassesAndInterfaces
/**
 * This interface indicates that a class is an instance of one of my adapters.
 * I plan on using this interface to make a field for the adapter in the subclass heirarchy of
 * classes like Downloads, Favorites, and Recently Added, so that it can be overriden and
 * they can be subclassed to facilitate DRYness.
 * */
interface TorahAdapter