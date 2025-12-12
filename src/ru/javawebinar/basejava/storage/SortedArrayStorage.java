package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {
    @Override
    protected int getIndex(String uuid) {
        Resume newResume = new Resume();
        newResume.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, newResume);
    }

    @Override
    public void saveResume(Resume r, int index) {
        storage[-1*(index+1)] = r;
    }

    @Override
    public void update(Resume r) {
    }

    @Override
    public void delete(String uuid) {
    }

}
