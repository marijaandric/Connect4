package com.example.mojprojekat;

import java.net.Socket;

public class Connect4Game {
    public Socket client1;
    public Socket client2;
    public static int row =6;
    public static int column = 7;
    public Polje polje[][];
    public int igrac = 1;

    public Connect4Game()
    {
        inicijalizuj();
    }

    public boolean checkWin()
    {
        for (int r = 0; r < row; r++) {
            for (int col = 0; col <= column - 4; col++) {
                if (polje[r][col].igrac != 0 &&
                        polje[r][col].igrac == polje[r][col + 1].igrac &&
                        polje[r][col].igrac == polje[r][col + 2].igrac &&
                        polje[r][col].igrac == polje[r][col + 3].igrac) {
                    return true;
                }
            }
        }

        // Provera vertikalno
        for (int col = 0; col < column; col++) {
            for (int r = 0; r <= row - 4; r++) {
                if (polje[r][col].igrac != 0 &&
                        polje[r][col].igrac == polje[r + 1][col].igrac &&
                        polje[r][col].igrac == polje[r + 2][col].igrac &&
                        polje[r][col].igrac == polje[r + 3][col].igrac) {
                    return true;
                }
            }
        }

        // Provera dijagonalno
        for (int r = 0; r <= row - 4; r++) {
            for (int col = 0; col <= column - 4; col++) {
                if (polje[r][col].igrac != 0 &&
                        polje[r][col].igrac == polje[r + 1][col + 1].igrac &&
                        polje[r][col].igrac == polje[r + 2][col + 2].igrac &&
                        polje[r][col].igrac == polje[r + 3][col + 3].igrac) {
                    return true;
                }
            }
        }

        // Provera dijagonalno
        for (int r = 0; r <= row - 4; r++) {
            for (int col = 3; col < column; col++) {
                if (polje[r][col].igrac != 0 &&
                        polje[r][col].igrac == polje[r + 1][col - 1].igrac &&
                        polje[r][col].igrac == polje[r + 2][col - 2].igrac &&
                        polje[r][col].igrac == polje[r + 3][col - 3].igrac) {
                    return true;
                }
            }
        }

        // Provera da li je nereseno
        for (int r = 0; r <= row; r++) {
            for (int col = 0; col < column; col++) {
                if (polje[r][col].igrac == 0 ) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean makeMove(int kolona)
    {

        for(int i = row-1;i>=0;i--)
        {
            if(polje[i][kolona].igrac == 0)
            {
                polje[i][kolona].igrac = igrac;
                igrac = igrac * (-1);
                return true;
            }
        }
        return false;
    }

    public void inicijalizuj()
    {
        polje = new Polje[row][column];
        for(int i=0;i<row;i++)
        {
            for(int j = 0;j<column;j++)
            {
                Polje p = new Polje(i,j);
                polje[i][j] = p;
            }
        }

    }
}
