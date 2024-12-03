package com.appx.model;

public sealed interface Expression permits Mul, Dont, Do {
}
