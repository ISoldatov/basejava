package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes.
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected final Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index >= 0) {
            System.out.println("ОШИБКА! Резюме с uuid=" + r.getUuid() + " уже присутсвует в хранилище.");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("ОШИБКА! Хранилище переполнено.");
        } else {
            saveResume(r, index);
            size++;
        }
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ОШИБКА! Резюме с uuid=" + uuid + " отсутсвует в хранилище.");
        } else {
            System.arraycopy(storage, index + 1, storage, index, size - (index + 1));
            storage[size - 1] = null;
            size--;
        }
    }

    public int size() {
        return size;
    }

    public void clear() {
        if (size > 0) {
            Arrays.fill(storage, 0, size, null);
            size = 0;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("ОШИБКА! Резюме с uuid=" + uuid + " отсутсвует в хранилище.");
            return null;
        }
        return storage[index];
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract void saveResume(Resume r, int index);

    protected abstract int getIndex(String uuid);
}
