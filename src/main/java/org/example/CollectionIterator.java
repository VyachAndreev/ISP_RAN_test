package org.example;

import java.util.*;

/*
 * In this CollectionIterator realization initialization takes O(n * m) time
 * (where n is dimension count and m is the size of the biggest collection),
 * but every next() is only O(n).
 * Takes O(n * m) memory.
 */
public class CollectionIterator<E> implements Iterator<Collection<E>> {

    private int mSize = 1;

    private int mCurrent = -1;

    private final ArrayList<E>[] mData;

    CollectionIterator(int n, Collection<E>[] collections) throws EmptyCollectionException {
        mData = new ArrayList[n];
        int i = n - 1;
        for (Collection<E> collection : collections) {
            if (collection.isEmpty()) {
                throw new EmptyCollectionException(collection);
            }
            mSize *= collection.size();
            mData[i] = new ArrayList<>();
            for (E element : collection) {
                mData[i].add(element);
            }
            i--;
        }
    }

    @Override
    public boolean hasNext() {
        return mSize > 0 && mCurrent < mSize - 1;
    }

    @Override
    public ArrayList<E> next() {
        mCurrent++;
        if (mCurrent >= mSize) {
            throw new NoSuchElementException();
        }
        ArrayList<E> result = new ArrayList<>();
        int currentIndex = mCurrent;
        for (ArrayList<E> arrayList : mData) {
            result.add(0, arrayList.get(currentIndex % arrayList.size()));
            currentIndex = currentIndex / arrayList.size();
        }
        return result;
    }
}
