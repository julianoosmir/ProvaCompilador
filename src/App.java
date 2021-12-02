import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Scanner teclado = new Scanner(System.in);
        String codigofonte;
        System.out.print("Digite o nome do programa:");
        codigofonte = teclado.nextLine();
        Arquivo arquivo = new Arquivo(codigofonte);
        List<String> fonte = arquivo.lerArquivo();

        Boolean compiladoOK = executaCompilacao(fonte);

        if (compiladoOK) {
            linkar("src/", fonte);
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
        String app = nomeArquivo.replace(".cpp", "App");
        String linhaDeComando = "g++ " + nomeArquivo + " -o " + app;
        Runtime comando = Runtime.getRuntime();
        try {

            Process p = comando.exec(linhaDeComando);
            p.waitFor();
            System.out.println(linhaDeComando);
            return true;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}
