package com.radiozkousky.data

object QuestionBankOFL {

    val allQuestions: List<Question> = buildList {
        // ============================================================
        // a) Radiokomunikační předpisy (35 otázek)
        // ============================================================
        add(Question(1001, Category.PREDPISY,
            "Zajištění účelného využívání rádiových kmitočtů a správu rádiového spektra vykonává",
            "Český telekomunikační úřad"))
        add(Question(1002, Category.PREDPISY,
            "Radiokomunikační službou je komunikační činnost, která spočívá v přenosu, vysílání nebo příjmu signálů prostřednictvím",
            "rádiových vln"))
        add(Question(1003, Category.PREDPISY,
            "Individuální oprávnění k využívání rádiových kmitočtů uděluje",
            "Český telekomunikační úřad"))
        add(Question(1004, Category.PREDPISY,
            "Držitel individuálního oprávnění k využívání rádiových kmitočtů je povinen platit za využívání rádiových kmitočtů",
            "poplatek dle nařízení vlády o poplatcích"))
        add(Question(1005, Category.PREDPISY,
            "Rádiovým spektrem se rozumí elektromagnetické vlny o kmitočtu (frekvenci)",
            "nižším než 3000 GHz"))
        add(Question(1006, Category.PREDPISY,
            "Rádiové kmitočty z pásma 160 MHz spadají do pásma označovaného jako",
            "VHF"))
        add(Question(1007, Category.PREDPISY,
            "Státní kontrolu elektronických komunikací vykonává",
            "Český telekomunikační úřad"))
        add(Question(1008, Category.PREDPISY,
            "Fyzická osoba vykonávající obsluhu vysílacího rádiového zařízení bez platného průkazu odborné způsobilosti se dopustila",
            "přestupku"))
        add(Question(1009, Category.PREDPISY,
            "Za obsluhu vysílacího rádiového zařízení bez platného průkazu odborné způsobilosti lze uložit fyzické osobě pokutu do výše",
            "100 000 Kč"))
        add(Question(1010, Category.PREDPISY,
            "V mezinárodní volací značce České republiky tvoří první dvě písmena (prefix) vždy dvojice písmen",
            "OK nebo OL"))
        add(Question(1011, Category.PREDPISY,
            "Mezinárodní volací značka letadlové stanice u letounů zapsaných v leteckém rejstříku ČR je",
            "OK a další tři písmena"))
        add(Question(1012, Category.PREDPISY,
            "Falešné volací značky a falešné signály",
            "se nesmí používat"))
        add(Question(1013, Category.PREDPISY,
            "Pohyblivá stanice letecké pohyblivé služby je",
            "letadlová stanice"))
        add(Question(1014, Category.PREDPISY,
            "Pevná služba je",
            "radiokomunikační služba mezi stanovenými pevnými body"))
        add(Question(1015, Category.PREDPISY,
            "Nejvyšší prioritu a absolutní přednost má zpráva",
            "tísňová"))
        add(Question(1016, Category.PREDPISY,
            "Pořadí zpráv dle důležitosti je následující:",
            "tísňové zprávy, pilnostní zprávy, zprávy o radiovém zaměřování, zprávy pro zajištění bezpečnosti letů, meteorologické zprávy, zprávy o pravidelnosti letů"))
        add(Question(1017, Category.PREDPISY,
            "Tísňové volání a tísňová zpráva se vysílají jen na rozkaz",
            "velitele nebo osoby odpovědné za loď nebo letadlo"))
        add(Question(1018, Category.PREDPISY,
            "Mezinárodní tísňový kmitočet v radiotelefonii v letecké pohyblivé službě je",
            "121,5 MHz"))
        add(Question(1019, Category.PREDPISY,
            "Služba u letadlové nebo lodní stanice podléhá nejvyšší pravomoci",
            "velitele nebo osoby, která je odpovědná za letadlo nebo loď"))
        add(Question(1020, Category.PREDPISY,
            "Doba platnosti průkazů radiotelefonistů pro leteckou a námořní pohyblivou službu je stanovena na",
            "10 let při prvním vydání průkazu"))
        add(Question(1021, Category.PREDPISY,
            "O prodloužení doby platnosti průkazu odborné způsobilosti se žádá",
            "písemně, minimálně jeden měsíc před koncem platnosti průkazu"))
        add(Question(1022, Category.PREDPISY,
            "Při žádosti o prodloužení platnosti průkazu je třeba také",
            "uhradit příslušný správní poplatek a doložit praxi v obsluze rádiových stanic"))
        add(Question(1023, Category.PREDPISY,
            "V případě, že doba platnosti průkazu již uplynula, může držitel průkazu žádat o nový průkaz",
            "v období do jednoho roku ode dne pozbytí platnosti průkazu"))
        add(Question(1024, Category.PREDPISY,
            "Doba platnosti průkazů radiotelefonistů námořní a letecké pohyblivé služby se na základě žádosti držitele prodlužuje o",
            "5 let"))
        add(Question(1025, Category.PREDPISY,
            "První znak nebo první dva znaky mezinárodní volací značky označují",
            "státní příslušnost stanice"))
        add(Question(1026, Category.PREDPISY,
            "Stejná volací značka",
            "nemůže být přidělena dvěma nebo více provozovatelům stanic"))
        add(Question(1027, Category.PREDPISY,
            "Inspekční orgány zemí, které provedou kontrolu vybavení radiové stanice",
            "mohou vyžadovat předložení průkazu operátora"))
        add(Question(1028, Category.PREDPISY,
            "Mezinárodní telekomunikační unie (ITU) je",
            "specializovanou organizací Organizace spojených národů pro oblast telekomunikací"))
        add(Question(1029, Category.PREDPISY,
            "Mezinárodní organizací CEPT se rozumí",
            "Evropská konference poštovních a telekomunikačních správ"))
        add(Question(1030, Category.PREDPISY,
            "Q-kódem se rozumí",
            "kódová skupina tří písmen začínající vždy písmenem Q, která má určitý konkrétní, mezinárodně dohodnutý význam"))
        add(Question(1031, Category.PREDPISY,
            "Volací značky se přidělují tak, aby nemohly být zaměněny",
            "s tísňovými, pilnostními a bezpečnostními signály nebo s kódovými zkratkami Q-kódu"))
        add(Question(1032, Category.PREDPISY,
            "Volací značkou je",
            "každé poznávací označení stanice přidělené podle Radiokomunikačního řádu, které umožňuje zjištění její totožnosti během vysílání"))
        add(Question(1033, Category.PREDPISY,
            "Telekomunikační tajemství se týká",
            "všech osob, které znají obsah rádiových zpráv nebo se dověděly o jejich existenci či o zprávě, která byla doručena prostřednictvím radiokomunikační služby"))
        add(Question(1034, Category.PREDPISY,
            "Každý, kdo se dozví informace o skutečnostech, které jsou předmětem telekomunikačního tajemství,",
            "je povinen zachovávat o nich mlčenlivost"))
        add(Question(1035, Category.PREDPISY,
            "Předmětem telekomunikačního tajemství je zejména",
            "obsah zpráv přepravovaných nebo jinak zprostředkovaných telekomunikačními zařízeními a sítěmi s výjimkou zpráv určených veřejnosti"))

        // ============================================================
        // b) Radiokomunikační provoz (83 otázek)
        // ============================================================
        add(Question(1036, Category.PROVOZ,
            "Letecká pohyblivá služba je",
            "pohyblivá služba mezi leteckými stanicemi a letadlovými stanicemi nebo mezi letadlovými stanicemi navzájem"))
        add(Question(1037, Category.PROVOZ,
            "Letecká pevná služba (AFS dle L10) je",
            "telekomunikační služba mezi stanovenými pevnými body"))
        add(Question(1038, Category.PROVOZ,
            "Letecká rozhlasová služba (dle L10) je",
            "služba určená k vysílání informací týkajících se leteckého provozu"))
        add(Question(1039, Category.PROVOZ,
            "Pozemní stanice letecké pohyblivé služby je",
            "letecká stanice"))
        add(Question(1040, Category.PROVOZ,
            "Vysílání pokusných signálů nesmí trvat déle než",
            "10 vteřin"))
        add(Question(1041, Category.PROVOZ,
            "Při zkušebním vysílání dokonalá čitelnost je uváděna stupněm",
            "5 (Dokonale čitelné)"))
        add(Question(1042, Category.PROVOZ,
            "Zkušební vysílání musí obsahovat",
            "volací značku volané stanice, volací značku letadla, slova \u201EZKOUŠKA RÁDIA\u201C, používaný kmitočet"))
        add(Question(1043, Category.PROVOZ,
            "Rychlost hovoru při radiotelefonním spojení nemá převyšovat",
            "100 slov za minutu"))
        add(Question(1044, Category.PROVOZ,
            "Radiotelefonní spojení letadlo – země by se mělo provádět",
            "všeobecně v jazyce ve kterém komunikuje pozemní stanice"))
        add(Question(1045, Category.PROVOZ,
            "Zprávy musí být vysílány",
            "v otevřené řeči a ve schválených frázích"))
        add(Question(1046, Category.PROVOZ,
            "Při vysílání",
            "zachováváme stále stejnou výši hlasu ve všech fázích hovoru"))
        add(Question(1047, Category.PROVOZ,
            "Pořadí zpráv dopravovaných leteckou pohyblivou službou je",
            "tísňové, pilnostní, o rádiovém zaměřování, pro zajištění bezpečnosti letů, meteorologické, o pravidelnosti letů"))
        add(Question(1048, Category.PROVOZ,
            "MAYDAY je radiotelefonní signál",
            "tísňové zprávy"))
        add(Question(1049, Category.PROVOZ,
            "PAN PAN je radiotelefonní signál",
            "pilnostní zprávy"))
        add(Question(1050, Category.PROVOZ,
            "Tísňová korespondence",
            "má přednost před všemi ostatními druhy spojení"))
        add(Question(1051, Category.PROVOZ,
            "Pilnostní zpráva",
            "má přednost před všemi ostatními druhy spojení vyjma tísňového provozu"))
        add(Question(1052, Category.PROVOZ,
            "Tísňový a pilnostní provoz musí být zahájen na kmitočtu",
            "který je v dané chvíli používán"))
        add(Question(1053, Category.PROVOZ,
            "Po volání letecké stanice, na které tato stanice neodpoví, je letadlová stanice povinna",
            "vyčkat nejméně 10 sekund, než provede další volání"))
        add(Question(1054, Category.PROVOZ,
            "Jména, zkratky a slova, jejichž výslovnost může vyvolat pochybnost, se v radiotelefonním provozu musí",
            "hláskovat"))
        add(Question(1055, Category.PROVOZ,
            "Letadlová stanice udržuje rádiové spojení s řídící stanicí",
            "vždy, je-li to možné"))
        add(Question(1056, Category.PROVOZ,
            "Letadlová stanice potvrzuje příjem důležitých zpráv řízení letového provozu nebo jejich částí",
            "jejich opakováním a připojením vlastní volací značky"))
        add(Question(1057, Category.PROVOZ,
            "Za letu musí letadlová stanice",
            "udržovat stálou poslechovou hlídku"))
        add(Question(1058, Category.PROVOZ,
            "Letadlo přechází z jednoho rádiového kmitočtu na druhý",
            "z příkazu letecké stanice v souladu s dohodnutými postupy"))
        add(Question(1059, Category.PROVOZ,
            "Jestliže letadlová stanice není schopna navázat spojení s leteckou stanicí na určeném a jiném kmitočtu pro danou trať",
            "pokusí se navázat spojení s jiným letadlem nebo jinými leteckými stanicemi"))
        add(Question(1060, Category.PROVOZ,
            "Letadlo za letu",
            "nesmí měnit svou volací značku"))
        add(Question(1061, Category.PROVOZ,
            "Poznávací značka civilního letadla je složena",
            "ze značky státní příslušnosti a rejstříkové značky"))
        add(Question(1062, Category.PROVOZ,
            "Volací značku letadla lze tvořit",
            "radiotelefonním označením provozovatele letadla za kterým následuje označení (číslo) letu v souladu s předpisem ICAO"))
        add(Question(1063, Category.PROVOZ,
            "Volací značka letecké stanice je tvořena",
            "zeměpisným názvem její polohy a službou, která je k dispozici"))
        add(Question(1064, Category.PROVOZ,
            "Ve volací značce letecké stanice přibližovacího stanoviště může být název",
            "APPROACH"))
        add(Question(1065, Category.PROVOZ,
            "Ve volací značce letecké stanice pro neřízená letiště je název",
            "INFO nebo RADIO"))
        add(Question(1066, Category.PROVOZ,
            "Zkrácenou volací značku použije letadlová stanice",
            "pouze v případě, že byla tímto způsobem oslovena leteckou stanicí"))
        add(Question(1067, Category.PROVOZ,
            "Správné zkrácení volací značky OKABC je",
            "OBC"))
        add(Question(1068, Category.PROVOZ,
            "Fráze \u201Evysílám naslepo\u201C se použije",
            "při neúspěšném navázání spojení"))
        add(Question(1069, Category.PROVOZ,
            "Jestliže letadlová stanice není schopna navázat spojení s leteckou stanicí na určeném kmitočtu",
            "pokusí se o navázání spojení na jiném kmitočtu pro danou trať"))
        add(Question(1070, Category.PROVOZ,
            "Všechna čísla se vyslovují",
            "jednotlivě, s výjimkou čísel souvisejících s výškou nad hladinou moře, s výškou oblačnosti, dohlednosti a dráhové dohlednosti"))
        add(Question(1071, Category.PROVOZ,
            "Rádiové spojení může být ukončeno",
            "na pokyn letecké stanice"))
        add(Question(1072, Category.PROVOZ,
            "V radiotelefonním provozu v pásmech VKV se kmitočet 119,000 MHz vyjadřuje",
            "JEDNA JEDNA DEVĚT ČÁRKA NULA"))
        add(Question(1073, Category.PROVOZ,
            "V radiotelefonním provozu v pásmech VKV se kmitočet 121,500 MHz vyjadřuje",
            "JEDNA DVA JEDNA ČÁRKA PĚT"))
        add(Question(1074, Category.PROVOZ,
            "V radiotelefonním provozu v pásmech VKV se kmitočet 121,050 MHz vyjadřuje",
            "JEDNA DVA JEDNA ČÁRKA NULA PĚT NULA"))
        add(Question(1075, Category.PROVOZ,
            "V radiotelefonním provozu v pásmech VKV se vysílací kanál vyjadřuje",
            "čtyřmi nebo šesti číslicemi"))
        add(Question(1076, Category.PROVOZ,
            "V radiotelefonním provozu v pásmech VKV se kmitočet 118,055 MHz vyjadřuje",
            "JEDNA JEDNA OSUM ČÁRKA NULA PĚT PĚT"))
        add(Question(1077, Category.PROVOZ,
            "Hláskovací abeceda (Národní/Mezinárodní): A, E, I",
            "A – Adam / Alpha, E – Emil / Echo, I – Ivan / India"))
        add(Question(1078, Category.PROVOZ,
            "Hláskovací abeceda (Národní/Mezinárodní): B, F, J",
            "B – Božena / Bravo, F – František / Foxtrot, J – Josef / Juliett"))
        add(Question(1079, Category.PROVOZ,
            "Hláskovací abeceda (Národní/Mezinárodní): C, G, K",
            "C – Cyril / Charlie, G – Gustav / Golf, K – Karel / Kilo"))
        add(Question(1080, Category.PROVOZ,
            "Hláskovací abeceda (Národní/Mezinárodní): D, H, L",
            "D – David / Delta, H – Helena / Hotel, L – Ludvík / Lima"))
        add(Question(1081, Category.PROVOZ,
            "Hláskovací abeceda (Národní/Mezinárodní): M, Q, U",
            "M – Marie / Mike, Q – Quido / Quebec, U – Urban / Uniform"))
        add(Question(1082, Category.PROVOZ,
            "Hláskovací abeceda (Národní/Mezinárodní): N, R, V",
            "N – Norbert / November, R – Rudolf / Romeo, V – Václav / Victor"))
        add(Question(1083, Category.PROVOZ,
            "Hláskovací abeceda (Národní/Mezinárodní): O, S, W",
            "O – Otakar / Oscar, S – Svatopluk / Sierra, W – dvojité V / Whisky"))
        add(Question(1084, Category.PROVOZ,
            "Hláskovací abeceda (Národní/Mezinárodní): P, T, X",
            "P – Petr / Papa, T – Tomáš / Tango, X – Xaver / X-ray"))
        add(Question(1085, Category.PROVOZ,
            "Hláskovací abeceda (Národní/Mezinárodní): Y, Z",
            "Y – Ypsilon / Yankee, Z – Zuzana / Zulu"))
        add(Question(1086, Category.PROVOZ,
            "Správný Q-kód pro \u201Etlak indikující nadmořskou výšku, přepočtený na střední hladinu moře\u201C",
            "QNH"))
        add(Question(1087, Category.PROVOZ,
            "Správný Q-kód pro \u201Emagnetický kurs pro směr k zaměřovači\u201C",
            "QDM"))
        add(Question(1088, Category.PROVOZ,
            "Správný Q-kód pro \u201Ežádost o zaměření\u201C",
            "QDL"))
        add(Question(1089, Category.PROVOZ,
            "Správná zkratka pro \u201Evzletová a přistávací dráha\u201C",
            "RWY"))
        add(Question(1090, Category.PROVOZ,
            "Správná zkratka pro \u201Eautomatická informační služba koncové řízené oblasti\u201C",
            "ATIS"))
        add(Question(1091, Category.PROVOZ,
            "Správná zkratka pro \u201Eletová informační oblast\u201C",
            "FIR"))
        add(Question(1092, Category.PROVOZ,
            "Správná zkratka pro \u201Ekmitočet\u201C",
            "FREQ"))
        add(Question(1093, Category.PROVOZ,
            "Zkušební vysílání musí obsahovat (OFL)",
            "volací značku volané stanice, volací značku letadla, slova \u201EZKOUŠKA RÁDIA\u201C, používaný kmitočet"))
        add(Question(1094, Category.PROVOZ,
            "Kód QFE se vztahuje k",
            "tlaku vzduchu vztaženému k nadmořské výšce letiště"))
        add(Question(1095, Category.PROVOZ,
            "V případě, že letadlo stojí na ploše letiště a hodnota tlaku je nastavena na QFE, bude výškoměr ukazovat",
            "nulu"))
        add(Question(1096, Category.PROVOZ,
            "Odchylky od spisovné češtiny při vysílání čísel jsou",
            "u číslic: 2, 4, 7, 8"))
        add(Question(1097, Category.PROVOZ,
            "Nastavení výškoměru na hodnotu 1000 hPa se vysílá takto:",
            "jeden tisíc"))
        add(Question(1098, Category.PROVOZ,
            "Nastavení výškoměru na hodnotu 1009 hPa se vysílá takto:",
            "QNH JEDNA NULA NULA DEVĚT"))
        add(Question(1099, Category.PROVOZ,
            "Letová hladina FL 300 se vysílá takto:",
            "letová hladina TŘI STA"))
        add(Question(1100, Category.PROVOZ,
            "Letová hladina FL 180 se vysílá takto:",
            "letová hladina JEDEN OSUM NULA"))
        add(Question(1101, Category.PROVOZ,
            "Potvrzení příjmu leteckou stanicí musí obsahovat",
            "volací značku letadla za kterou následuje, je-li to nutné, volací značka letecké stanice"))
        add(Question(1102, Category.PROVOZ,
            "Rádiové spojení může být ukončeno (OFL)",
            "na pokyn letecké stanice"))
        add(Question(1103, Category.PROVOZ,
            "Volací znak INFORMATION je přiřazen (letištím)",
            "letištím, poskytujícím službu AFIS"))
        add(Question(1104, Category.PROVOZ,
            "Volací znak INFORMATION je přiřazen (střediskům)",
            "letovým informačním střediskům např. FIC Praha"))
        add(Question(1105, Category.PROVOZ,
            "Fráze \u201Ečekejte\u201C znamená",
            "čekejte, zavolám Vás"))
        add(Question(1106, Category.PROVOZ,
            "Fráze \u201Ekonec\u201C znamená",
            "rozhovor je ukončen a neočekává se odpověď"))
        add(Question(1107, Category.PROVOZ,
            "Fráze \u201Eprovedu\u201C znamená",
            "rozumím Vaší zprávě a budu podle ní postupovat"))
        add(Question(1108, Category.PROVOZ,
            "Fráze \u201Epotvrďte\u201C znamená",
            "potvrďte mi, že jste zprávu přijal a rozuměl jí"))
        add(Question(1109, Category.PROVOZ,
            "Fráze \u201Epříjem\u201C znamená",
            "moje vysílání skončilo, očekávám Vaši odpověď"))
        add(Question(1110, Category.PROVOZ,
            "Fráze \u201Erozumím\u201C znamená",
            "přijal jsem vše z Vašeho posledního vysílání"))
        add(Question(1111, Category.PROVOZ,
            "Fráze \u201Eschváleno\u201C znamená",
            "povolení pro požadovaný úkon je schváleno"))
        add(Question(1112, Category.PROVOZ,
            "Fráze \u201Enegativ\u201C znamená",
            "povolení není potvrzeno – to není správné – ne"))
        add(Question(1113, Category.PROVOZ,
            "Fráze \u201Eopakujte\u201C znamená",
            "opakujte vše nebo následující část Vašeho posledního vysílání"))
        add(Question(1114, Category.PROVOZ,
            "Fráze \u201Esprávně\u201C znamená",
            "to je správné"))
        add(Question(1115, Category.PROVOZ,
            "Fráze \u201Evzlet schválen\u201C",
            "není přípustná"))
        add(Question(1116, Category.PROVOZ,
            "Simplexní provoz je způsob provozu rádiové stanice, který",
            "využívá jeden nebo dva kmitočty a umožňuje přenos zpráv střídavě v obou směrech, přičemž během vysílání zpráv není možný současný příjem zpráv"))
        add(Question(1117, Category.PROVOZ,
            "Duplexní provoz je způsob provozu rádiové stanice, který",
            "umožňuje současný přenos zpráv oběma směry a vyžaduje současné využívání dvou kmitočtů (kanálů)"))
        add(Question(1118, Category.PROVOZ,
            "Při frázi MEZERA MEZERA pro dvě letadlové stanice, pořadí pro odpovědi volaných stanic je následující:",
            "odpovídá druhá oslovená stanice"))

        // ============================================================
        // c) Elektrotechnika a radiotechnika (31 otázek)
        // ============================================================
        add(Question(1119, Category.ELEKTRO,
            "Paralelně řazené akumulátory",
            "umožňují dodávat větší proud"))
        add(Question(1120, Category.ELEKTRO,
            "Sériově řazené akumulátory",
            "se zapojují pro zvýšení dodávaného napětí"))
        add(Question(1121, Category.ELEKTRO,
            "Jmenovité napětí článku olověného akumulátoru je",
            "2 V"))
        add(Question(1122, Category.ELEKTRO,
            "Jmenovité napětí článku alkalického akumulátoru je",
            "1,2 V"))
        add(Question(1123, Category.ELEKTRO,
            "Jmenovité napětí suchého galvanického článku je",
            "1,5 V"))
        add(Question(1124, Category.ELEKTRO,
            "Suché galvanické články",
            "nelze dobíjet"))
        add(Question(1125, Category.ELEKTRO,
            "Antény dělíme podle směru vysílání nebo příjmu na",
            "směrové a všesměrové"))
        add(Question(1126, Category.ELEKTRO,
            "Všesměrová anténa má vyzařovací charakteristiku",
            "kruhovou"))
        add(Question(1127, Category.ELEKTRO,
            "Všesměrová anténa musí přijímat nebo vysílat stejně všemi směry",
            "v horizontální rovině"))
        add(Question(1128, Category.ELEKTRO,
            "Půlvlnný dipól",
            "může být směrová i všesměrová anténa, záleží na jeho orientaci k zemskému povrchu"))
        add(Question(1129, Category.ELEKTRO,
            "Půlvlnný dipól umístěný rovnoběžně se zemským povrchem",
            "má v horizontální rovině osmičkovou vyzařovací charakteristiku"))
        add(Question(1130, Category.ELEKTRO,
            "Vztah mezi délkou vlny (\u03BB) a kmitočtem (f), když c je rychlost světla, je",
            "f = c / \u03BB"))
        add(Question(1131, Category.ELEKTRO,
            "Který typ modulace mění kmitočet vysokofrekvenčního signálu v závislosti na přiváděném modulačním napětí",
            "kmitočtová modulace"))
        add(Question(1132, Category.ELEKTRO,
            "Ampérmetr a voltmetr se při měření zařazují",
            "ampérmetr do série se spotřebičem, voltmetr paralelně ke spotřebiči"))
        add(Question(1133, Category.ELEKTRO,
            "V suchém, bezprašném prostředí považujeme při dotyku živých částí zařízení během jeho obsluhy za bezpečná napětí",
            "stejnosměrné do 60 V a střídavé do 25 V"))
        add(Question(1134, Category.ELEKTRO,
            "Při úrazu elektrickým proudem",
            "vyprostíme postiženého z dosahu el. proudu, zavoláme záchrannou službu, provedeme záklon hlavy a dojde-li k selhání základních životních funkcí, zahájíme resuscitaci masáží srdce až do příjezdu záchranářů, případně umělým dýcháním"))
        add(Question(1135, Category.ELEKTRO,
            "Funkce \u201ESQUELCH\u201C VKV radiostanice je určena",
            "k potlačení slabších rušivých signálů včetně vlastního šumu přijímače"))
        add(Question(1136, Category.ELEKTRO,
            "Údaj elektrické napětí 2 V lze také zapsat",
            "2000 mV"))
        add(Question(1137, Category.ELEKTRO,
            "Kmitočet 406 MHz lze také zapsat",
            "0,406 GHz"))
        add(Question(1138, Category.ELEKTRO,
            "Délka rádiové vlny v pásmu 160 MHz (VHF) je",
            "cca. 2 m"))
        add(Question(1139, Category.ELEKTRO,
            "Vztah mezi napětím (U), proudem (I) a odporem (R) je:",
            "U = R*I"))
        add(Question(1140, Category.ELEKTRO,
            "Vztah mezi výkonem (příkonem) (P), napětím (U) a proudem (I) je:",
            "P = U*I"))
        add(Question(1141, Category.ELEKTRO,
            "Radiostanice odebírající z baterie 12 V proud 500 mA má příkon",
            "6 W"))
        add(Question(1142, Category.ELEKTRO,
            "Tři dobré vodiče elektřiny jsou",
            "měď, zlato, stříbro"))
        add(Question(1143, Category.ELEKTRO,
            "Čtyři dobré izolanty jsou",
            "sklo, vzduch, plast, porcelán"))
        add(Question(1144, Category.ELEKTRO,
            "Ke zdroji 10 V jsou připojeny dva odpory 10 \u03A9 zapojené do série. Odebíraný příkon činí:",
            "5 W"))
        add(Question(1145, Category.ELEKTRO,
            "Radiotechnická součástka je identifikována jako kondenzátor, pokud se její hodnota měří v",
            "pF"))
        add(Question(1146, Category.ELEKTRO,
            "Výstupním výkonem stanice se rozumí",
            "výkon koncového stupně vysílače dodávaný do napaječe anténního systému"))
        add(Question(1147, Category.ELEKTRO,
            "Je možné vysílat bez antény?",
            "ne, hrozí poškození radiostanice"))
        add(Question(1148, Category.ELEKTRO,
            "Co NENÍ možné dělat, když máme stisknuté tlačítko pro vysílání (PTT)?",
            "přijímat jiné vysílání"))
        add(Question(1149, Category.ELEKTRO,
            "V jakém kmitočtovém pásmu pracuje letadlová VHF radiostanice?",
            "118–137 MHz"))
    }

    fun getByCategory(category: Category): List<Question> =
        allQuestions.filter { it.category == category }

    fun getShuffled(category: Category? = null): List<Question> {
        val questions = if (category != null) getByCategory(category) else allQuestions
        return questions.shuffled()
    }
}
