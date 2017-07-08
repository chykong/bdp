/*Cookie*/
jQuery.jCookie = function(sCookieName_, oValue_, oExpires_, oOptions_) {
	if (!navigator.cookieEnabled) {
		return false;
	}
	var oOptions_ = oOptions_ || {};
	if (typeof (arguments[0]) !== 'string' && arguments.length === 1) {
		oOptions_ = arguments[0];
		sCookieName_ = oOptions_.name;
		oValue_ = oOptions_.value;
		oExpires_ = oOptions_.expires;
	}

	sCookieName_ = encodeURI(sCookieName_);

	if (oValue_ && (typeof (oValue_) !== 'number' && typeof (oValue_) !== 'string' && oValue_ !== null)) {
		return false;
	}

	var _sPath = oOptions_.path ? "; path=" + oOptions_.path : "";
	var _sDomain = oOptions_.domain ? "; domain=" + oOptions_.domain : "";
	var _sSecure = oOptions_.secure ? "; secure" : "";
	var sExpires_ = "";

	if (oValue_ || (oValue_ === null && arguments.length == 2)) {

		oExpires_ = (oExpires_ === null || (oValue_ === null && arguments.length == 2)) ? -1 : oExpires_;

		if (typeof (oExpires_) === 'number' && oExpires_ != 'session' && oExpires_ !== undefined) {
			var _date = new Date();
			_date.setTime(_date.getTime() + (oExpires_ * 24 * 60 * 60 * 1000));
			sExpires_ = [ "; expires=", _date.toGMTString() ].join("");
		}
		document.cookie = [ sCookieName_, "=", encodeURI(oValue_), sExpires_, _sDomain, _sPath, _sSecure ].join("");

		return true;
	}

	if (!oValue_ && typeof (arguments[0]) === 'string' && arguments.length == 1 && document.cookie && document.cookie.length) {
		var _aCookies = document.cookie.split(';');
		var _iLenght = _aCookies.length;
		while (_iLenght--) {
			var _aCurrrent = _aCookies[_iLenght].split("=");
			if (jQuery.trim(_aCurrrent[0]) === sCookieName_) {
				return decodeURI(_aCurrrent[1]);
			}
		}
	}

	return false;
};
/* Cookie */