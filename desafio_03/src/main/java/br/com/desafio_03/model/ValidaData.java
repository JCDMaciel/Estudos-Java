package br.com.desafio_03.model;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Calendar;

public class ValidaData {
    public static String imprimeDataDeNascimento(LocalDate dataDeNascimento) {
        return dataDeNascimento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static int obterValor(LocalDate data, ChronoField campo) {
        TemporalField campoTemporal = campo;
        return data.get(campoTemporal);
    }
    public static boolean validaDataDeNascimento(LocalDate dataDeNascimento) {
        int dia = obterValor(dataDeNascimento, ChronoField.DAY_OF_MONTH);
        int mes = obterValor(dataDeNascimento, ChronoField.MONTH_OF_YEAR);
        int ano = obterValor(dataDeNascimento, ChronoField.YEAR);

        if (dia <= 0 || mes <= 0 || ano <= 0000) {
            return false;
        }

        if (validaDataFutura(dataDeNascimento)) {
            System.out.println("Você não é um viajante do futuro");
            System.out.println();
            return false;
        }

        if (mes > 12) {
            System.out.println("Esse mes não existe");
            System.out.println();
            return false;
        }
        else {
            if (mes == 2 && Year.isLeap(ano) && dia > 29) {
                System.out.println("Esse dia não existe");
                System.out.println();
                return false;
            }
            else if (mes == 2 && !Year.isLeap(ano) && dia > 28) {
                System.out.println("Esse dia não existe");
                System.out.println();
                return false;
            }
            else {
                if (mes % 2 == 0 && dia > 30 && mes != 2) {
                    System.out.println("Esse dia não existe");
                    System.out.println();
                    return false;
                }
                if (mes % 2 != 0 && dia > 31) {
                    System.out.println("Esse dia não existe");
                    System.out.println();
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean validaDataFutura(LocalDate dataDeNascimento){
        Calendar calendar = Calendar.getInstance();

        int dia = obterValor(dataDeNascimento, ChronoField.DAY_OF_MONTH);
        int mes = obterValor(dataDeNascimento, ChronoField.MONTH_OF_YEAR);
        int ano = obterValor(dataDeNascimento, ChronoField.YEAR);

        if (ano > calendar.get(Calendar.YEAR)){
            return true;
        }
        else if (ano == calendar.get(Calendar.YEAR)){
            if (mes > calendar.get(Calendar.MONTH)){
                return true;
            }
            else if (mes == calendar.get(Calendar.MONTH)){
                if (dia > calendar.get(Calendar.DAY_OF_MONTH)) return true;
            }
        }
        return false;
    }
}
