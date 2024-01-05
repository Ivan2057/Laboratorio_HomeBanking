package Entidades;

public class InicioSesion {
    private String usuario;
    private String password;
    private boolean usuarioCorrecto;

    public boolean usuarioCorrecto(){
        return usuarioCorrecto = true;
    }

    public boolean usuarioIncorrecto(){
        return usuarioCorrecto = false;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isUsuarioCorrecto() {
        return usuarioCorrecto;
    }

    public void setUsuarioCorrecto(boolean usuarioCorrecto) {
        this.usuarioCorrecto = usuarioCorrecto;
    }
}
