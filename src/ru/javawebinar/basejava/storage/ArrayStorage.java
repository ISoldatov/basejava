package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void saveResume(Resume r, int index) {
        storage[size] = r;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("ОШИБКА! Резюме с uuid=" + r.getUuid() + " отсутсвует в хранилище.");
        } else {
            storage[index] = r;
        }
    }

    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
