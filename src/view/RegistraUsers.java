package view;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.layout.Pane;

import model.User;

public class RegistraUsers  implements Initializable{


@FXML
 private Label LabelChange;
	 
 @FXML
 private Label TitlleUser;

 @FXML
 private TextField PesquisaNome;

 @FXML
 private Button findByName;

 @FXML
 private TableView<User> TableView;

 @FXML
 private TableColumn<User, Integer> Idcollum;

 @FXML
 private TableColumn<User, String> UserCollum;

 @FXML
 private TableColumn<User, String> CpfCollum;

 @FXML
 private TableColumn<User, String> EmailCollum;

 @FXML
 private ComboBox<String> OptionsComboBox;

 @FXML
 private Label LabelName;

 @FXML
 private Label LabelCPF;

 @FXML
 private Label labelemail;

 @FXML
 private TextField TxtName;

 @FXML
 private TextField Txtemail;

 @FXML
 private TextField Txtid;

 @FXML
 private TextField TxtCpf;
 
 @FXML
 private Button PesquisarId;

 @FXML
 private Button btnAdd;
 
 @FXML
 private Pane Painel;
 
 @FXML
 private Label LabelId;
 
    
 @FXML
 private Button exitid;
 
 @FXML
 void Exit(ActionEvent event) {

	new ShowAlert().mensagemAlert();
    	
       
	    System.exit(0);
	 
 }

 
 
    private List<User> listaClientes = new ArrayList<User>();		
        
    @FXML
    void findById(ActionEvent event) {
    	if(!pesquisaPorId()) {
			new ShowAlert().erroAlert();
		}  
    }
    
    public boolean pesquisaPorId() {
    	 ObservableList<User> lista = FXCollections.observableArrayList(listaClientes);
         
         
        for(User cliente: lista) {
        	if(cliente.getId().equals(Integer.parseInt(Txtid.getText()))) {
        		TxtName.setText(cliente.getNome());
        		TxtCpf.setText(cliente.getCpf());
        		Txtemail.setText(cliente.getEmail());
        		Txtid.setEditable(false);
        		
        		TxtName.setDisable(false);
    			TxtCpf.setDisable(false);
    			Txtemail.setDisable(false);
    			btnAdd.setDisable(false);
    			
        		return true;
        	}
        }
         
         
         return false;
   }

  
    @FXML
    void BtnAction(ActionEvent event) {
    	if(OptionsComboBox.getValue() == "Incluir") {
    		incluirCliente();
    	} else if(OptionsComboBox.getValue() == "Alterar") {
    		Txtid.setEditable(true);
    		alterarCliente();
    	} else if(OptionsComboBox.getValue() == "Excluir") {
    		Txtid.setEditable(true);
    		excluirCliente();
    	}
    }

    @FXML
    void findByName(ActionEvent event) {
    	
    	ObservableList<User> lista = FXCollections.observableArrayList(listaClientes);
        
    	List<User> novaLista = new ArrayList<User>();
    	
	        if(!PesquisaNome.getText().isEmpty() || !PesquisaNome.getText().equals("")) {
		        for(User cliente: lista) {
		        	if(cliente.getNome().contains(PesquisaNome.getText())) {
		        		System.out.println(cliente);
		        		novaLista.add(cliente);
		        	}
		        }
		        
		 	   ObservableList<User> novaListaObs = FXCollections.observableArrayList(novaLista);

		 	  TableView.setItems(novaListaObs);
	        } else {
	        	listar();
	        }
    }
    
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Painel.setVisible(true);
		
		OptionsComboBox.getItems().setAll("Incluir", "Alterar", "Excluir"); 
		
		OptionsComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String s2) {
            	limparCampos();
            	if(OptionsComboBox.getValue() == "Incluir") {
            		camposVisiveis("Adicionar Cliente", "Adicionar", 300, false);
            		Txtid.setEditable(true);
            	} else if (OptionsComboBox.getValue() == "Alterar"){
            		camposVisiveis("Editar Cliente", "Editar", 157, true);
            		Txtid.setEditable(true);
            	} else if (OptionsComboBox.getValue() == "Excluir"){
            		camposVisiveis("Excluir Cliente", "Excluir", 157, true);
            		Txtid.setEditable(true);
            	}
            }
        });
		
		listar();
		
	
	}
	
	public void incluirCliente() {
		if(validacaoCampos()) {
			if(listaClientes.size() != 0) {
				User cliente = listaClientes.get(listaClientes.size() -1);
			
				listaClientes.add(new User(cliente.getId() + 1, TxtCpf.getText(), Txtemail.getText(), TxtName.getText()));
			} else {
				listaClientes.add(new User(1, TxtCpf.getText(), Txtemail.getText(), TxtName.getText()));
			}
			new ShowAlert().sucessoAlert("Cliente adicionado com sucesso!");
			limparCampos();
			listar();
		} else {
			new ShowAlert().validacaoAlert();
		}
	}
	
	public void alterarCliente() {
		if(validacaoCampos()) {
	        ObservableList<User> lista = FXCollections.observableArrayList(listaClientes);
	
	        for(User cliente: lista) {
	        	if(cliente.getId().equals(Integer.parseInt(Txtid.getText()))) {
	        		listaClientes.remove(cliente);
	        		listaClientes.add(new User(Integer.parseInt(Txtid.getText()), TxtCpf.getText(), Txtemail.getText(), TxtName.getText()));
	        	}
	        }
	        
			new ShowAlert().sucessoAlert("Cliente editado com sucesso!");

	    	limparCampos();
	    	
	        listar();
	        
			camposVisiveis("Editar Cliente", "Editar", 157, true);
		} else {
			new ShowAlert().validacaoAlert();
		}
	}
	
	public void excluirCliente() {
        ObservableList<User> lista = FXCollections.observableArrayList(listaClientes);

        if (new ShowAlert().confirmationAlert()) {
			for(User cliente: lista) {
				if(cliente.getId().equals(Integer.parseInt(Txtid.getText()))) {
					listaClientes.remove(cliente);
		        }
		    }		
		}
        
    	limparCampos();
    	
        listar();
        
		camposVisiveis("Excluir Cliente", "Excluir", 157, true);

	}
	
	
	public void listar() {
		UserCollum.setCellValueFactory(new PropertyValueFactory<User, String>("Nome"));
		CpfCollum.setCellValueFactory(new PropertyValueFactory<User, String>("Cpf"));
		EmailCollum.setCellValueFactory(new PropertyValueFactory<User, String>("Email"));
		Idcollum.setCellValueFactory(new PropertyValueFactory<User, Integer>("Id"));
		
		TableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	    
	    ObservableList<User> lista = FXCollections.observableArrayList(listaClientes);

	    TableView.setItems(lista);
	}
	
	public void limparCampos() {
		Txtid.setText("");
		TxtName.setText("");
		TxtCpf.setText("");
		Txtemail.setText("");
	}
	

	public void camposVisiveis(String titulo,String botao, int tamanho, boolean visivel) {
		LabelChange.setText(titulo);
		btnAdd.setText(botao);
		
		Txtid.setPrefWidth(tamanho);
		PesquisarId.setVisible(visivel);
		Painel.setVisible(true);
		
		TxtName.setDisable(visivel);
		TxtCpf.setDisable(visivel);
		Txtemail.setDisable(visivel);
		btnAdd.setDisable(visivel);
		
		
	}
	
	public boolean validacaoCampos() {
		
		if(Txtemail.getText().isEmpty() | TxtName.getText().isEmpty() | TxtCpf.getText().isEmpty()) {
			return false;
		}	
		return true;
	}

}
