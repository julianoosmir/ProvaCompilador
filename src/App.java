import java.util.Arrays;
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
        Arquivo arquivo = new Arquivo(codigofonte);
        List<String> fonte = arquivo.lerArquivo();

//        String linha = "for i = 1 to 10";
//        String tokens[] = linha.split(" ");
//        System.out.println(tokens.length);
//        for (String cha : tokens) {
//            System.out.println(cha + " = " + Arrays.asList(tokens).indexOf(cha));
//        }
        Boolean compiladoOK = executaCompilacao(fonte);

        if (compiladoOK) {
            linkar("/", fonte);

        }


    }

    public static Boolean executaCompilacao(List<String> fonte) {
        Compilador comp = new Compilador(fonte);
        Boolean compiladoOK = comp.compilar();

        if (compiladoOK) {
            System.out.println("Compilação: OK");
        } else {
            System.out.println("Compilação: Erro");
        }
        return compiladoOK;
    }

    public static Boolean linkar(String caminho, List<String> fonte) {

        String nomeArquivo = caminho;
        String linha = fonte.get(0);
        String palavras[] = linha.split(" ");
        nomeArquivo += palavras[1].replaceAll("\"", "");
        nomeArquivo += ".cpp";
        nomeArquivo = nomeArquivo.replaceAll("/","");

        try {
            String app = nomeArquivo.replace(".cpp", "App");
            String linhaDeComando = "g++ " + nomeArquivo + " -o " + app;

            Runtime comando = Runtime.getRuntime();
            comando.exec(linhaDeComando);

            // falta arrumar o delete


            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}
