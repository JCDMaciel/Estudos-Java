package br.com.desafio_05.model;

import java.time.LocalDate;
import java.time.Year;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validacao {
    public static boolean isCpf(String CPF) {
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        try {
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48);

            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10)))
                return(true);
            else return(false);
        } catch (InputMismatchException erro) {
            return(false);
        }
    }

    public static String imprimeCPF(String CPF) {
        return(CPF.substring(0, 3) + "." + CPF.substring(3, 6) + "." +
                CPF.substring(6, 9) + "-" + CPF.substring(9, 11));
    }

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

    public static boolean isEmail(String email) {
        boolean isEmailIdValid = false;
        if (email != null && email.length() > 0) {
            String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
            Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(email);
            if (matcher.matches()) {
                isEmailIdValid = true;
            }
        }
        return isEmailIdValid;
    }
}
