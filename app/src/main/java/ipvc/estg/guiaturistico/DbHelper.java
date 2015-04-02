package ipvc.estg.guiaturistico;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tiago on 25/03/2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "categorias.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Contrato.SQL_CREATE_ENTRIES);
        db.execSQL(Contrato.SQL_CREATE_ENTRIES2);

        db.execSQL("insert into categoria values (1, 'Monumento');");
        db.execSQL("insert into categoria values (2, 'Cultura');");
        db.execSQL("insert into categoria values (3, 'Gastronomia');");
        db.execSQL("insert into categoria values (4, 'Alojamento');");
        db.execSQL("insert into categoria values (5, 'AgendaCultural');");
        db.execSQL("insert into categoria values (6, 'Praia');");
        db.execSQL("insert into categoria values (7, 'DesportoLazer');");
        db.execSQL("insert into categoria values (8, 'EspaçoNoturno');");
        db.execSQL("insert into categoria values (9, 'Outro');");

        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria)  values (1,'Museu do Traje','Praça da República 4900-520 Viana do Castelo Portugal',258809306,'wwww','sem descricao','museutraje@cm-viana-castelo.pt','sem imagem','41.6935222','-8.828055277777777',1);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria)  values (2,'Museu de Artes Decorativas','Largo de S. Domingos 4900-330 Viana do Castelo Portugal',258809305,'Sem site','O Museu de Artes Decorativas está instalado num solar urbano situado no largo de São Domingos, onde também fica a Igreja do convento da mesma evocação, mandado construir pelo Santo Frei Bartolomeu dos Mártires','museu.a.a@cm-viana-castelo.pt','sem imagem','41.6916833','-8.833916666666667',1);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria)  values (3,'Museu de Carros de Cavalo','Sta. Leocádia de Geraz do Lima (Quinta da Bouça) Viana do Castelo Portugal',258731162,'Sem email','Constitui-se em um museu temático dedicado ao transporte de tração animal, em particular por carruagens.','http://www.cm-viana-castelo.pt/pt/outros-espacos-museologicos','sem imagem','41.702956','-8.660978',1);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria)  values (4,'Hospital Velho','Rua do Hospital Velho, Viana do Castelo Portugal',258822620,'Sem email','Antiga pousada de acolhimento dos peregrinos de Santiago, fundada por João Paes “o velho” em 1468 e restaurada no século XVI','sem site','sem imagem','41.692140','-8.827888',1);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria)  values (5,'Casa dos Costa Barros','Rua De São Pedro 22-28 4900-538 Santa Maria Maior ,Viana do Castelo Portugal',258823705,'Sem email','Casa senhorial da época dos descobrimentos onde se destaca a janela monumental central de inspiração renascentista com motivos decorativos “manuelinos” e “platerescos”','http://www.solaresdeportugal.com/','sem imagem','41.692996','-8.826597',1);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria)  values (6,'Estatua de Viana','Alameda 5 de Outubro 4900 Viana do Castelo Portugal',null,'Sem email','Mandado construir em 1774 pelo Conde da Bobadela, José António Freire de Andrade, Governador de Armas da província do Minho é, paralelamente ao Templo-Monumento de Santa Luzia, um dos ex-libris da cidade','sem site','sem imagem','41.692731','-8.825776',1);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria)  values (7,'Ponte Eiffel','Av. Dom Afonso III, Viana do Castelo Portugal',null,'Sem email','Inaugurada em 30 de Junho de 1878, em plena época da arquitetura do ferro, sob o risco e os cálculos da prestigiada Casa Eiffel, a ponte metálica sobre o rio Lima veio não só permitir o tráfego ferroviário, como também substituir a velha ponte de madeira que ligava o terreiro de São Bento em Viana à margem esquerda do rio Lima (Darque). Com quinhentos e sessenta e três metros de cumprimento e seis de largura, foram necessários mais de 2.000.000 quilos de ferro para a construção dos tabuleiros que assentam em nove pilares em cantaria de granito, cujas fundações chegam a atingir os 22 metros.','sem site','sem imagem','41.692401','-8.817761',1);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria)  values (8,'Igreja da Caridade,Convento de Sant’Ana','Rua Bombeiros, Viana do Castelo,Portugal',null,'Sem email','Igreja do antigo Convento de Santa Ana, de freiras beneditinas, mandado edificar pela nobreza local com o apoio da Câmara, para albergar as filhas dos nobres vianenses que eventualmente não casassem. O convento primitivo, de raiz gótica, foi obra de Pero Galego, morador em Caminha, onde nos alvores do século XVI dirigiu a segunda fase das obras na igreja Matriz','sem site','sem imagem','41.694984','-8.829833',1);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria)  values (9,'Teatro Municipal Sá de Miranda','Rua de Sá de Miranda 4900-533 Viana do Castelo,Portugal',null,'Sem email','Teatro “Italiano” dos finais do século XIX, segundo plano do arquiteto João Marques Sardinha. É um edifício sóbrio, com alguns elementos neoclássicos, onde se destaca o teto abobadado com uma belíssima pintura a fresco da autoria de João Baptista Rio. Possui ainda o pano de boca original, idealizado pelo cenógrafo Italiano Manini e executado por Hercole Lambertini. Este Teatro, recentemente restaurado, é sem dúvida o principal espaço cultural da cidade','sem site','sem imagem','41.695579','-8.828942',1);");
       //db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria)  values (10,'Basílica de Santa Luzia','Rua de Sá de Miranda 4900-533 Viana do Castelo,Portugal',null,'Sem email','O templo do Sagrado Coração de Jesus edificado no esporão poente da montanha de Santa Luzia, de onde domina e “abençoa” a cidade de Viana do Castelo, é sem dúvida um dos monumentos mais conhecidos e mais emblemáticos do País.','sem imagem','41.701008','-8.834674',1);");


   //     db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria)  values (11,'Casa de João o Velho ou dos Arcos','Largo Instituto Histórico do Minho, à Rua do Poço Viana do Castelo Portugal',null,'Sem email','A Casa dos Arcos ou de João Velho, implantada no largo da igreja matriz de Viana, é um dos poucos exemplares de arquitectura civil gótica do género que subsiste em Portugal','http://www.cm-viana-castelo.pt/pt/outros-espacos-museologicos','sem imagem','41.6934778','-8.827266666666667',2);");
   //     db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria)  values (12,'Citânia de Santa Luzia','Largo Instituto Histórico do Minho, à Rua do Poço Viana do Castelo Portugal',null,'Sem email','A Casa dos Arcos ou de João Velho, implantada no largo da igreja matriz de Viana, é um dos poucos exemplares de arquitectura civil gótica do género que subsiste em Portugal','http://www.cm-viana-castelo.pt/pt/outros-espacos-museologicos','sem imagem','41.6934778','-8.827266666666667',2);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(Contrato.SQL_DELETE_ENTRIES_categoria);
        db.execSQL(Contrato.SQL_DELETE_ENTRIES_pontos);
        onCreate(db);

    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


}
