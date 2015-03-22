var playervars = new Object();
var flex = new Object();

/* �洢����������� */
var kanjiangyi = [];
/* �洢��ҵ�������� */
var zuozuoye = [];
/* �洢������������� */
var questions = [];
/* �洢ѧϰ��������� */
var studypoint = [];
/* �洢���ؼ������� */
var download = [];
/* �洢ֱ�����ɼ������� */
var zhibodayi = [];
/* �洢���������� */
var tikutiku = [];
/* �洢д���ۼ������� */
var xiepingjia = [];
/* �洢��ʼ����ɼ������� */
var playercomplete = [];

/**
* ��������ȡ��ز��������ø÷��������Ⱥ�˳��
* 
* @param oid
*            �ǲ���������id������ΪString����������ͬ�Ĳ������Ͳ���
* @author Wei Chou
*/
function getPlayerVars(oid) {
    flex[oid] = document.getElementById(oid);
    if (playervars[oid]["boo"]) {	//boo��־���������Ƿ��Ѿ���
        flex[oid].setPlayerVars(playervars[oid]["obj"]);
        playervars[oid]["boo"] = false;
    }
}

/**
* �������ص���������������������ز��������ø÷��������Ⱥ�˳��
* 
* @param oid
*            �ǲ���������id������ΪString����������������ͬһҳ�����ж�����������������Ҫ���õĲ������ܸ�����ͬ
* @param obj
*            ������Object
* @author Wei Chou
*/
function setPlayerVars(oid, obj) {
    if (!playervars[oid]) playervars[oid] = new Object();

    playervars[oid]["obj"] = obj;
    if (flex[oid]) {
        flex[oid].setPlayerVars(playervars[oid]["obj"]);
    } else {
        playervars[oid]["boo"] = true;
    }
}

/**
* ��������ҳ�潻���ӿڣ���flex���ã�
* 
* @param oid
*            �ǲ���������id������ΪString
* @param sec
*            ��ǰ���Ž��ȣ���λΪ��
* @author Wei Chou
*/
function playerKanJiangYi(oid, sec) {
    for (var i = 0; i < kanjiangyi.length; i++) {
        if (kanjiangyi[i])
            kanjiangyi[i](oid, sec);
    }
}
/**
* �������¼�������
* 
* @param func
*            js�ص�������Ӧ������������(oid, sec)
* @author Wei Chou
*/
function onJiangYi(func) {
    kanjiangyi.push(func);
}

////////////////////////////////////////
/**
* ����ҵ��ҳ�潻���ӿڣ���flex���ã�
* 
* @param oid
*            �ǲ���������id������ΪString
* @param relateID
*            ��ǰ����id
* @author Wei Chou
*/
function playerZuoZuoYe(oid, relateID) {
    for (var i = 0; i < zuozuoye.length; i++) {
        if (zuozuoye[i])
            zuozuoye[i](oid, relateID);
    }
}
/**
* ����ҵ�¼�������
* 
* @param func
*            js�ص�������Ӧ������������(oid, relateID)
* @author Wei Chou
*/
function onZuoYe(func) {
    zuozuoye.push(func);
}

/**
* �������¼�
*/
function playerQuestions(oid) {
    for (var i = 0; i < questions.length; i++) {
        if (questions[i])
            questions[i](oid);
    }
}
/**
* �������¼�������
* @param func
* @return
*/
function onQuestions(func) {
    questions.push(func);
}
////////////////////////////////////////
/**
* ѧϰ����ҳ�潻���ӿڣ���flex���ã�
* 
* @param oid
*            �ǲ���������id������ΪString
* @param obj
*            ѧϰ������e.g:{RelateID:1234567,PartNum:123,MarkTime:123.456,Title:"���ڽ�ʲô"}
* @author Wei Chou
*/
function playerStudyPoint(oid, obj) {
    for (var i = 0; i < studypoint.length; i++) {
        if (studypoint[i])
            studypoint[i](oid, obj);
    }
}
/**
* ѧϰ���¼�������
* 
* @param func
*            js�ص�������Ӧ������������(oid, obj)
* @author Wei Chou
*/
function onStudyPoint(func) {
    studypoint.push(func);
}

/**
* ������ҳ�潻���ӿڣ���flex���ã�
* 
* @param oid
*            �ǲ���������id������ΪString
* @param type
*            Ҫ���ص�����"vedio"/"document"
* @author Wei Chou
*/
function playerDownLoad(oid, type) {
    for (var i = 0; i < download.length; i++) {
        if (download[i])
            download[i](oid, type);
    }
}
/**
* �����¼�������
* 
* @param func
*            js�ص�������Ӧ������������(oid, type)
* @author Wei Chou
*/
function onDownLoad(func) {
    download.push(func);
}

/**
* ֱ��������ҳ�潻���ӿڣ���flex���ã�
* 
* @param oid
*            �ǲ���������id������ΪString
* @author Wei Chou
*/
function playerZhiBoDaYi(oid) {
    for (var i = 0; i < zhibodayi.length; i++) {
        if (zhibodayi[i])
            zhibodayi[i](oid);
    }
}
/**
* ֱ�������¼�������
* 
* @param func
*            js�ص�������Ӧ����һ������(oid)
* @author Wei Chou
*/
function onZhiBoDaYi(func) {
    zhibodayi.push(func);
}

/**
* �����ҳ�潻���ӿڣ���flex���ã�
* 
* @param oid
*            �ǲ���������id������ΪString
* @author Wei Chou
*/
function playerTiKu(oid) {
    for (var i = 0; i < tikutiku.length; i++) {
        if (tikutiku[i])
            tikutiku[i](oid);
    }
}
/**
* ����¼�������
* 
* @param func
*            js�ص�������Ӧ����һ������(oid)
* @author Wei Chou
*/
function onTiKu(func) {
    tikutiku.push(func);
}

/**
* д������ҳ�潻���ӿڣ���flex���ã�
* 
* @param oid
*            �ǲ���������id������ΪString
* @author Wei Chou
*/
function playerXiePingJia(oid) {
    for (var i = 0; i < xiepingjia.length; i++) {
        if (xiepingjia[i])
            xiepingjia[i](oid);
    }
}
/**
* д�����¼�������
* 
* @param func
*            js�ص�������Ӧ����һ������(oid)
* @author Wei Chou
*/
function onXiePingJia(func) {
    xiepingjia.push(func);
}

/**
* player׼������
* @param oid
* @return
*/
function playerInitComplete(oid) {
    getPlayerSets(oid);
    for (var i = 0; i < playercomplete.length; i++) {
        if (playercomplete[i])
            playercomplete[i](oid);
    }
}
/**
* player��ʼ������¼�������
* @param func
*/
function onInitComplete(func) {
    playercomplete.push(func);
}

/**
* ������Щ��ť���û�����
* @param oid ����������id������ΪString
* @param str �ַ�������ť������
* @param boo ���û�����
*/
function setButtonEnabled(oid, str, boo) {
    if (!playervars[oid]) playervars[oid] = {};
    if (!playervars[oid]['buttonEnabled']) playervars[oid]['buttonEnabled'] = {};
    playervars[oid]['buttonEnabled']['string'] = str;
    playervars[oid]['buttonEnabled']['enable'] = boo;
    if (flex[oid]) {
        flex[oid].setButtonEnabled(playervars[oid]['buttonEnabled']['string'],
				playervars[oid]['buttonEnabled']['enable']);
    } else {
        playervars[oid]['buttonEnabled']['boo'] = true; //��������
    }
}

/**
* ��ʾ�����ز˵���
* @param oid
* @param show true/false
*/
function setMenubarShow(oid, show) {
    if (!playervars[oid]) playervars[oid] = {};
    if (!playervars[oid]['menubarShow']) playervars[oid]['menubarShow'] = {};
    playervars[oid]['menubarShow']['show'] = show;
    if (flex[oid]) {
        flex[oid].setMenubarShow(playervars[oid]['menubarShow']['show']);
    } else {
        playervars[oid]['menubarShow']['boo'] = true; //��������
    }
}

/**
* ����ִ��˳���ԭ�򣬸÷�����ִ�в��ܷ���getPlayerVars()�
* ��Ӧ�÷���playerInitComplete()������
* @param oid
*/
function getPlayerSets(oid) {
    if (!flex[oid]) return;
    if (playervars[oid]['buttonEnabled'] && playervars[oid]['buttonEnabled']['boo']) {
        flex[oid].setButtonEnabled(playervars[oid]['buttonEnabled']['string'],
				playervars[oid]['buttonEnabled']['enable']);
        playervars[oid]['buttonEnabled']['boo'] = false;
    }
    if (playervars[oid]['menubarShow'] && playervars[oid]['menubarShow']['boo']) {
        flex[oid].setMenubarShow(playervars[oid]['menubarShow']['show']);
        playervars[oid]['menubarShow']['boo'] = false;
    }
}




/**�������������**/
//���ò�������ʶ�����ݲ���
(function(){
   /* var nextRel = getNextRelate();
    var prevRel = getPrevRelate();*/
    setPlayerVars(playerid, {
       /* userID: getUserID(),
        relateID: getRelateID(),
        prevRelate: prevRel ? prevRel.title : "",
        nextRelate: nextRel ? nextRel.title : "",
        prevRelateURL: prevRel ? prevRel.url : "",
        nextRelateURL: nextRel ? nextRel.url : "",
        webserviceURL: appUrl+"/player/HdvInterface.asmx?wsdl",
        questionLinkURL: questionUrl.substring(0, questionUrl.indexOf("add.aspx")) + "question_show.aspx",
        zhiBoDaYiURL:"http://ask.edu24ol.com/ask.asp",
        tiKuURL:"http://"+location.host+"/exam/",
        xiePingJiaURL:$("#hlkEvaluate").attr("href"),
        mp3DownloadLink:$("#hlkMediaDown").attr("href"),
        pdfDownloadLink:$("#hlkNoteDown").attr("href"),*/
        partsData:partArray
    });
})();

//֪ʶ��ı��¼�
onStudyPoint(function (oid, obj) {
    currentPartId = obj.id;
    onPartChange();
});
//��ʼ�����
onInitComplete(function (oid) {
    
});

//������
onJiangYi(function (oid, sec) {
    showNote();
});
//������
onQuestions(function (oid) {
    
});
//����ҵ
onZuoYe(function (oid, relateID) {
    showExercise();
});
//����
onDownLoad(function (oid, type) {
//    if (type == "document") {
//        $("#hlkNoteDown").click();
//    } else {
//        $("#hlkMediaDown").click();
//    }
});
//ֱ������
onZhiBoDaYi(function (oid) {
    //window.open("http://ask.edu24ol.com/ask.asp");
});
//���
onTiKu(function (oid) {
    //window.open("http://www.edu24ol.com/exam/");
});
//д����
onXiePingJia(function (oid) {
//    if (getUserID() != 0) {
//        $("#hlkEvaluate").click();
//    }
});