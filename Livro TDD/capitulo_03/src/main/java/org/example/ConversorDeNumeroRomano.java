package org.example;

public class ConversorDeNumeroRomano {

    public boolean isRomano(String numeroRomano){
        String listaDeRomanos = "MDCLXVI";
        int contadorDeLetraIgual = 0;
        char letraAnterior = 0;

        if (numeroRomano == null || numeroRomano.equals("")) return false;

        for (char letra : numeroRomano.toCharArray()) {

            if (listaDeRomanos.indexOf(letra) == -1) {
                return false;
            }

            if (letra == letraAnterior){
                contadorDeLetraIgual++;

                if (letraAnterior == 'V') return false;
                if (letraAnterior == 'L') return false;
                if (letraAnterior == 'D') return false;

                if (contadorDeLetraIgual > 3){
                    return false;
                }
            } else {
                letraAnterior = letra;
                contadorDeLetraIgual = 1;
            }

        }

        return true;
    }

    public int converte(String numeroRomano) {
        int numero = 0;

        int[] valores = new int[numeroRomano.length()];

        numeroRomano = numeroRomano.toUpperCase();

        for (int i = 0; i < numeroRomano.length(); i++) {
            switch (numeroRomano.charAt(i)) {
                case 'M':
                    valores[i] = 1000;
                    break;
                case 'D':
                    valores[i] = 500;
                    break;
                case 'C':
                    valores[i] = 100;
                    break;
                case 'L':
                    valores[i] = 50;
                    break;
                case 'X':
                    valores[i] = 10;
                    break;
                case 'V':
                    valores[i] = 5;
                    break;
                case 'I':
                    valores[i] = 1;
                    break;
            }
        }

        for (int i = 0; i < valores.length; i++) {
            if (i + 1 < valores.length && valores[i] < valores[i + 1]) {
                numero -= valores[i];
            } else {
                numero += valores[i];
            }
        }

        return numero;
    }
}
