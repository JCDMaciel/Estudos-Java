import java.util.Scanner;

public class Principal{
    public static void main(String[] args){
        Cadastro c = new Cadastro();
        Scanner in = new Scanner(System.in);
        int start;
        boolean sair = false;

        while (!sair){
            System.out.println("******* Menu principal *******");
            System.out.println("Deseja acessar a tela de cadastros?\n1 para sim\n2 caso queira sair");
            start = in.nextInt();

            if (start == 1){
                while (start == 1){
                    c.menu();
                    start = 0;
                }
            } else if (start == 2) sair = true;
            else System.out.println("******* Opção inválida *******");
        }
    }
}
