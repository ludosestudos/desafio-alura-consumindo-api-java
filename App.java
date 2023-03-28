import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) throws Exception {

        // fazer uma conexão HTTP e buscar os top 250 filmes
        // String url = "https://imdb-api.com/en/API/Top250Movies/k_0ojt0yvm | logar na plataforma vídeo 42 minutos";
        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        // extrair só os dados que interessam (titulo, poster, classificação)
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        // exibir e manipular os dados
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println (("\u001b[37m \u001b[44mTítulo: \u001b[m") + (filme.get("title")));
            //System.out.println ("Filme :" + (filme.get("title")));
            System.out.println ("Imagem :" + (filme.get("image")));
            System.out.print  ("Classificação: " + (filme.get("imDbRating") + " | "));
            double classificao = Double.parseDouble(filme.get("imDbRating"));
            int numeroEstrela = (int)classificao;

            for (int i = 1; i <= numeroEstrela; i++) {
                System.out.print("🎖️");
            }
            System.out.println("\n");
        }
    }
}
