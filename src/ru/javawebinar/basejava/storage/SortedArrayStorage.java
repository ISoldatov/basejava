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
        int insertIndex = -1 * (index + 1);
        System.arraycopy(storage, insertIndex, storage, insertIndex + 1, size - insertIndex);
        storage[insertIndex] = r;
    }
}
