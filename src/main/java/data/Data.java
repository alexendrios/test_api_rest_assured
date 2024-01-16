package data;

import com.github.javafaker.Faker;
import java.text.DecimalFormat;
import java.util.Locale;
import java.util.Random;

public class Data {

    private static Faker massaTeste(){
       return  new Faker(new Locale("en-US"));
    }

    private static double arredondar(double valor) {
        DecimalFormat df = new DecimalFormat("#.##");
        return Double.parseDouble(df.format(valor).replace(",", "."));
    }

    private static double gerarDesconto() {
        Random random = new Random();
        return  random.nextDouble() * 30;
    }

    private static double gerarAvaliacao() {
        Random random = new Random();
        return random.nextDouble() * 5;
    }

    private static int gerarEstoque() {
        Random random = new Random();
        return  random.nextInt(1001);
    }

    private static String gerarImagem(){
        return massaTeste().avatar().image();
    }

    private static String titulo = massaTeste().commerce().productName();
    private static String descricao = massaTeste().lorem().sentence();
    private static String preco = massaTeste().commerce().price().replace(",", ".");
    private  static double desconto = gerarDesconto();
    private  static double avaliacao = gerarAvaliacao();
    private  static int estoque = gerarEstoque();
    private static String marca = massaTeste().commerce().material();
    private static  String categoria = massaTeste().commerce().department();

    public static String getSucessUser(String user, String password){
        return "{\n" +
                "    \"username\": \""+user+"\",\n" +
                "    \"password\": \""+password+"\"\n" +
                "}";
    }
    public static String getSucessProduto(){
        return "{\n" +
                "    \"title\": \"" + titulo +"\",\n"+
                "    \"description\": \""+descricao+"\",\n" +
                "    \"price\": "+preco+",\n" +
                "    \"discountPercentage\": "+ arredondar(desconto) +",\n" +
                "    \"rating\": "+arredondar(avaliacao)+",\n" +
                "    \"stock\": "+estoque+",\n" +
                "    \"brand\": \""+marca+"\",\n" +
                "    \"category\": \""+categoria+"\",\n" +
                "    \"thumbnail\": \""+gerarImagem()+"\"\n" +
                "}";
    }

    public static String getInSucessProduto(){
        return "{\n" +
                "    \"title\": \"" + titulo +"\",\n"+
                "    \"description\": \""+descricao+"\",\n" +
                "    \"price\": false,\n" +
                "    \"discountPercentage\": false,\n" +
                "    \"rating\": false,\n" +
                "    \"stock\": false,\n" +
                "    \"brand\": \""+marca+"\",\n" +
                "    \"category\": \""+categoria+"\",\n" +
                "    \"thumbnail\": \""+gerarImagem()+"\"\n" +
                "}";
    }

    public static String getFormatoErradoProduto(){
        return "{\n" +
                "    \"id\": \"false\",\n" +
                "    \"titulo\": \"" + titulo +"\",\n"+
                "    \"descricao\": \""+descricao+"\",\n" +
                "    \"disco\": false,\n" +
                "    \"discografia\": false,\n" +
                "    \"desc\": false,\n" +
                "    \"desc0\": false,\n" +
                "    \"desc1\": \""+marca+"\",\n" +
                "    \"desc2\": \""+categoria+"\",\n" +
                "    \"image\": "+preco+"\n" +
                "}";
    }

    public static  String getFormatoErradoDocumento(){
        return "{\n" +
                "    \"title\": \"" + titulo +"\",\n"+
                "    \"description\": \""+descricao+"\"\n" +
                "    \"price\": "+preco+",\n" +
                "    \"discountPercentage\": "+ arredondar(desconto) +",\n" +
                "    \"rating\": "+arredondar(avaliacao)+",\n" +
                "    \"stock\": "+estoque+",\n" +
                "    \"brand\": \""+marca+"\"\n" +
                "    \"category\": \""+categoria+"\",\n" +
                "    \"thumbnail\": \""+gerarImagem()+"\"\n" +
                "}";
    }
}
