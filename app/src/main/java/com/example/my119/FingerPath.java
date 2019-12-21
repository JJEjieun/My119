package com.example.my119;

import android.graphics.Path;


// 전자서명 구현하기 위한 클래스 초기화
public class FingerPath {
    public int color;
    public int strokeWidth;
    public Path path;

    public FingerPath(int color, int strokeWidth, Path path) {
        this.color = color;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }
}