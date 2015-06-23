package ipvc.estg.guiaturistico;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by tiago on 25/03/2015.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 20;
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

        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (1,'Museu do Traje','Praça da República 4900-520 Viana do Castelo Portugal',258809306,'wwww','sem descricao','museutraje@cm-viana-castelo.pt','museudotraje','41.6935222','-8.828055277777777',1,1);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (2,'Museu de Artes Decorativas','Largo de S. Domingos 4900-330 Viana do Castelo Portugal',258809305,'Sem site','O Museu de Artes Decorativas está instalado num solar urbano situado no largo de São Domingos, onde também fica a Igreja do convento da mesma evocação, mandado construir pelo Santo Frei Bartolomeu dos Mártires','museu.a.a@cm-viana-castelo.pt','museuartesdecorativas','41.6916833','-8.833916666666667',1,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (3,'Museu de Carros de Cavalo','Sta. Leocádia de Geraz do Lima (Quinta da Bouça) Viana do Castelo Portugal',258731162,'Sem email','Constitui-se em um museu temático dedicado ao transporte de tração animal, em particular por carruagens.','http://www.cm-viana-castelo.pt/pt/outros-espacos-museologicos','museucarrodecavalos','41.702956','-8.660978',1,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (4,'Hospital Velho','Rua do Hospital Velho, Viana do Castelo Portugal',258822620,'Sem email','Antiga pousada de acolhimento dos peregrinos de Santiago, fundada por João Paes “o velho” em 1468 e restaurada no século XVI','sem site','hospitaldevianadocastelo','41.692140','-8.827888',1,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (5,'Casa dos Costa Barros','Rua De São Pedro 22-28 4900-538 Santa Maria Maior ,Viana do Castelo Portugal',258823705,'Sem email','Casa senhorial da época dos descobrimentos onde se destaca a janela monumental central de inspiração renascentista com motivos decorativos “manuelinos” e “platerescos”','http://www.solaresdeportugal.com/','casacostabarros','41.692996','-8.826597',1,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (6,'Estatua de Viana','Alameda 5 de Outubro 4900 Viana do Castelo Portugal',null,'Sem email','Mandado construir em 1774 pelo Conde da Bobadela, José António Freire de Andrade, Governador de Armas da província do Minho é, paralelamente ao Templo-Monumento de Santa Luzia, um dos ex-libris da cidade','sem site','estatuavianadocastelo','41.692731','-8.825776',1,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (7,'Ponte Eiffel','Av. Dom Afonso III, Viana do Castelo Portugal',null,'Sem email','Inaugurada em 30 de Junho de 1878, em plena época da arquitetura do ferro, sob o risco e os cálculos da prestigiada Casa Eiffel, a ponte metálica sobre o rio Lima veio não só permitir o tráfego ferroviário, como também substituir a velha ponte de madeira que ligava o terreiro de São Bento em Viana à margem esquerda do rio Lima (Darque). Com quinhentos e sessenta e três metros de cumprimento e seis de largura, foram necessários mais de 2.000.000 quilos de ferro para a construção dos tabuleiros que assentam em nove pilares em cantaria de granito, cujas fundações chegam a atingir os 22 metros.','sem site','ponteeiffel','41.692401','-8.817761',1,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (8,'Igreja da Caridade,Convento de Sant’Ana','Rua Bombeiros, Viana do Castelo,Portugal',null,'Sem email','Igreja do antigo Convento de Santa Ana, de freiras beneditinas, mandado edificar pela nobreza local com o apoio da Câmara, para albergar as filhas dos nobres vianenses que eventualmente não casassem. O convento primitivo, de raiz gótica, foi obra de Pero Galego, morador em Caminha, onde nos alvores do século XVI dirigiu a segunda fase das obras na igreja Matriz','sem site','igrejadacaridadeconventodesantasna','41.694984','-8.829833',1,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (9,'Teatro Municipal Sá de Miranda','Rua de Sá de Miranda 4900-533 Viana do Castelo,Portugal',null,'Sem email','Teatro “Italiano” dos finais do século XIX, segundo plano do arquiteto João Marques Sardinha. É um edifício sóbrio, com alguns elementos neoclássicos, onde se destaca o teto abobadado com uma belíssima pintura a fresco da autoria de João Baptista Rio. Possui ainda o pano de boca original, idealizado pelo cenógrafo Italiano Manini e executado por Hercole Lambertini. Este Teatro, recentemente restaurado, é sem dúvida o principal espaço cultural da cidade','sem site','teatromunicipalsamiranda','41.695579','-8.828942',1,0);");
       //db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria)  values (10,'Basílica de Santa Luzia','Rua de Sá de Miranda 4900-533 Viana do Castelo,Portugal',null,'Sem email','O templo do Sagrado Coração de Jesus edificado no esporão poente da montanha de Santa Luzia, de onde domina e “abençoa” a cidade de Viana do Castelo, é sem dúvida um dos monumentos mais conhecidos e mais emblemáticos do País.','sem imagem','41.701008','-8.834674',1);");


   //     db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria)  values (11,'Casa de João o Velho ou dos Arcos','Largo Instituto Histórico do Minho, à Rua do Poço Viana do Castelo Portugal',null,'Sem email','A Casa dos Arcos ou de João Velho, implantada no largo da igreja matriz de Viana, é um dos poucos exemplares de arquitectura civil gótica do género que subsiste em Portugal','http://www.cm-viana-castelo.pt/pt/outros-espacos-museologicos','sem imagem','41.6934778','-8.827266666666667',2);");
   //     db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria)  values (12,'Citânia de Santa Luzia','Largo Instituto Histórico do Minho, à Rua do Poço Viana do Castelo Portugal',null,'Sem email','A Casa dos Arcos ou de João Velho, implantada no largo da igreja matriz de Viana, é um dos poucos exemplares de arquitectura civil gótica do género que subsiste em Portugal','http://www.cm-viana-castelo.pt/pt/outros-espacos-museologicos','sem imagem','41.6934778','-8.827266666666667',2);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (13,'O Navio-Hospital Gil Eannes','Navio Gil Eannes Doca Comercial 4900-405 Viana do Castelo Portugal',258809710,'Sem email','O navio-hospital Gil Eannes, construído em Viana do Castelo, em 1955, apoiou, durante décadas, a frota bacalhoeira portuguesa que atuava nos bancos da Terra Nova e Gronelândia. O projeto de reconversão transformou-o em Núcleo Museológico e Pousada da Juventude, proporcionando aos seus visitantes uma experiência inesquecível','fundacaogileannes.pt','naviogileannes','41.6906873','-8.8310161',2,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (14,'Biblioteca Municipal de Viana do Castelo','Alameda 5 de Outubro Viana do Castelo Portugal',null,'Sem email','Projeto da autoria do arquiteto Álvaro Siza Vieira.','null','bibliotecamunicipal','41.69159','-8.827023',2,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (15,'Biblioteca Municipal de Viana do Castelo','Alameda 5 de Outubro Viana do Castelo Portugal',null,'Sem email','Projeto da autoria do arquiteto Álvaro Siza Vieira.','null','bibliotecamunicipal','41.69159','-8.827023',2,0);");


        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (16,'Restaurante Adega DO Padrinho','Rua Gago Coutinho 162 4900-509 Viana do Castelo Portugal',258826954,'Sem email','Restaurante','null','restauranteadegadopadrinho','41.6931261','-8.8262547',3,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (17,'Restaurante Adega DO Padrinho2','Restaurante A Gruta, Rua Grande 87 – 89,Viana do Castelo,Portugal',963953121,'Sem email','Restaurante','null','restauranteadegadopadrinho','41.6924369','-8.8275097',3,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (18,'Cozinha do Ricardo','Rua Gago Coutinho 162 4900-509 Viana do Castelo Portugal',258826954,'Sem email','Restaurante','null','restaurantecozinhadoricardo','41.6909355','-8.8317691',3,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (19,'Restaurante Colombo','Rua de Monserrate, 219  Viana do Castelo Portugal',258820201,'Sem email','Restaurante','null','restaurantecolombo','41.694371','-8.84107',3,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (20,'Casa da Armas','Rua Gago Coutinho 162 4900-509 Viana do Castelo Portugal',258826954,'Sem email','Restaurante','null','casaarmas','41.6931261','-8.8262547',3,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (21,'Mesa para 6','Rua Prior do Crato,46, Viana do Castelo Portugal',258109343,'Sem email','Restaurante','null','mesa6tapas','41.6922093','-8.8280608',3,0);");

        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (22,'Pousada Monte de Santa Luzia','Monte de Santa Luzia - Apartado 536,4901-909 Viana do Castelo Portugal',258800370,'recepcao.mluzia@pousadas.pt','Pousada','www.pousadas.pt','pousadadesantaluzia','41.703469','-8.835574',4,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (23,'Casa Melo Alvim Hotel','Avenida Conde da Carreira, 28, 4900-343 Viana do Castelo Portugal',258808200,'hotel@meloalvimhouse.com','Hotel ****','www.meloalvimhouse.com','casameloalvim','41.694455','-8.831387',4,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (24,'Hotel Axis Viana Business & SPA','Avenida Capitão Gaspar de Castro Viana do Castelo Portugal',258802000,'reservas@axisviana.com','Hotel ****','www.axishoteis.com','hotelaxisviana','41.702593','-8.819241',4,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (25,'Hotel Calatrava','Rua Manuel Fiuza Júnior, 157, Viana do Castelo Portugal',258828911,'hotelcalatrava@gmail.com','Hotel **','null','hotelcalatrava','41.697303','-8.82393',4,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (26,'Pousada da Juventude Viana do Castelo','Rua de Limia,46, Viana do Castelo Portugal',258838458,'Sem email','Pousada','vianacastelo@movijovem.pt','pousadajuventude','41.6792011','-8.818432448',4,0);");


        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (27,'Dia 2 de Abril','null',null,'Sem email','Dia Mundial do Livro Infantil','null','agenda','41.69159','-8.827023',5,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (28,'Dia 11 todos os meses','null',null,'Sem email','Feira de artesanato mensal','null','agenda','41.693657','-8.828301',5,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (29,'Dia 4 de Julho e todos os meses','null',null,'Sem email','Feira de Antiguidades e Velharias','null','agenda','41.693657','-8.828301',5,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (30,'Dias 18, 19, 20 e 21 Junho','null',null,'Sem email','Feira Medieval de Viana do Castelo','null','agenda','41.693657','-8.828301',5,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (31,'Dias 20 a 23 de Agosto','null',null,'Sem email','- Romaria de Nª Srª d’Agonia','null','agenda','41.693657','-8.828301',5,0);");

        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (32,'Praia Afife','null',null,'Sem email','Praia com agua de qualidade maxima','null','praiaafife','41.78079','-8.87150',6,0);");//
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (33,'Praia Amorosa','null',null,'Sem email','Praia com agua de qualidade maxima','null','praiaamorosa','41.64693','-8.82509',6,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (34,'Praia Cabedelo','null',null,'Sem email','Praia com agua de qualidade maxima','null','praiacabedelo','41.68003','-8.83546',6,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (35,'Praia Ínsua','null',null,'Sem email','Praia com agua de qualidade maxima','null','praiainsua','41.78539','-8.87133',6,0);");


        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (36,'Surf Clube de Viana','Praia do Cabedelo 4935-161 Viana do Castelo',258332043,'info@surfingviana.com','Escola de surf, bodyboard, longboard e paddle','www.surfingviana.com','surfclubeviana','41.6784167','-8.82733611111111',7,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (37,'Cavaleiros do Mar','Edifício do Parque – Estrada da Papanata 204, Loja B | 4900-476 Viana do Castelo',926163190,'info@cavaleirosdomar.com','CANOAGEM / KAYAK','www.cavaleirosdomar.com','cavaleirosdomar','41.6974444','-8.8208',7,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (38,'Viana Remadores do Lima','Rua Doutor Adriano Magalhães, Argaçosa - Meadela, Viana do Castelo',258842374,'vianaremadoresdolima@gmail.com','Remo','www.vianaremadoresdolima.pt','categoria','41.696934','-8.810629',7,0);");///////////////////////////////
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (39,'Beach Bowling - Centro de Lazer e Divertimentos do Vale do Lima, Lda','Parque empresarial da Praia Norte, Lt 3/4 | 4900-350 Viana do Castelo',258813413,'bowling@beachbowling-viana.com','Bowling','sem site','beachbowling','41.6912366','-8.8467347',7,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (40,'Bicicletas Lavarinhas','Estrada da Papanata, 189 | 4900-470 Viana do Castelo',258811900,'info@lavarinhas.com','BTT, Passeios de Ciclismo','www.lavarinhas.com','bicletaslavarinhas','41.698689','-8.82008',7,0);");

        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (41,'Aquário Bar','Praia do Cabedelo - Darque 4935 Viana do Castelo',258323780,'sem email','Bar','sem site','aquariobar','41.680797','-8.828372',8,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (42,'Bar - Centro Histórico','Rua dos Fornos, 35 4900 Viana do Castelo',null,'sem email','Bar','sem site','centrohistorico','41.692789','-8.8280447',8,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (43,'Bar Discoteca Prosak Viana','Av. de Cabo Verde, Praia Norte 4900 Viana do Castelo',null,'sem email','Discoteca/bar','sem site','prosak','41.6927002','-8.8472519',8,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (44,'Discoteca Fiori Klub','Rua de Monserrate, nº 384  4900 Viana do Castelo ',960283535,'sem email','Discoteca','sem site','fiori','41.6955886','-8.8426794',8,0);");


        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (45,'Centro Hospitalar Alto Minho Epe','Estrada de Santa Luzia',258802100,'sem email','Hospital','sem site','hospitalsantaluzia','41.6968676','-8.8330213',9,0);");
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (46,'Estação de Viana do Castelo','sem morada',null,'sem email','Estação de Comboios','sem site','estacaocombois','41.6948863','-8.831216',9,0);");


        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (47,'Teste','sem morada',919643063,'sem email','fala que eu gosto de te ouvir','sem site','bandeirafranca','41.6940098','-8.8322639',9,0);");

        //inserir para testar a bussula
        db.execSQL("insert into pontos (_id, Nome, Morada, Telefone, Email, Descricao, Site, Imagem, Latitude, Longitude, Id_categoria,Checked)  values (48,'TesteBussula','frente ao nosso quarto',919643063,'sem email','fala que eu gosto de te ouvir','sem site','bandeirainglaterra','41.693822','-8.832688',9,0);");










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
