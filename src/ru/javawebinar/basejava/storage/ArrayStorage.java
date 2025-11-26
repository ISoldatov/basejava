package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;
import java.util.Arrays;

/**
 * Array based storage for Resumes.
 */
public class ArrayStorage extends AbstractArrayStorage {

    public void clear() {
        if (size > 0) {
            Arrays.fill(storage, 0, size, null);
            size = 0;
        }
    }

    public void save(Resume r) {
        if (getIndex(r.getUuid()) >= 0) {
            System.out.println("ОШИБКА! Резюме с uuid=" + r.getUuid() + " уже присутсвует в хранилище.");
        } else if (size == STORAGE_LIMIT) {
            System.out.println("ОШИБКА! Хранилище переполнено.");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("ОШИБКА! Резюме с uuid=" + r.getUuid() + " отсутсвует в хранилище.");
        } else {
            storage[index] = r;
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

    /**
     * @return array, contains only Resumes in storage (without null).
     */

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
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
