package JeuParking;
import mvc.ExceptionsDuProjet.exceptionChevauchementDePiece;
import mvc.Modele;
import java.util.Observable;
import java.awt.*;


public class ModeleJeuParking extends Observable{

    //region ATTRIBUTS
    private Modele modelePlateau;
    private int idPieceSelected;
    private boolean partieFinie;
    //endregion

    // region GETTER
    public Modele getModelePlateau() {
        return modelePlateau;
    }
    public boolean isPartieFinie() {
        return partieFinie;
    }
    //endregion

    //region CONSTRUCTEUR
    /**
     * Création du modèle dans VueControllerJeuParking
     * spécifie la taille du plateau
     * pas de piece selectionnée au debut, la partie n'est pas finie et on initialise les contours du plateau
     *
     * @param nbColonnes
     * @param nbLignes
     */
    public ModeleJeuParking(int nbColonnes,int nbLignes){
        modelePlateau=new Modele(nbColonnes,nbLignes);
        idPieceSelected=0;
        partieFinie=false;
        // bordure :
        setContours();
    }
    //endregion

    //region METHODES
    /**
     * Création et positionnement des pieces de contour (pas de direction possible donc "")
     */
    private void setContours(){
        try{
            modelePlateau.posePiece(7,0,new boolean[][]{{true,true,true}},0,0,"",Color.BLACK);
            modelePlateau.posePiece(7,4,new boolean[][]{{true,true,true,true}},0,0,"",Color.BLACK);
            modelePlateau.posePiece(0,0,new boolean[][]{{true,true,true,true,true,true,true,true}},0,0,"",Color.BLACK);
            modelePlateau.posePiece(1,0,new boolean[][]{{true},{true},{true},{true},{true},{true}},0,0,"",Color.BLACK);
            modelePlateau.posePiece(1,7,new boolean[][]{{true},{true},{true},{true},{true},{true}},0,0,"",Color.BLACK);
        }catch (mvc.ExceptionsDuProjet.exceptionPieceHorsPlateau | exceptionChevauchementDePiece exceptionPieceHorsPlateau) {
            exceptionPieceHorsPlateau.printStackTrace();
        }
    }

    /**
     * Selection d'une piece grace a sa position, et remplie var @idPieceSelected avec son id
     *
     * @param posX coordonnées
     * @param posY coordonnées
     */
    public void selectionnerPiece(int posX,int posY) {
        this.idPieceSelected=modelePlateau.selectionnerPiece(posX,posY);
    }

    /**
     * Déplacement de la piece selectionnée préalablement (grace var @idPieceSelected), si = 0 rien ne se passe
     * direction = "horizontal" ou "vertical" (aurait pu etre "droite", "gauche", "bas", "haut")
     * on test si la partie est finie si une piece est située dans la case[7][3]
     *
     * @param direction
     */
    public void deplacerPiece(String direction){
        if(idPieceSelected !=0){
            try{
                modelePlateau.deplacementPiece(idPieceSelected,direction);
            } catch(Exception ignored){

            }
        }
        // pour tester la fin de partie on teste simplement si une piece est dans le trou des contours,
        //etant donnée que seule la piece principale peut y acceder
        if(modelePlateau.selectionnerPiece(7,3)!=0){
            partieFinie=true;
            setChanged();
            notifyObservers();
        }
    }


    /**
     * Suppression de toutes les pieces du plateau (pour commencer une nouvelle partie)
     * On remet les contours + on crée les pieces (piece principale de couleur rouge, autres pieces couleur aleat)
     * avec leur direction respective
     */
    public void initialiserNiveau1(){
        modelePlateau.clearPieces();
        setContours();

        try {
            //piece principale
            modelePlateau.posePiece(1,3,new boolean[][]{{true},{true}},0,0,"horizontal",Color.RED);
            //autres pieces
            modelePlateau.posePiece(3,2,new boolean[][]{{true},{true}},0,0,"horizontal");
            modelePlateau.posePiece(3,6,new boolean[][]{{true},{true}},0,0,"horizontal");
            modelePlateau.posePiece(5,2,new boolean[][]{{true},{true}},0,0,"horizontal");
            modelePlateau.posePiece(5,5,new boolean[][]{{true},{true}},0,0,"horizontal");
            modelePlateau.posePiece(1,1,new boolean[][]{{true},{true}},0,0,"horizontal");

            modelePlateau.posePiece(1,4,new boolean[][]{{true,true}},0,0,"vertical");
            modelePlateau.posePiece(2,4,new boolean[][]{{true,true,true}},0,0,"vertical");
            modelePlateau.posePiece(4,3,new boolean[][]{{true,true}},0,0,"vertical");
            modelePlateau.posePiece(3,3,new boolean[][]{{true,true,true}},0,0,"vertical");
            modelePlateau.posePiece(6,3,new boolean[][]{{true,true}},0,0,"vertical");
        } catch (mvc.ExceptionsDuProjet.exceptionPieceHorsPlateau | exceptionChevauchementDePiece exceptionPieceHorsPlateau) {
            exceptionPieceHorsPlateau.printStackTrace();
        }


    }

    /**
     * Suppression de toutes les pieces du plateau (pour commencer une nouvelle partie)
     * On remet les contours + on crée les pieces (piece principale de couleur rouge, autres pieces couleur aleat)
     * avec leur direction respective
     */
    public void initialiserNiveau2(){
        modelePlateau.clearPieces();
        setContours();
        //piece principale
        try {
            modelePlateau.posePiece(1,3,new boolean[][]{{true},{true}},0,0,"horizontal",Color.RED);

            //autres pieces
            modelePlateau.posePiece(2,1,new boolean[][]{{true},{true}},0,0,"horizontal");
            modelePlateau.posePiece(2,2,new boolean[][]{{true},{true},{true}},0,0,"horizontal");
            modelePlateau.posePiece(2,5,new boolean[][]{{true},{true}},0,0,"horizontal");

            modelePlateau.posePiece(1,1,new boolean[][]{{true,true}},0,0,"vertical");
            modelePlateau.posePiece(1,4,new boolean[][]{{true,true}},0,0,"vertical");
            modelePlateau.posePiece(3,3,new boolean[][]{{true,true}},0,0,"vertical");
            modelePlateau.posePiece(5,1,new boolean[][]{{true,true}},0,0,"vertical");
            modelePlateau.posePiece(4,3,new boolean[][]{{true,true,true}},0,0,"vertical");
            modelePlateau.posePiece(6,1,new boolean[][]{{true,true,true}},0,0,"vertical");

        } catch (mvc.ExceptionsDuProjet.exceptionPieceHorsPlateau | exceptionChevauchementDePiece exceptionPieceHorsPlateau) {
            exceptionPieceHorsPlateau.printStackTrace();
        }


    }

    /**
     * Suppression de toutes les pieces du plateau (pour commencer une nouvelle partie)
     * On remet les contours + on crée les pieces (piece principale de couleur rouge, autres pieces couleur aleat)
     * avec leur direction respective
     */
    public void initialiserNiveau3() {
        modelePlateau.clearPieces();
        setContours();

        try {
            //piece principale
            modelePlateau.posePiece(1, 3, new boolean[][]{{true}, {true}}, 0, 0, "horizontal", Color.RED);

            //autres pieces
            modelePlateau.posePiece(2,1,new boolean[][]{{true},{true}},0,0,"horizontal");
            modelePlateau.posePiece(1,2,new boolean[][]{{true},{true}},0,0,"horizontal");
            modelePlateau.posePiece(3,2,new boolean[][]{{true},{true}},0,0,"horizontal");
            modelePlateau.posePiece(4,5,new boolean[][]{{true},{true}},0,0,"horizontal");
            modelePlateau.posePiece(5,4,new boolean[][]{{true},{true}},0,0,"horizontal");
            modelePlateau.posePiece(4,6,new boolean[][]{{true},{true},{true}},0,0,"horizontal");

            modelePlateau.posePiece(3,3,new boolean[][]{{true,true}},0,0,"vertical");
            modelePlateau.posePiece(3,5,new boolean[][]{{true,true}},0,0,"vertical");
            modelePlateau.posePiece(4,3,new boolean[][]{{true,true}},0,0,"vertical");
            modelePlateau.posePiece(6,2,new boolean[][]{{true,true}},0,0,"vertical");
            modelePlateau.posePiece(1,4,new boolean[][]{{true,true,true}},0,0,"vertical");

        } catch (mvc.ExceptionsDuProjet.exceptionPieceHorsPlateau | exceptionChevauchementDePiece exceptionPieceHorsPlateau) {
            exceptionPieceHorsPlateau.printStackTrace();
        }

    }

    //endregion


}
