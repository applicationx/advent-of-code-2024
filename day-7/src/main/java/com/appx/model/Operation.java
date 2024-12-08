package com.appx.model;

public enum Operation {
    ADD {
        @Override
        public long apply(long a, long b) {
            return a + b;
        }
    },
    MULTIPLY {
        @Override
        public long apply(long a, long b) {
            return a * b;
        }
    },
    CONCATENATE {
        @Override
        public long apply(long a, long b) {
            String concatenated = String.valueOf(a) + String.valueOf(b);
            try {
                return Long.parseLong(concatenated);
            } catch (NumberFormatException e) {
                return -1; // Return -1 if the concatenated number is too large
            }
        }
    };

    public abstract long apply(long a, long b);
} 