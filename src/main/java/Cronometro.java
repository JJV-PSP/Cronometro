import javafx.scene.control.Button;

public class Cronometro extends Application {
    private TextField inputField;
    private ProgressBar progressBar;
    private Label tiempoLabel;
    private Button iniciarButton;
    private Button cancelarButton;
    private ToggleButton temaButton;
    private ComboBox<Integer> tiempoPredefinidoCombo;
    private int tiempoTotal;
    private int tiempoActual;
    private boolean contando;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Contador de Tiempo");
        VBox root = new VBox(10);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);

        Label instruccionLabel = new Label("Introduce el tiempo en segundos:");
        inputField = new TextField();
        progressBar = new ProgressBar(0);
        progressBar.setPrefWidth(200);
        tiempoLabel = new Label("Tiempo: 0:00:00");
        iniciarButton = new Button("Iniciar");
        cancelarButton = new Button("Cancelar");
        cancelarButton.setDisable(true);

        // ComboBox para tiempos predefinidos
        tiempoPredefinidoCombo = new ComboBox<>();
        tiempoPredefinidoCombo.getItems().addAll(10, 30, 60, 120); // Ejemplos de tiempos predefinidos
        tiempoPredefinidoCombo.setPromptText("Selecciona un tiempo");

        // ToggleButton para cambiar tema
        temaButton = new ToggleButton("Tema Oscuro");
        temaButton.setOnAction(e -> cambiarTema());

        root.getChildren().addAll(instruccionLabel, inputField, tiempoPredefinidoCombo, progressBar, tiempoLabel, iniciarButton, cancelarButton, temaButton);

        iniciarButton.setOnAction(e -> iniciarContador());
        cancelarButton.setOnAction(e -> cancelarContador());

        Scene scene = new Scene(root, 300, 250);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void iniciarContador() {
        try {
            if (tiempoPredefinidoCombo.getValue() != null) {
                tiempoTotal = tiempoPredefinidoCombo.getValue();
                inputField.setText(String.valueOf(tiempoTotal));
            } else {
                tiempoTotal = Integer.parse