package com.example.labwork8_1.Util;

import lombok.NonNull;

public class UtilArray {

    public static int barrierElement(int @NonNull[] arr, int value) {
        final int size = arr.length;
        if (size != 0) {
            int last = arr[size - 1];//�������� ������� ������� �������
            arr[size - 1] = value;//�����������, ��� value ���� � �������
            //���� �������� ����, ��� ������� ���� � �������, ������ ������ ����� �� ���������
            int i = 0;
            for (i = 0; arr[i] != value; ++i) {//���� ������� � �����
            }
            arr[size - 1] = last;//��������������� ��������� �������
            if (i != (size - 1) || value == last) {//�� ��������� � ������ ��� ��������� ������� ��� �������
                return i;
            }
        }
        return -1;
    }

    public static void insertionSort(int @NonNull [] arrayPtr) // ���������� ���������
    {
        int temp;// ��������� ���������� ��� �������� �������� �������� ������������ �������
        // ������ ����������� ��������
        int item;
        for (int counter = 1; counter < arrayPtr.length; counter++)
        {
            temp = arrayPtr[counter]; // �������������� ��������� ���������� ������� ��������� �������� �������
            item = counter-1; // ���������� ������ ����������� �������� �������
            while(item >= 0 && arrayPtr[item] > temp) // ���� ������ �� ����� 0 � ���������� ������� ������� ������ ��������
            {
                arrayPtr[item + 1] = arrayPtr[item]; // ������������ ��������� �������
                arrayPtr[item] = temp;
                item--;
            }
        }
    }
}

