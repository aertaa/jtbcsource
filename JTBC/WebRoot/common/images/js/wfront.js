wfront = {
  tckBackString: function(_strers)
  {
    var tbool = false;
    var tstrers = _strers;
    if (tstrers.substr(0, 11) == '<!--jtbc-->') tbool = true;
    return tbool;
  },
  tckrBackString: function(_strers)
  {
    var tstrers = _strers;
    if (tstrers.substr(0, 11) == '<!--jtbc-->') tstrers = tstrers.substr(11);
    return tstrers;
  },
  tckinnerHTMLDisplay: function(_strid1, _strid2)
  {
    var tstrid1 = _strid1;
    var tstrid2 = _strid2;
    var tobj1 = $(tstrid1);
    var tobj2 = $(tstrid2);
    if (tobj1 && tobj2)
    {
      if (tobj1.innerHTML != '') tobj2.style.display = '';
    };
  },
  tloadImgSrcs: function(_obj, _width, _height)
  {
    var tobj = _obj;
    var twidth = _width;
    var theight = _height;
    var tnewwidth = 0;
    var tnewheight = 0;
    var tImage = new Image();
    tImage.src = tobj.getAttribute('srcs');
    if (tImage.width > twidth && tImage.height > theight)
    {
      if ((tImage.width / twidth) > (tImage.height / theight)) tnewwidth = twidth;
      else tnewheight = theight;
    }
    else
    {
      if (tImage.width > twidth && tImage.height <= theight) tnewwidth = twidth;
      if (tImage.width <= twidth && tImage.height > theight) tnewheight = theight;
    };
    tobj.onload = function() {};
    tobj.src = tImage.src;
    if (tnewwidth != 0) tobj.width = tnewwidth;
    if (tnewheight != 0) tobj.height = tnewheight;
  }
};

wfront.img = {
  tinsertSelects: function(_name, _value)
  {
    var tname = _name;
    var tvalue = _value;
    var tobj = $(tname);
    if (tobj)
    {
      if (tobj.getAttribute('inserted') != 'yes')
      {
        var tAry = tvalue.split('|');
        for (ti = 0; ti < tAry.length; ti ++)
        {
          if (tAry[ti]) cls.selects.tAdd(tname, tAry[ti], tAry[ti]);
        };
        tobj.setAttribute('inserted', 'yes');
      };
    };
  }
};

wfront.loading = {
  tLoader: function(_strers)
  {
    var tstrers = _strers;
    if (tstrers)
    {
      var tHTML = '<div class="jloader"><div>' + tstrers + '</div></div>';
      cls.mask.tShow(tHTML);
      cls.mask.tSetStyle();
    };
  },
  tClose: function()
  {
    cls.mask.tClose();
  }
};

wfront.validator = {
  tRequire: /.+/,
  tEmail: /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/,
  tNumber: /^\d+$/,
  tEnglish: /^[A-Za-z]+$/,
  tChinese:  /^[\u0391-\uFFE5]+$/,
  tCheck: function(_obj)
  {
    var tobj = _obj;
    var tbool = true;
    var tFocusobj = null;
    var tRmessageString = '';
    if (tobj)
    {
      var tVobj, tValue;
      var tRtypeStr, tRequireStr, tRmessage;
      var tInputObj = tobj.getElementsByTagName('input');
      for(ti = 0; ti < tInputObj.length; ti++)
      {
        tVobj = tInputObj[ti];
        tValue = tVobj.value;
        tRtypeStr = tVobj.getAttribute('rtype');
        tRequireStr = tVobj.getAttribute('require');
        tRmessage = tVobj.getAttribute('rmessage');
        if (tRtypeStr)
        {
          if (tRequireStr != '0' || (tRequireStr == '0' && tValue))
          {
            if (!this['t' + tRtypeStr].test(tValue))
            {
              if (!tFocusobj) tFocusobj = tVobj;
              tRmessageString += tRmessage + '\n';
              tbool = false;
            };
          };
        };
      };
    };
    if (tFocusobj) tFocusobj.focus();
    if (tRmessageString) alert(tRmessageString);
    return tbool;
  }
};