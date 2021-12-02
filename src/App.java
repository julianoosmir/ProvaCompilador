import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
       // https://github.com/SidneyMaldonado/Cport.git (fetch)
        Scanner teclado = new Scanner(System.in);
        String codigofonte;
        System.out.print("Digite o nome do programa:");
        codigofonte = teclado.nextLine();
        Arquivo arquivo = new Arquivo(codigofonte); // fazer interface para o usuario
        List<String> fonte = arquivo.lerArquivo();

//        for(String linha : fonte){
//            System.out.println(linha);
//        }

    }
}
