package com.challenger.alkemy.api.warmup.commons;

import java.beans.PropertyEditor;
import java.io.FileNotFoundException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.URLEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import com.challenger.alkemy.api.warmup.models.entity.Categoria;
import com.challenger.alkemy.api.warmup.models.entity.Usuario;
import com.challenger.alkemy.api.warmup.services.CategoriaService;
import com.challenger.alkemy.api.warmup.services.UsuarioService;

@Component
public class CommonsUtils {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private CategoriaService categoriaService;
	
	public ResponseEntity<?> validar(BindingResult result){
		Map<String, Object> errores = new HashMap<>();
		result.getFieldErrors().forEach(err->{
			errores.put(err.getField(), "El campo " + err.getField() +  " " + err.getDefaultMessage());
		});
		
		return ResponseEntity.badRequest().body(errores);
	}
	
	public void validarUrl(String urlImage) throws Exception {
		try {
		    PropertyEditor urlEditor = new URLEditor();
		    urlEditor.setAsText(urlImage);
		    
		    final URL url = new URL(urlImage);
		    HttpURLConnection huc = (HttpURLConnection) url.openConnection();
		    int responseCode = huc.getResponseCode();
		    
		    if(responseCode!=RESPONSE_CODE_OK) {
		    	throw new Exception("Error, no se puede acceder a imagen.");
		    }
		    
		    String contentType = huc.getContentType();
	        if (contentType != null) {
	            if (!contentType.startsWith("image/")) {
	                throw new Exception("El formato del archivo no corresponde a una imagen.");
	            }
	        }

		} catch (IllegalArgumentException ex) {
    		throw new Exception("Formato URL invalido");
		} catch (UnknownHostException uhe) {
			throw new Exception(String.format("Error host (%s) inexistente.", uhe.getMessage()));
		} catch (FileNotFoundException fnfe) {
			throw new Exception(String.format("Error archivo (%s) inexistente.", fnfe.getMessage()));
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		return;
	}
	
	
	public Categoria validarCategoria(Long categoriaId) throws Exception {
		Optional<Categoria> categoriaDb =  categoriaService.findCategoriaById(categoriaId);

		if(!categoriaDb.isPresent()) {
	    	throw new Exception(String.format("La categoria con ID (%s) no existe", String.valueOf(categoriaId)));
		}
		
		return categoriaDb.get();
	}
	
	
	public Usuario validarUsuario(String usuario) throws Exception {
		Optional<Usuario> usuarioDb =  usuarioService.findUsuarioByEmail(usuario);

		if(!usuarioDb.isPresent()) {
	    	throw new Exception(String.format("El usuario con ID (%s) no existe", usuario));
		}
		
		return usuarioDb.get();
	}

	
	public static String FILTER_ORDER_ASC = "ASC";
	public static String FILTER_ORDER_DESC = "DESC";
	public static Integer RESPONSE_CODE_OK = 200;
}
