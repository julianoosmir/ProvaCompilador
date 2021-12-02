import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Compilador {
    public String nomeArquivo;
    public List<String> codigoFonte;
    public HashMap<String, Integer> tabelaSimbolos;
    public int numerolinha = 0;
    public List<String> byteCode; // codigo que sera gerado pelo compilador

    public Compilador(List<String> fonte) {
        this.codigoFonte = fonte;
        this.byteCode = new ArrayList<>();

    }

    public Boolean compilar() {

        Boolean erro = false;
        this.numerolinha = 0;

        // passar por todas as linhas do codigo fonte
        for (String linha : this.codigoFonte) {

            erro = compilarLinha(linha.trim());

            if (erro) {
                System.out.println("Erro ao compilar o programa: " + this.numerolinha + " => " + linha);
            }
            numerolinha++;
        }
        if (!erro) {
            gravarByteCode();
        }
        return !erro;
    }

    private Boolean compilarLinha(String linha) {
        Boolean erro = false;

        if (linha.startsWith("begin")) {
            erro = processarBegin(linha);
        }
        if (linha.equals("fim")) {
            erro = processarFim(linha);
        }

        if (linha.startsWith("var")) {
            erro = processarVar(linha);
        }
        if (linha.startsWith("output")) {
            erro = processarOutput(linha);
        }
        if (linha.startsWith("input")) {
            erro = processarInput(linha);
        }
        if (linha.equals("novalinha")) {
            this.byteCode.add("cout<< \"\\n\"");
        }

        if (linha.startsWith("for")) {
            erro = processarFor(linha);
        }

        if (linha.equals("fimconte")) {
            this.byteCode.add("}");

        }



        return erro;
    }

    private Boolean processarFor(String linha) {
        Boolean erro = false;
        String tokens[] = linha.split(" ");

        String inicio = tokens[4];
        String fim = tokens[6];
        String variavel = tokens[2];
        String resultado = "for( " + variavel +"="+inicio+";" + variavel +" < "+fim+";"+variavel +"++){";
        this.byteCode.add(resultado);
        return erro;
    }

    private Boolean processarInput(String linha) {
        Boolean erro = false;
        String variavel = linha.replace("input ","");
        String resultado = "cin >> "+variavel+";";
        this.byteCode.add(resultado);
        return erro;
    }

    private Boolean processarOutput(String linha) {
        Boolean erro = false;
        String resultado = linha.replace("output", "");
        resultado = " cout <<" + resultado;
        resultado += ";";
        this.byteCode.add(resultado);
        return erro;
    }

    private Boolean processarVar(String linha) {
        Boolean erro = false;
        String resultado = linha.replace("var", "int");
        resultado += ";";
        this.byteCode.add(resultado);

        return erro;
    }

    private Boolean processarBegin(String linha) {
        Boolean erro = false;
        this.byteCode.add("#include  <iostream>");
        this.byteCode.add("int main() {");

        String tokens[] = linha.split(" ");
        this.nomeArquivo = tokens[1].replaceAll("\"", "");
        this.nomeArquivo += ".cpp";

        return erro;
    }

}
