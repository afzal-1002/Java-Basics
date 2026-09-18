package com.epam.rd.autotasks;

public class Battleship8x8 {

    private final long ships;
    private long shots = 0L;

    public Battleship8x8(final long ships) {
        this.ships = ships;
    }

    public boolean shoot(String shot) {

        char columnChar = Character.toUpperCase(shot.charAt(0));

        int column = columnChar - 'A';
        int row = shot.charAt(1) - '1';

        int index = 63 - (row * 8 + column);

        long mask = 1L << index;

        // Register the shot
        shots |= mask;

        // Check if the shot hit a ship
        return (ships & mask) != 0;
    }

    public String state() {

        StringBuilder result = new StringBuilder();

        for (int row = 0; row < 8; row++) {

            for (int column = 0; column < 8; column++) {

                int index = 63 - (row * 8 + column);
                long mask = 1L << index;

                boolean hasShip = (ships & mask) != 0;
                boolean wasShot = (shots & mask) != 0;

                if (hasShip && wasShot) {
                    result.append('☒');
                } else if (hasShip) {
                    result.append('☐');
                } else if (wasShot) {
                    result.append('×');
                } else {
                    result.append('.');
                }
            }

            if (row < 7) {
                result.append('\n');
            }
        }

        return result.toString();
    }
}