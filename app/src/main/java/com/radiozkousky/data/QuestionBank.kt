package com.radiozkousky.data

object QuestionBank {

    val allQuestions: List<Question> = buildList {
        // ============================================================
        // a) Radiokomunikační předpisy (35 otázek)
        // ============================================================
        add(Question(1, Category.PREDPISY,
            "Zajištění účelného využívání rádiových kmitočtů a správu rádiového spektra vykonává",
            "Český telekomunikační úřad"))
        add(Question(2, Category.PREDPISY,
            "Radiokomunikační službou je komunikační činnost, která spočívá v přenosu, vysílání nebo příjmu signálů prostřednictvím",
            "rádiových vln"))
        add(Question(3, Category.PREDPISY,
            "Individuální oprávnění k využívání rádiových kmitočtů uděluje",
            "Český telekomunikační úřad"))
        add(Question(4, Category.PREDPISY,
            "Držitel individuálního oprávnění k využívání rádiových kmitočtů je povinen platit za využívání rádiových kmitočtů",
            "poplatek dle nařízení vlády o poplatcích"))
        add(Question(5, Category.PREDPISY,
            "Rádiovým spektrem se rozumí elektromagnetické vlny o kmitočtu (frekvenci)",
            "nižším než 3000 GHz"))
        add(Question(6, Category.PREDPISY,
            "Rádiové kmitočty z pásma 160 MHz spadají do pásma označovaného jako",
            "VHF"))
        add(Question(7, Category.PREDPISY,
            "Státní kontrolu elektronických komunikací vykonává",
            "Český telekomunikační úřad"))
        add(Question(8, Category.PREDPISY,
            "Fyzická osoba vykonávající obsluhu vysílacího rádiového zařízení bez platného průkazu odborné způsobilosti se dopustila",
            "přestupku"))
        add(Question(9, Category.PREDPISY,
            "Za obsluhu vysílacího rádiového zařízení bez platného průkazu odborné způsobilosti lze uložit fyzické osobě pokutu do výše",
            "100 000 Kč"))
        add(Question(10, Category.PREDPISY,
            "V mezinárodní volací značce České republiky tvoří první dvě písmena (prefix) vždy dvojice písmen",
            "OK nebo OL"))
        add(Question(11, Category.PREDPISY,
            "Mezinárodní volací značka letadlové stanice u letounů zapsaných v leteckém rejstříku ČR je",
            "OK a další tři písmena"))
        add(Question(12, Category.PREDPISY,
            "Falešné volací značky a falešné signály",
            "se nesmí používat"))
        add(Question(13, Category.PREDPISY,
            "Pohyblivá stanice letecké pohyblivé služby je",
            "letadlová stanice"))
        add(Question(14, Category.PREDPISY,
            "Pevná služba je",
            "radiokomunikační služba mezi stanovenými pevnými body"))
        add(Question(15, Category.PREDPISY,
            "Nejvyšší prioritu a absolutní přednost má zpráva",
            "tísňová"))
        add(Question(16, Category.PREDPISY,
            "Pořadí zpráv dle důležitosti je následující:",
            "tísňové zprávy, pilnostní zprávy, zprávy o radiovém zaměřování, zprávy pro zajištění bezpečnosti letů, meteorologické zprávy, zprávy o pravidelnosti letů"))
        add(Question(17, Category.PREDPISY,
            "Tísňové volání a tísňová zpráva se vysílají jen na rozkaz",
            "velitele nebo osoby odpovědné za loď nebo letadlo"))
        add(Question(18, Category.PREDPISY,
            "Mezinárodní tísňový kmitočet v radiotelefonii v letecké pohyblivé službě je",
            "121,5 MHz"))
        add(Question(19, Category.PREDPISY,
            "Služba u letadlové nebo lodní stanice podléhá nejvyšší pravomoci",
            "velitele nebo osoby, která je odpovědná za letadlo nebo loď"))
        add(Question(20, Category.PREDPISY,
            "Doba platnosti průkazů radiotelefonistů pro leteckou a námořní pohyblivou službu je při prvním vydání průkazu stanovena na",
            "10 let"))
        add(Question(21, Category.PREDPISY,
            "O prodloužení doby platnosti průkazu odborné způsobilosti se žádá",
            "písemně, minimálně jeden měsíc před koncem platnosti průkazu"))
        add(Question(22, Category.PREDPISY,
            "Při žádosti o prodloužení platnosti průkazu je třeba také",
            "uhradit příslušný správní poplatek a doložit praxi v obsluze rádiových stanic"))
        add(Question(23, Category.PREDPISY,
            "V případě, že doba platnosti průkazu již uplynula, může držitel průkazu žádat o nový průkaz",
            "v období do jednoho roku ode dne pozbytí platnosti průkazu"))
        add(Question(24, Category.PREDPISY,
            "Doba platnosti průkazů radiotelefonistů námořní a letecké pohyblivé služby se na základě žádosti držitele prodlužuje o",
            "5 let"))
        add(Question(25, Category.PREDPISY,
            "První znak nebo první dva znaky mezinárodní volací značky označují",
            "státní příslušnost stanice"))
        add(Question(26, Category.PREDPISY,
            "Stejná volací značka",
            "nemůže být přidělena dvěma nebo více provozovatelům stanic"))
        add(Question(27, Category.PREDPISY,
            "Inspekční orgány zemí, které provedou kontrolu vybavení radiové stanice",
            "mohou vyžadovat předložení průkazu operátora"))
        add(Question(28, Category.PREDPISY,
            "Mezinárodní telekomunikační unie (ITU) je",
            "specializovanou organizací Organizace spojených národů pro oblast telekomunikací"))
        add(Question(29, Category.PREDPISY,
            "Mezinárodní organizací CEPT se rozumí",
            "Evropská konference poštovních a telekomunikačních správ"))
        add(Question(30, Category.PREDPISY,
            "Q-kódem se rozumí",
            "kódová skupina tří písmen začínající vždy písmenem Q, která má určitý konkrétní, mezinárodně dohodnutý význam"))
        add(Question(31, Category.PREDPISY,
            "Volací značky se přidělují tak, aby nemohly být zaměněny",
            "s tísňovými, pilnostními a bezpečnostními signály nebo s kódovými zkratkami Q-kódu"))
        add(Question(32, Category.PREDPISY,
            "Volací značkou je",
            "každé poznávací označení stanice přidělené podle Radiokomunikačního řádu, které umožňuje zjištění její totožnosti během vysílání"))
        add(Question(33, Category.PREDPISY,
            "Telekomunikační tajemství se týká",
            "všech osob, které znají obsah rádiových zpráv nebo se dověděly o jejich existenci či o zprávě, která byla doručena prostřednictvím radiokomunikační služby"))
        add(Question(34, Category.PREDPISY,
            "Každý, kdo se dozví informace o skutečnostech, které jsou předmětem telekomunikačního tajemství,",
            "je povinen zachovávat o nich mlčenlivost"))
        add(Question(35, Category.PREDPISY,
            "Předmětem telekomunikačního tajemství je zejména",
            "obsah zpráv přepravovaných nebo jinak zprostředkovaných telekomunikačními zařízeními a sítěmi s výjimkou zpráv určených veřejnosti"))

        // ============================================================
        // b) Radiokomunikační provoz (98 otázek)
        // ============================================================
        add(Question(36, Category.PROVOZ,
            "Letecká pohyblivá služba je",
            "pohyblivá služba mezi leteckými stanicemi a letadlovými stanicemi nebo mezi letadlovými stanicemi navzájem"))
        add(Question(37, Category.PROVOZ,
            "Služba rádiového určování pro účely radionavigace je",
            "radionavigační služba"))
        add(Question(38, Category.PROVOZ,
            "Letecká pevná služba (AFS dle L10) je",
            "telekomunikační služba mezi stanovenými pevnými body"))
        add(Question(39, Category.PROVOZ,
            "Letecká rozhlasová služba (dle L10) je",
            "služba určená k vysílání informací týkajících se leteckého provozu"))
        add(Question(40, Category.PROVOZ,
            "Vysílání pokusných signálů nesmí trvat déle než",
            "10 vteřin"))
        add(Question(41, Category.PROVOZ,
            "Při zkušebním vysílání dokonalá čitelnost je uváděna stupněm",
            "5 (Perfectly Readable)"))
        add(Question(42, Category.PROVOZ,
            "Zkušební vysílání musí obsahovat",
            "volací značku volané stanice, volací značku letadla, slova „RADIO CHECK“, používaný kmitočet"))
        add(Question(43, Category.PROVOZ,
            "Při zkoušce rádia se použije fráze",
            "RADIO CHECK nebo HOW DO YOU READ?"))
        add(Question(44, Category.PROVOZ,
            "Rychlost hovoru při radiotelefonním spojení nemá převyšovat",
            "100 slov za minutu"))
        add(Question(45, Category.PROVOZ,
            "Radiotelefonní spojení letadlo – země by se mělo provádět",
            "všeobecně v jazyce ve kterém komunikuje pozemní stanice"))
        add(Question(46, Category.PROVOZ,
            "Zprávy musí být vysílány",
            "v otevřené řeči a ve schválených frázích"))
        add(Question(47, Category.PROVOZ,
            "Při vysílání",
            "zachováváme stále stejnou výši hlasu ve všech fázích hovoru"))
        add(Question(48, Category.PROVOZ,
            "Pořadí zpráv dopravovaných leteckou pohyblivou službou je",
            "tísňové, pilnostní, o rádiovém zaměřování, pro zajištění bezpečnosti letů, meteorologické, o pravidelnosti letů"))
        add(Question(49, Category.PROVOZ,
            "3x opakovaný tísňový signál MAYDAY musí být použit",
            "na začátku první zprávy tísňové korespondence"))
        add(Question(50, Category.PROVOZ,
            "PAN PAN MEDICAL je radiotelefonní signál zprávy",
            "pilnostní"))
        add(Question(51, Category.PROVOZ,
            "Tísňová korespondence",
            "má přednost před všemi ostatními druhy spojení"))
        add(Question(52, Category.PROVOZ,
            "Pilnostní zpráva",
            "má přednost před všemi ostatními druhy spojení vyjma tísňového provozu"))
        add(Question(53, Category.PROVOZ,
            "Tísňový a pilnostní provoz musí být zahájen na kmitočtu",
            "který je v dané chvíli používán"))
        add(Question(54, Category.PROVOZ,
            "Každá stanice, která ví o tísňovém provozu má povinnost",
            "na tomto kmitočtu nevysílat ale tento provoz sledovat"))
        add(Question(55, Category.PROVOZ,
            "Po volání letecké stanice, na které tato stanice neodpoví, je letadlová stanice povinna",
            "vyčkat nejméně 10 sekund, než provede další volání"))
        add(Question(56, Category.PROVOZ,
            "Jména, zkratky a slova, jejichž výslovnost může vyvolat pochybnost se v radiotelefonním provozu musí",
            "hláskovat pomocí mezinárodní hláskovací abecedy"))
        add(Question(57, Category.PROVOZ,
            "Letadlová stanice potvrzuje příjem důležitých zpráv řízení letového provozu nebo jejich částí",
            "jejich opakováním a připojením vlastní volací značky"))
        add(Question(58, Category.PROVOZ,
            "Letadlo přechází z jednoho rádiového kmitočtu na druhý v řízeném prostoru",
            "z příkazu letecké stanice nebo v souladu s předepsanými postupy"))
        add(Question(59, Category.PROVOZ,
            "Letadlo za letu",
            "nesmí měnit svou volací značku"))
        add(Question(60, Category.PROVOZ,
            "Za letu musí letadlová stanice",
            "udržovat stálou poslechovou hlídku"))
        add(Question(61, Category.PROVOZ,
            "Poznávací značka civilního letadla je složena",
            "ze značky státní příslušnosti a rejstříkové značky"))
        add(Question(62, Category.PROVOZ,
            "Volací značku letadla lze v některých případech tvořit",
            "radiotelefonním označením provozovatele letadla, za kterým následuje označení (číslo) letu v souladu s předpisem ICAO"))
        add(Question(63, Category.PROVOZ,
            "Zkrácenou volací značku použije letadlová stanice",
            "pouze v případě, že byla tímto způsobem oslovena leteckou stanicí"))
        add(Question(64, Category.PROVOZ,
            "Správné zkrácení volací značky OKABC je",
            "OBC"))
        add(Question(65, Category.PROVOZ,
            "Jakmile je spojení navázáno",
            "lze nepřetržitě korespondovat v obou směrech bez dalšího použití volacích značek nebo volání"))
        add(Question(66, Category.PROVOZ,
            "Všechna čísla se vyslovují",
            "jednotlivě, s výjimkou čísel souvisejících s výškou nad hladinou moře, s výškou oblačnosti, dohlednosti a dráhové dohlednosti"))
        add(Question(67, Category.PROVOZ,
            "V radiotelefonním provozu v pásmech VKV se kmitočet 119,500 MHz vyjadřuje",
            "ONE ONE NINE DECIMAL FIVE"))
        add(Question(68, Category.PROVOZ,
            "V radiotelefonním provozu v pásmech VKV se kmitočet 121,500 MHz vyjadřuje",
            "ONE TWO ONE DECIMAL FIVE"))
        add(Question(69, Category.PROVOZ,
            "V radiotelefonním provozu v pásmech VKV se kmitočet 121,050 MHz vyjadřuje",
            "ONE TWO ONE DECIMAL ZERO FIVE ZERO"))
        add(Question(70, Category.PROVOZ,
            "V radiotelefonním provozu v pásmech VKV se vysílací kanál vyjadřuje",
            "čtyřmi nebo šesti číslicemi"))
        add(Question(71, Category.PROVOZ,
            "V radiotelefonním provozu v pásmech VKV se kmitočet 118,055 MHz vyjadřuje",
            "ONE ONE EIGHT DECIMAL ZERO FIVE FIVE"))
        add(Question(72, Category.PROVOZ,
            "Odchylky od spisovné češtiny při vysílání čísel jsou",
            "u číslic: 2, 4, 7, 8"))
        add(Question(73, Category.PROVOZ,
            "Odchylky od spisovné angličtiny při vysílání čísel jsou",
            "u číslic: 3, 4, 9"))
        add(Question(74, Category.PROVOZ,
            "Nastavení výškoměru na hodnotu 1000 hPa se vysílá takto:",
            "QNH ONE THOUSAND"))
        add(Question(75, Category.PROVOZ,
            "Nastavení výškoměru na hodnotu 1009 hPa se vysílá takto:",
            "QNH ONE ZERO ZERO NINE"))
        add(Question(76, Category.PROVOZ,
            "Letová hladina FL 300 se vysílá takto:",
            "Flight Level THREE HUNDRED"))
        add(Question(77, Category.PROVOZ,
            "Letová hladina FL 180 se vysílá takto:",
            "Flight Level ONE EIGHT ZERO"))
        add(Question(78, Category.PROVOZ,
            "Potvrzení příjmu leteckou stanicí musí obsahovat",
            "volací značku letadla za kterou následuje, je-li to nutné, volací značka letecké stanice"))
        add(Question(79, Category.PROVOZ,
            "Rádiové spojení může být ukončeno",
            "na pokyn letecké stanice"))
        add(Question(80, Category.PROVOZ,
            "Volací značka letecké stanice je tvořena",
            "zeměpisným názvem její polohy a službou, která je k dispozici"))
        add(Question(81, Category.PROVOZ,
            "Stanice letecké pohyblivé služby používají",
            "koordinovaný světový čas"))
        add(Question(82, Category.PROVOZ,
            "Hláskovací abeceda (Mezinárodní): A, E, I",
            "A – Alpha, E – Echo, I – India"))
        add(Question(83, Category.PROVOZ,
            "Hláskovací abeceda (Mezinárodní): B, F, J",
            "B – Bravo, F – Foxtrot, J – Juliett"))
        add(Question(84, Category.PROVOZ,
            "Hláskovací abeceda (Mezinárodní): C, G, K",
            "C – Charlie, G – Golf, K – Kilo"))
        add(Question(85, Category.PROVOZ,
            "Hláskovací abeceda (Mezinárodní): D, H, L",
            "D – Delta, H – Hotel, L – Lima"))
        add(Question(86, Category.PROVOZ,
            "Hláskovací abeceda (Mezinárodní): M, Q, U",
            "M – Mike, Q – Quebec, U – Uniform"))
        add(Question(87, Category.PROVOZ,
            "Hláskovací abeceda (Mezinárodní): N, R, V",
            "N – November, R – Romeo, V – Victor"))
        add(Question(88, Category.PROVOZ,
            "Hláskovací abeceda (Mezinárodní): O, S, W",
            "O – Oscar, S – Sierra, W – Whisky"))
        add(Question(89, Category.PROVOZ,
            "Hláskovací abeceda (Mezinárodní): P, T, X",
            "P – Papa, T – Tango, X – X-ray"))
        add(Question(90, Category.PROVOZ,
            "Hláskovací abeceda (Mezinárodní): Y, Z",
            "Y – Yankee, Z – Zulu"))
        add(Question(91, Category.PROVOZ,
            "Správný Q-kód pro „tlak indikující nadmořskou výšku, přepočtený na střední hladinu moře“",
            "QNH"))
        add(Question(92, Category.PROVOZ,
            "Q-kód QFE se vztahuje k",
            "tlaku vzduchu vztaženému k nadmořské výšce letiště"))
        add(Question(93, Category.PROVOZ,
            "V případě, že letadlo stojí na ploše letiště a hodnota tlaku je nastavena na QFE, bude výškoměr ukazovat",
            "nulu"))
        add(Question(94, Category.PROVOZ,
            "Správný Q-kód pro „magnetický kurs pro směr k zaměřovači“",
            "QDM"))
        add(Question(95, Category.PROVOZ,
            "Správný Q-kód pro „magnetické zaměření od zaměřovače“",
            "QDR"))
        add(Question(96, Category.PROVOZ,
            "Zkratka AFIS znamená",
            "Stanoviště letištní letové informační služby"))
        add(Question(97, Category.PROVOZ,
            "Zkratka AIP znamená",
            "Letecká informační příručka"))
        add(Question(98, Category.PROVOZ,
            "Zkratka ATC znamená",
            "Řízení letového provozu"))
        add(Question(99, Category.PROVOZ,
            "Zkratka ATIS znamená",
            "Automatická informační služba koncové řízené oblasti"))
        add(Question(100, Category.PROVOZ,
            "Zkratka CAVOK znamená",
            "Dohlednost, oblačnost a současné počasí lepší než předepsané hodnoty nebo podmínky"))
        add(Question(101, Category.PROVOZ,
            "Zkratka CTR znamená",
            "Řízený okrsek"))
        add(Question(102, Category.PROVOZ,
            "Zkratka DME znamená",
            "Měřič vzdálenosti"))
        add(Question(103, Category.PROVOZ,
            "Zkratka FIR znamená",
            "Letová informační oblast"))
        add(Question(104, Category.PROVOZ,
            "Zkratka IFR znamená",
            "Pravidla pro let podle přístrojů"))
        add(Question(105, Category.PROVOZ,
            "Zkratka RVR znamená",
            "Dráhová dohlednost"))
        add(Question(106, Category.PROVOZ,
            "Zkratka TWR znamená",
            "Letištní řídící věž"))
        add(Question(107, Category.PROVOZ,
            "Zkratka VFR znamená",
            "Pravidla pro let za viditelnosti"))
        add(Question(108, Category.PROVOZ,
            "Zkratka UTC znamená",
            "Světový koordinovaný čas"))
        add(Question(109, Category.PROVOZ,
            "Správná zkratka pro „kmitočet“",
            "FREQ"))
        add(Question(110, Category.PROVOZ,
            "Správná zkratka pro „vzletová a přistávací dráha“",
            "RWY"))
        add(Question(111, Category.PROVOZ,
            "Správná zkratka pro „automatická informační služba koncové řízené oblasti“",
            "ATIS"))
        add(Question(112, Category.PROVOZ,
            "Správná zkratka pro „letová informační oblast“",
            "FIR"))
        add(Question(113, Category.PROVOZ,
            "Zkratka FZRA znamená",
            "namrzající déšť"))
        add(Question(114, Category.PROVOZ,
            "Zkratka BKN znamená",
            "oblačno"))
        add(Question(115, Category.PROVOZ,
            "Zkratka NOSIG znamená",
            "bez význačné změny"))
        add(Question(116, Category.PROVOZ,
            "Zkratka INTSF znamená",
            "sílení nebo zvyšování uvedené hodnoty"))
        add(Question(117, Category.PROVOZ,
            "Volací znak INFORMATION je přiřazen (letištím)",
            "letištím, poskytujícím službu AFIS"))
        add(Question(118, Category.PROVOZ,
            "Volací znak INFORMATION je přiřazen (střediskům)",
            "letovým informačním střediskům např. FIC Praha"))
        add(Question(119, Category.PROVOZ,
            "Fráze „STAND BY“ znamená",
            "čekejte, zavolám Vás"))
        add(Question(120, Category.PROVOZ,
            "Fráze „AFFIRM“ znamená",
            "\"Ano\""))
        add(Question(121, Category.PROVOZ,
            "Fráze „WILCO“ znamená",
            "rozumím Vaší zprávě a budu podle ní postupovat"))
        add(Question(122, Category.PROVOZ,
            "Fráze „ACKNOWLEDGE“ znamená",
            "potvrďte mi, že jste zprávu přijal a rozuměl jí"))
        add(Question(123, Category.PROVOZ,
            "Fráze „CONFIRM“ znamená",
            "požaduji ověření: (povolení, instrukce, opatření, informace)"))
        add(Question(124, Category.PROVOZ,
            "Fráze „ROGER“ znamená",
            "přijal jsem vše z Vašeho posledního vysílání"))
        add(Question(125, Category.PROVOZ,
            "Fráze „APPROVED“ znamená",
            "povolení pro požadovaný úkon je schváleno"))
        add(Question(126, Category.PROVOZ,
            "Fráze „NEGATIV“ znamená",
            "povolení není potvrzeno – to není správné – ne"))
        add(Question(127, Category.PROVOZ,
            "Fráze „SAY AGAIN“ znamená",
            "opakujte vše nebo následující část Vašeho posledního vysílání"))
        add(Question(128, Category.PROVOZ,
            "Slovo „správně“ znamená",
            "to je správné"))
        add(Question(129, Category.PROVOZ,
            "Fráze „TAKE-OFF APPROVED“",
            "není přípustná"))
        add(Question(130, Category.PROVOZ,
            "Fráze „LINE UP RUNWAY“ znamená",
            "vstupte na dráhu"))
        add(Question(131, Category.PROVOZ,
            "Fráze „REQUEST START UP“ znamená",
            "žádám spouštění"))
        add(Question(132, Category.PROVOZ,
            "Fráze „HOLD POSITION“ znamená",
            "vyčkávejte na místě"))
        add(Question(133, Category.PROVOZ,
            "Při frázi BREAK BREAK pro dvě letadlové stanice, pořadí pro odpovědi volaných stanic je následující:",
            "odpovídá druhá oslovená stanice"))

        // ============================================================
        // c) Elektrotechnika a radiotechnika (31 otázek)
        // ============================================================
        add(Question(134, Category.ELEKTRO,
            "Paralelně řazené akumulátory",
            "umožňují dodávat větší proud"))
        add(Question(135, Category.ELEKTRO,
            "Sériově řazené akumulátory",
            "se zapojují pro zvýšení dodávaného napětí"))
        add(Question(136, Category.ELEKTRO,
            "Jmenovité napětí článku olověného akumulátoru je",
            "2 V"))
        add(Question(137, Category.ELEKTRO,
            "Jmenovité napětí článku alkalického akumulátoru je",
            "1,2 V"))
        add(Question(138, Category.ELEKTRO,
            "Jmenovité napětí suchého galvanického článku je",
            "1,5 V"))
        add(Question(139, Category.ELEKTRO,
            "Suché galvanické články",
            "nelze dobíjet"))
        add(Question(140, Category.ELEKTRO,
            "Antény dělíme podle směru vysílání nebo příjmu na",
            "směrové a všesměrové"))
        add(Question(141, Category.ELEKTRO,
            "Všesměrová anténa má vyzařovací charakteristiku",
            "kruhovou"))
        add(Question(142, Category.ELEKTRO,
            "Všesměrová anténa musí přijímat nebo vysílat stejně všemi směry",
            "v horizontální rovině"))
        add(Question(143, Category.ELEKTRO,
            "Půlvlnný dipól",
            "může být směrová i všesměrová anténa, záleží na jeho orientaci k zemskému povrchu"))
        add(Question(144, Category.ELEKTRO,
            "Půlvlnný dipól umístěný rovnoběžně se zemským povrchem",
            "má v horizontální rovině osmičkovou vyzařovací charakteristiku"))
        add(Question(145, Category.ELEKTRO,
            "Vztah mezi délkou vlny (λ) a kmitočtem (f), když c je rychlost světla, je",
            "f = c / λ"))
        add(Question(146, Category.ELEKTRO,
            "Který typ modulace mění kmitočet vysokofrekvenčního signálu v závislosti na přiváděném modulačním napětí",
            "kmitočtová modulace"))
        add(Question(147, Category.ELEKTRO,
            "Ampérmetr a voltmetr se při měření zařazují",
            "ampérmetr do série se spotřebičem, voltmetr paralelně ke spotřebiči"))
        add(Question(148, Category.ELEKTRO,
            "V suchém, bezprašném prostředí považujeme při dotyku živých částí zařízení během jeho obsluhy za bezpečná napětí",
            "stejnosměrné do 60 V a střídavé do 25 V"))
        add(Question(149, Category.ELEKTRO,
            "Při úrazu elektrickým proudem",
            "vyprostíme postiženého z dosahu el. proudu, zavoláme záchrannou službu, provedeme záklon hlavy a dojde-li k selhání základních životních funkcí, zahájíme resuscitaci masáží srdce až do příjezdu záchranářů, případně umělým dýcháním"))
        add(Question(150, Category.ELEKTRO,
            "Funkce „SQUELCH“ VKV radiostanice je určena",
            "k potlačení slabších rušivých signálů včetně vlastního šumu přijímače"))
        add(Question(151, Category.ELEKTRO,
            "Údaj elektrické napětí 2 V lze také zapsat",
            "2000 mV"))
        add(Question(152, Category.ELEKTRO,
            "Kmitočet 406 MHz lze také zapsat",
            "0,406 GHz"))
        add(Question(153, Category.ELEKTRO,
            "Délka rádiové vlny v pásmu 160 MHz (VHF) je",
            "cca. 2 m"))
        add(Question(154, Category.ELEKTRO,
            "Vztah mezi napětím (U), proudem (I) a odporem (R) je:",
            "U = R*I"))
        add(Question(155, Category.ELEKTRO,
            "Vztah mezi výkonem (příkonem) (P), napětím (U) a proudem (I) je:",
            "P = U*I"))
        add(Question(156, Category.ELEKTRO,
            "Radiostanice odebírající z baterie 12 V proud 500 mA má příkon",
            "6 W"))
        add(Question(157, Category.ELEKTRO,
            "Tři dobré vodiče elektřiny jsou",
            "měď, zlato, stříbro"))
        add(Question(158, Category.ELEKTRO,
            "Čtyři dobré izolanty jsou",
            "sklo, vzduch, plast, porcelán"))
        add(Question(159, Category.ELEKTRO,
            "Ke zdroji 10 V jsou připojeny dva odpory 10 Ω zapojené do série. Odebíraný příkon činí:",
            "5 W"))
        add(Question(160, Category.ELEKTRO,
            "Radiotechnická součástka je identifikována jako kondenzátor, pokud se její hodnota měří v",
            "pF"))
        add(Question(161, Category.ELEKTRO,
            "Výstupním výkonem stanice se rozumí",
            "výkon koncového stupně vysílače dodávaný do napaječe anténního systému"))
        add(Question(162, Category.ELEKTRO,
            "Je možné vysílat bez antény?",
            "ne, hrozí poškození radiostanice"))
        add(Question(163, Category.ELEKTRO,
            "Co NENÍ možné dělat, když máme stisknuté tlačítko pro vysílání (PTT)?",
            "přijímat jiné vysílání"))
        add(Question(164, Category.ELEKTRO,
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
