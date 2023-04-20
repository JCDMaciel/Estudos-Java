import java.time.Year;
import java.util.Calendar;

public class ValidaData {
    public static String imprimeDataDeNascimento(String dataDeNascimento) {
        return(dataDeNascimento.substring(0, 2) + "/" + dataDeNascimento.substring(2, 4) + "/" + dataDeNascimento.substring(4, 8));
    }
    public static boolean validaDataDeNascimento(String dataDeNascimento) {
        int dia = Integer.parseInt(dataDeNascimento.substring(0, 2));
        int mes = Integer.parseInt(dataDeNascimento.substring(2, 4));
        int ano = Integer.parseInt(dataDeNascimento.substring(4, 8));

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

    public static boolean validaDataFutura(String dataDeNascimento){
        Calendar calendar = Calendar.getInstance();

        int dia = Integer.parseInt(dataDeNascimento.substring(0, 2));
        int mes = Integer.parseInt(dataDeNascimento.substring(2, 4));
        int ano = Integer.parseInt(dataDeNascimento.substring(4, 8));

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
