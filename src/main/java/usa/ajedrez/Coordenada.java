package usa.ajedrez;

public class Coordenada {
    private int posicionX, posicionY;

    public Coordenada(int posicionX, int posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public Coordenada(String coordenada){
        posicionX=(char)coordenada.toCharArray()[0]-'a';
        posicionY=Integer.parseInt(coordenada.substring(1,2))-1;
    }

    public boolean esValida(){
        return (posicionX >= 0 && posicionX < 8) && (posicionY >= 0 && posicionY < 8);
    }

    public int getX() {
        return posicionX;
    }

    public void setX(int posicionX) {
        this.posicionX = posicionX;
    }

    public int getY() {
        return posicionY;
    }

    public void setY(int posicionY) {
        this.posicionY = posicionY;
    }

    public String toString(){
        return Integer.toString(posicionX)+", "+Integer.toString(posicionY);
    }

    public boolean coordenadasIguales(Coordenada coordenada){
        return((posicionX==coordenada.getX())&&(posicionY== coordenada.getY()));
    }

    public String getCoordenadaParseada(){
        String coordenadaString="";
        coordenadaString = (char) (posicionX+'a')+Integer.toString(posicionY+1);
        return coordenadaString;
    }
}
