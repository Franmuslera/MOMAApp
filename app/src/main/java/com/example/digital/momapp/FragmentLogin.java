package com.example.digital.momapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLogin extends Fragment {
    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private EditText editTextEmail;
    private EditText editTextContraseña;
    private Button buttonRegistrar;
    private Button buttonLogin;
    private FirebaseAuth mAuth= FirebaseAuth.getInstance();



    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        editTextEmail = view.findViewById(R.id.edit_text_email);
        editTextContraseña = view.findViewById(R.id.edit_text_contraseña);
        buttonRegistrar= view.findViewById(R.id.butto_registrar);
        buttonLogin= view.findViewById(R.id.button_login);




        loginButton = view.findViewById(R.id.button_login_facebook);
        loginButton.setReadPermissions("email");
        loginButton.setFragment(this);
        callbackManager = CallbackManager.Factory.create();
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                updateUI(loginResult.getAccessToken());
                Toast.makeText(getContext(), "Acceso exitoso", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancel() {
                Toast.makeText(getContext(),"Cancel",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();

            }
        });

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= editTextEmail.getText().toString();
                String contraseña = editTextContraseña.getText().toString();
                if (email.isEmpty() && contraseña.isEmpty()){
                    Toast.makeText(getContext(), "Ingrese su email y contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!email.contains("@") && !email.contains(".com")){
                    Toast.makeText(getContext(), "Ingresar email valido", Toast.LENGTH_SHORT).show();
                }else if (contraseña.length()<6){
                    Toast.makeText(getContext(), "La contraseña debe tener al menos 6 caracteres ", Toast.LENGTH_SHORT).show();
                    return;
                }else crearUsuario(email,contraseña);
            }
        });

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email= editTextEmail.getText().toString();
                String contraseña = editTextContraseña.getText().toString();
                if (email.isEmpty() && contraseña.isEmpty()){
                    Toast.makeText(getContext(), "Ingrese su email y contraseña", Toast.LENGTH_SHORT).show();
                    return;
                }else if (!email.contains("@") && !email.contains(".com")){
                    Toast.makeText(getContext(), "Ingresar email valido", Toast.LENGTH_SHORT).show();
                }else if (contraseña.length()<6){
                    Toast.makeText(getContext(), "La contraseña debe tener al menos 6 caracteres ", Toast.LENGTH_SHORT).show();
                    return;
                }else loguearUsuario(email,contraseña);

            }
        });













        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
      //  mAuth.addAuthStateListener(authStateListener);
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        updateUI(accessToken);
    }



    public void updateUI(AccessToken accessToken) {
        if (accessToken != null && !accessToken.isExpired()) {
            Intent intent = new Intent(getView().getContext(), ActivityInicio.class);
            startActivity(intent);
            getActivity().finish();
        }

    }
    private void crearUsuario(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //Log.d("firebase", "createUserWithEmail:success");
                    //FirebaseUser user = mAuth.getCurrentUser();

                    Toast.makeText(getContext(), "Registro Exitoso", Toast.LENGTH_SHORT).show();
                } else {
                    //Log.w("firebase", "createUserWithEmail:failure", task.getException());
                    Toast.makeText(getContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void loguearUsuario(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d("firebase", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                } else {
                    Log.w("firebase", "signInWithEmail:failure", task.getException());
                    Toast.makeText(getContext(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public interface ReaccionadorDelUsuario {
        public void succesfull();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
