package ru.netology.storage;

public interface IGetProducts<R, T, S>{
    R get(T t, S s);
}
