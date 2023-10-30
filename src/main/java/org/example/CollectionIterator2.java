package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * This realization of CollectionIterator takes a little bit more memory to work (which is still O(n * m))
 * in exchange for lesser time cost for initialization O(n) and a little bit more time for every next() operation
 * (which is still O(n)).
 */
public class CollectionIterator2<E> implements Iterator<Collection<E>> {
    /*
     * Stores n.
     */
    private final int mDimensions;

    /*
     * Stores all Collections data.
     */
    private final Collection<E>[] mCollections;

    /*
     * Stores current sates of iterators.
     */
    private final Iterator<E>[] mIterators;

    /*
     * Stores current element value.
     */
    private final ArrayList<E> mCurrentData = new ArrayList<>();

    CollectionIterator2(int n, Collection<E>[] collections) throws EmptyCollectionException {
        mDimensions = n;
        mCollections = new Collection[n];
        mIterators = new Iterator[n];
        int i = 0;
        for (Collection<E> collection : collections) {
            if (collection.isEmpty()) {
                throw new EmptyCollectionException(collection);
            }
            mCollections[i] = collection;
            mIterators[i] = collection.iterator();
            i++;
        }
    }

    @Override
    public boolean hasNext() {
        for (Iterator<E> iterator : mIterators) {
            if (iterator.hasNext()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ArrayList<E> next() {
        if (mCurrentData.isEmpty()) {
            // mCurrentData was not set to the first value.
            for (Iterator<E> iterator : mIterators) {
                mCurrentData.add(iterator.next());
            }
        } else {
            int iteratorToIterate = mDimensions - 1;
            // Searching for the first iterator from the end that has the ability to go next().
            while (iteratorToIterate > -1 && !mIterators[iteratorToIterate].hasNext()) {
                iteratorToIterate--;
            }
            System.out.println(iteratorToIterate);
            if (iteratorToIterate > -1) {
                // Iterating on the found iterator and resetting all the following ones.
                mCurrentData.set(iteratorToIterate, mIterators[iteratorToIterate].next());
                for (int i = iteratorToIterate + 1; i < mDimensions; i++) {
                    mIterators[i] = mCollections[i].iterator();
                    mCurrentData.set(i, mIterators[i].next());
                }
            } else {
                // All iterators were fully iterated through.
                throw new NoSuchElementException();
            }
        }
        return mCurrentData;
    }

}
