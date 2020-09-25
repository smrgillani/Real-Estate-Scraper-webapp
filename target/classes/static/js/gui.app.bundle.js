! function (s) {
	function e(e) {
		for (var t, a, o = e[0], n = e[1], r = e[2], i = 0, l = []; i < o.length; i++) a = o[i], c[a] && l.push(c[a][0]), c[a] = 0;
		for (t in n) Object.prototype.hasOwnProperty.call(n, t) && (s[t] = n[t]);
		for (p && p(e); l.length;) l.shift()();
		return d.push.apply(d, r || []), u()
	}

	function u() {
		for (var e, t = 0; t < d.length; t++) {
			for (var a = d[t], o = !0, n = 1; n < a.length; n++) {
				var r = a[n];
				0 !== c[r] && (o = !1)
			}
			o && (d.splice(t--, 1), e = i(i.s = a[0]))
		}
		return e
	}
	var a = {},
		c = {
			0: 0
		},
		d = [];

	function i(e) {
		if (a[e]) return a[e].exports;
		var t = a[e] = {
			i: e,
			l: !1,
			exports: {}
		};
		return s[e].call(t.exports, t, t.exports, i), t.l = !0, t.exports
	}
	i.m = s, i.c = a, i.d = function (e, t, a) {
		i.o(e, t) || Object.defineProperty(e, t, {
			enumerable: !0,
			get: a
		})
	}, i.r = function (e) {
		"undefined" != typeof Symbol && Symbol.toStringTag && Object.defineProperty(e, Symbol.toStringTag, {
			value: "Module"
		}), Object.defineProperty(e, "__esModule", {
			value: !0
		})
	}, i.t = function (t, e) {
		if (1 & e && (t = i(t)), 8 & e) return t;
		if (4 & e && "object" == typeof t && t && t.__esModule) return t;
		var a = Object.create(null);
		if (i.r(a), Object.defineProperty(a, "default", {
				enumerable: !0,
				value: t
			}), 2 & e && "string" != typeof t)
			for (var o in t) i.d(a, o, function (e) {
				return t[e]
			}.bind(null, o));
		return a
	}, i.n = function (e) {
		var t = e && e.__esModule ? function () {
			return e.default
		} : function () {
			return e
		};
		return i.d(t, "a", t), t
	}, i.o = function (e, t) {
		return Object.prototype.hasOwnProperty.call(e, t)
	}, i.p = "/resources/", i.h = "d81dd1fede445fed4151", i.cn = "app";
	var t = window.webpackJsonp = window.webpackJsonp || [],
		o = t.push.bind(t);
	t.push = e, t = t.slice();
	for (var n = 0; n < t.length; n++) e(t[n]);
	var p = o;
	d.push([30, 1]), u()
}({
	10: function (e, i, l) {
		"use strict";
		(function (t) {
			var a = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
			Object.defineProperty(i, "__esModule", {
				value: !0
			});
			var o = l(25),
				n = l(720),
				r = l(30),
				e = function () {
					function e() {
						this.emitter = new n.EventEmitter, this.loadingPercent = 0, this.isLoading = !1, this.loadingItems = [], this.canCurrentLoadingDisplayBeOverridden = !1, this.isLoadingDisplayed = !0, this.loadingEvent = 0, this.currentMapUrl = "", this.symbolOn = !0, this.arealOn = !0, this.hasSymbol = !1, this.hasAreal = !1, this.geometryOn = !0, this.reliefOn = !0, this.visCount = 0, this.isLiveMap = !1, this.isLiveActive = !1, this.modalOpen = !1, this.modalContent = null, this.modalWidth = 0, this.modalTime = Date.now(), this.topotypeStates = {
							ln: {},
							bmp: {}
						}, this.currentRollOver = "", this.currentMarkedRegion = "", this.snackbarText = "", this.snackbarOpen = !1, this.treeDrawerOpen = !1, this.isContentTreeVisible = !0, this.isSearchMenuVisible = !1, this.windowWidth = t(window).width(), this.windowHeight = t(window).height(), this.appHeaderHeight = 0, this.bottomBarHeight = 0, this.mapContentDrawer = !1, this.mapLegendDrawer = !0, this.isMapContentVisible = !1, this.isMapLegendVisible = !0, this.canMapLegendBeRendered = !1, this.isBottomLegend = !1, this.legendWidth = 10, this.contentWidth = 10, this.mapContentFillsScreen = !1, this.dataTable = [], this.guitimestamp = 0, this.currentLocIda = null, this.currentMapIda = null, this.currentGeometryIda = null, this.mapTitle = "", this.currentPattern = null, this.mapPath = [], this.actualZoomFactor = 100, this.rulerWidth = 200, this.adaptedRulerWidth = 200, this.panningActivated = !1, this.isFullScreen = !1, this.searchMenuWidth = 500, this.searchResult = [], this.foundMapsSearchResult = [], this.projectListSearchResult = [], this.unitListSearchResult = [], this.validIds = [], this.colorFields = [], this.animationAvailable = !1, this.animationStarted = !1, this.showAutomationControls = !0, this.automationControlsMinimized = !0
					}
					return e.prototype.isTopoOn = function (e, t) {
						var a = this;
						return r.bajoodooLog("isTopoOn: " + t), o.computed(function () {
							return void 0 === a.topotypeStates[e] || void 0 === a.topotypeStates[e][t] || a.topotypeStates[e][t]
						}).get()
					}, e.prototype.setTopoState = function (e, t, a) {
						r.bajoodooLog("setTopoState: " + t, a), this.topotypeStates[e][t] = a
					}, Object.defineProperty(e.prototype, "isSymbolOn", {
						get: function () {
							return this.symbolOn
						},
						enumerable: !0,
						configurable: !0
					}), e.prototype.setSymbolState = function (e) {
						this.symbolOn = e
					}, Object.defineProperty(e.prototype, "isGeometryOn", {
						get: function () {
							return this.geometryOn
						},
						enumerable: !0,
						configurable: !0
					}), e.prototype.setGeometryState = function (e) {
						this.geometryOn = e
					}, Object.defineProperty(e.prototype, "isReliefOn", {
						get: function () {
							return this.reliefOn
						},
						enumerable: !0,
						configurable: !0
					}), e.prototype.setReliefState = function (e) {
						this.reliefOn = e
					}, Object.defineProperty(e.prototype, "isArealOn", {
						get: function () {
							return this.arealOn
						},
						enumerable: !0,
						configurable: !0
					}), e.prototype.setArealState = function (e) {
						this.arealOn = e
					}, e.prototype.setSnackbarText = function (e) {
						this.snackbarText = e
					}, Object.defineProperty(e.prototype, "snackbar", {
						get: function () {
							return this.snackbarText
						},
						enumerable: !0,
						configurable: !0
					}), e.prototype.addDatatableItem = function (e) {
						this.dataTable.push(e)
					}, Object.defineProperty(e.prototype, "loadingState", {
						get: function () {
							return 0 < this.loadingItems.length
						},
						enumerable: !0,
						configurable: !0
					}), e.prototype.getLoadingPercent = function () {
						return this.loadingPercent
					}, e.prototype.setLoadingPercent = function (e) {
						this.setLoadingItemPercent(e)
					}, e.prototype.setIsLoading = function (t) {
						this.loadingItems.filter(function (e) {
							return t.id === e.id && t.type === e.type
						}).length || (this.loadingItems.push(t), this.loadingEvent = Date.now())
					}, e.prototype.setLoadingItemPercent = function (t) {
						var a = this;
						this.loadingItems.map(function (e) {
							return e.id === t.id && e.type === t.type && (e.percent = t.percent, a.loadingEvent = Date.now()), e
						})
					}, e.prototype.setNotLoading = function (t) {
						var e = this.loadingItems.filter(function (e) {
							return t.id !== e.id && t.type !== e.type
						});
						this.loadingItems = e, this.loadingEvent = Date.now()
					}, a([o.observable], e.prototype, "loadingPercent", void 0), a([o.observable], e.prototype, "isLoading", void 0), a([o.observable], e.prototype, "loadingItems", void 0), a([o.observable], e.prototype, "canCurrentLoadingDisplayBeOverridden", void 0), a([o.observable], e.prototype, "isLoadingDisplayed", void 0), a([o.observable], e.prototype, "loadingEvent", void 0), a([o.observable], e.prototype, "vars", void 0), a([o.observable], e.prototype, "paths", void 0), a([o.observable], e.prototype, "currentMapUrl", void 0), a([o.observable], e.prototype, "symbolOn", void 0), a([o.observable], e.prototype, "arealOn", void 0), a([o.observable], e.prototype, "hasSymbol", void 0), a([o.observable], e.prototype, "hasAreal", void 0), a([o.observable], e.prototype, "geometryOn", void 0), a([o.observable], e.prototype, "reliefOn", void 0), a([o.observable], e.prototype, "isLiveMap", void 0), a([o.observable], e.prototype, "isLiveActive", void 0), a([o.observable], e.prototype, "modalOpen", void 0), a([o.observable], e.prototype, "modalTime", void 0), a([o.observable], e.prototype, "topotypeStates", void 0), a([o.observable], e.prototype, "currentRollOver", void 0), a([o.observable], e.prototype, "currentMarkedRegion", void 0), a([o.observable], e.prototype, "snackbarText", void 0), a([o.observable], e.prototype, "snackbarOpen", void 0), a([o.observable], e.prototype, "treeDrawerOpen", void 0), a([o.observable], e.prototype, "isContentTreeVisible", void 0), a([o.observable], e.prototype, "isSearchMenuVisible", void 0), a([o.observable], e.prototype, "windowWidth", void 0), a([o.observable], e.prototype, "windowHeight", void 0), a([o.observable], e.prototype, "appHeaderHeight", void 0), a([o.observable], e.prototype, "bottomBarHeight", void 0), a([o.observable], e.prototype, "mapContentDrawer", void 0), a([o.observable], e.prototype, "mapLegendDrawer", void 0), a([o.observable], e.prototype, "isMapContentVisible", void 0), a([o.observable], e.prototype, "isMapLegendVisible", void 0), a([o.observable], e.prototype, "canMapLegendBeRendered", void 0), a([o.observable], e.prototype, "isBottomLegend", void 0), a([o.observable], e.prototype, "legendWidth", void 0), a([o.observable], e.prototype, "contentWidth", void 0), a([o.observable], e.prototype, "mapContentFillsScreen", void 0), a([o.observable], e.prototype, "dataTable", void 0), a([o.observable], e.prototype, "currentMapIda", void 0), a([o.observable], e.prototype, "mapPath", void 0), a([o.observable], e.prototype, "actualZoomFactor", void 0), a([o.observable], e.prototype, "adaptedRulerWidth", void 0), a([o.observable], e.prototype, "panningActivated", void 0), a([o.observable], e.prototype, "isFullScreen", void 0), a([o.observable], e.prototype, "upperMapContentHeight", void 0), a([o.observable], e.prototype, "legendContainerHeight", void 0), a([o.observable], e.prototype, "searchMenuWidth", void 0), a([o.observable], e.prototype, "searchResult", void 0), a([o.observable], e.prototype, "foundMapsSearchResult", void 0), a([o.observable], e.prototype, "projectListSearchResult", void 0), a([o.observable], e.prototype, "unitListSearchResult", void 0), a([o.observable], e.prototype, "animationAvailable", void 0), a([o.observable], e.prototype, "animationStarted", void 0), a([o.observable], e.prototype, "showAutomationControls", void 0), a([o.observable], e.prototype, "automationControlsMinimized", void 0), a([o.action], e.prototype, "setTopoState", null), a([o.computed], e.prototype, "isSymbolOn", null), a([o.action], e.prototype, "setSymbolState", null), a([o.computed], e.prototype, "isGeometryOn", null), a([o.action], e.prototype, "setGeometryState", null), a([o.computed], e.prototype, "isReliefOn", null), a([o.action], e.prototype, "setReliefState", null), a([o.computed], e.prototype, "isArealOn", null), a([o.action], e.prototype, "setArealState", null), a([o.action], e.prototype, "setSnackbarText", null), a([o.computed], e.prototype, "snackbar", null), a([o.action], e.prototype, "addDatatableItem", null), a([o.computed], e.prototype, "loadingState", null), a([o.action], e.prototype, "setIsLoading", null), a([o.action], e.prototype, "setNotLoading", null), e
				}();
			i.default = new e
		}).call(this, l(31))
	},
	148: function (e, r, i) {
		"use strict";
		(function (s) {
			var t = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
			Object.defineProperty(r, "__esModule", {
				value: !0
			});
			var a = i(18),
				o = i(25),
				n = i(39),
				u = i(19),
				c = i(17),
				d = i(30),
				p = i(757),
				f = i(20),
				h = i(10),
				e = function () {
					function e() {
						this.vectorSymbols = [], this.client = n.default.create({
							baseURL: _WWW,
							responseType: "document"
						})
					}
					return e.prototype.has = function (e) {
						return d.bajoodooLog("VectorSymbolStore.has(" + e + ")"), this.vectorSymbols["_" + e] instanceof p.VectorSymbol ? 1 != this.vectorSymbols["_" + e].isLoading : (this.load(e), !1)
					}, e.prototype.get = function (e) {
						if (d.bajoodooLog("VectorSymbolStore.get(" + e + ")"), this.vectorSymbols["_" + e] instanceof p.VectorSymbol && 1 != this.vectorSymbols["_" + e].isLoading) return this.vectorSymbols["_" + e];
						throw d.bajoodooLog("VectorSymbolStore.get(" + e + ") throws error"), new Error("vectorSymbol not available")
					}, e.prototype.load = function (r) {
						var i = this;
						if (this.vectorSymbols["_" + r] instanceof p.VectorSymbol && 1 == this.vectorSymbols["_" + r].isLoading) d.bajoodooLog("VectorSymbolStore.load(" + r + ") - already loading!!!");
						else {
							d.bajoodooLog("VectorSymbolStore.load(" + r + ")");
							var e = c.default.localization,
								t = {
									ida: r,
									svg: null,
									path: null,
									size: null,
									isLoading: !0
								};
							this.vectorSymbols["_" + r] = new p.VectorSymbol(t);
							var l = {
								id: r.toString(),
								type: a.LOADING_TYPE_VECTORSYMBOLS,
								message: e.messages.LOADING_VECTORSYMBOL + [" (", r, ")"].join("")
							};
							h.default.setIsLoading(l), this.client.get(h.default.paths._JSON + u.default.projectIda + "/vectorSymbols/" + r + ".svg", {
								onDownloadProgress: function (e) {
									l.percent = Math.round(100 * e.loaded / e.total), h.default.setLoadingPercent(l)
								}
							}).then(function (e) {
								var t = s(s(e.data)[0].childNodes)[1].outerHTML || (new XMLSerializer).serializeToString(s(s(e.data)[0].childNodes)[1]),
									a = {
										ida: r,
										svg: t.replace("../../../../img/", _IMG),
										path: null,
										size: null,
										isLoading: !1
									},
									o = a.svg.indexOf('viewBox="0 0 '),
									n = a.svg.substr(o + 13, 16).split(" ");
								a.size = parseInt(n[0]), a.path = s(s(a.svg)[0].childNodes[3]).attr("d").trim(), i.vectorSymbols["_" + r] = new p.VectorSymbol(a), f.default.vectorSymbolLoaded(), h.default.setNotLoading(l)
							}).catch(function (e) {
								throw d.bajoodooLog(e), new Error("vectorSymbol loader error")
							})
						}
					}, t([o.observable], e.prototype, "vectorSymbols", void 0), t([o.action], e.prototype, "load", null), e
				}();
			r.default = new e
		}).call(this, i(31))
	},
	149: function (e, t, n) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var r = n(10),
			i = n(39),
			l = n(19),
			s = n(58),
			u = n(18),
			c = n(17),
			a = function () {
				function e() {
					this.patterns = [], this.pending = !1, this.queue = []
				}
				return e.prototype.loadPattern = function (t) {
					var a = this;
					if (!0 === this.pending) return this.queue.indexOf(t) < 0 && this.queue.push(t), !1;
					this.pending = !0;
					var e = i.default.create({
						baseURL: _WWW,
						responseType: "json"
					});
					if (this.hasPattern(t)) return this.finishLoading(t), !0;
					var o = {
						id: t.toString(),
						type: u.LOADING_TYPE_PATTERN,
						message: c.default.localization.messages.LOADING_PROJECT_STRUCTURE + [" (", t, ")"].join("")
					};
					return r.default.setIsLoading(o), e.get(r.default.paths._JSON + l.default.projectIda + "/patterns/" + t + ".json?" + n.h, {
						onDownloadProgress: function (e) {
							o.percent = Math.round(100 * e.loaded / e.total), r.default.setLoadingPercent(o)
						}
					}).then(function (e) {
						a.patterns.push({
							ida: t,
							elements: e.data
						}), a.finishLoading(t)
					}).catch(function (e) {
						return a.finishLoading(t), !1
					}), !1
				}, e.prototype.finishLoading = function (e) {
					r.default.setNotLoading({
						id: e.toString(),
						type: u.LOADING_TYPE_PATTERN
					}), -1 < this.queue.indexOf(e) && this.queue.splice(this.queue.indexOf(e), 1), this.pending = !1, r.default.emitter.emit("PATTERN" + e, {
						pattern_ida: e
					}), r.default.emitter.emit(u.PATTERN_LOADED), 0 < this.queue.length ? this.loadPattern(this.queue.shift()) : r.default.emitter.emit(u.PATTERN_QUEUE_FINISHED)
				}, e.prototype.clearQueue = function () {
					this.queue = []
				}, e.prototype.hasPattern = function (t) {
					return 0 < this.patterns.filter(function (e) {
						return e.ida == t
					}).length
				}, e.prototype.getPattern = function (t) {
					if (this.hasPattern(t)) {
						var e = this.patterns.filter(function (e) {
							return e.ida == t
						});
						return r.default.emitter.emit("PATTERN" + t, {
							pattern_ida: t
						}), r.default.emitter.emit(u.PATTERN_LOADED), e[0]
					}
					this.loadPattern(t)
				}, e.prototype.triggerCurrentPattern = function (t) {
					var a = this,
						o = t[0];
					if (this.hasPattern(o)) {
						var e = this.getPattern(o);
						this.currentPattern = r.default.currentPattern = e, this.findCurrentPatternString(t)
					} else r.default.emitter.once("PATTERN" + o, function () {
						var e = a.getPattern(o);
						a.currentPattern = r.default.currentPattern = e, a.findCurrentPatternString(t)
					}), this.loadPattern(o)
				}, e.prototype.findCurrentPatternString = function (e) {
					var a = e.join("_"),
						t = this.currentPattern.elements.filter(function (e, t) {
							return -1 < e.indexOf(a)
						});
					t.length ? this.currentPatternString = t[0] : this.currentPatternString = this.currentPattern.elements[0];
					var o = this.currentPatternString.split("_").reverse().map(function (e) {
						return parseInt(e)
					});
					s.default.setCurrentStructures(o), r.default.emitter.emit(u.CURRENT_STRUCTURE_UPDATED)
				}, e
			}();
		t.default = new a
	},
	150: function (e, t, o) {
		"use strict";
		(function (p) {
			Object.defineProperty(t, "__esModule", {
				value: !0
			});
			var s = o(18),
				g = o(30),
				a = o(79),
				y = o(19),
				v = o(10),
				b = o(20),
				e = function () {
					function e() {
						this.lastRightClickTime = null, this.lastTouchTime = null, this.lastTouchPosition = null, this.lastPanningPosition = null, this.lastMultiTouchCenterPosition = null, this.lastMultiTouchDistance = null
					}
					return e.prototype.cancelBubble = function (e, t) {
						g.bajoodooLog("cancelBubble", t), e.stopPropagation ? e.stopPropagation() : e.cancelBubble = !0, e.preventDefault ? e.preventDefault() : e.returnValue = !1
					}, e.prototype.checkMapInViewport = function () {
						try {
							var e = v.default.actualViewBox,
								t = Number(p("#bg").attr("x")),
								a = Number(p("#bg").attr("y")),
								o = Number(p("#bg").attr("width")) + Number(p("#bg").attr("x")),
								n = Number(p("#bg").attr("height")) + Number(p("#bg").attr("y")),
								r = b.default.current.geometry,
								i = document.getElementById("geometry_" + r),
								l = i.createSVGPoint(),
								s = p(window).width(),
								u = window.innerHeight - v.default.appHeaderHeight - v.default.bottomBarHeight;
							if (g.bajoodooLog("checkMapInViewport WH", window.innerHeight, v.default.appHeaderHeight, v.default.bottomBarHeight, u), u <= 0) return !1;
							l.x = s / 2, l.y = v.default.appHeaderHeight + u / 2;
							var c = l.matrixTransform(i.getScreenCTM().inverse()),
								d = !1;
							return t > c.x ? (e.x -= c.x - t, d = !0, g.bajoodooLog("ThematicalMapTools.checkMapInViewport('rightError')", e.x)) : o < c.x && (e.x -= c.x - o, d = !0, g.bajoodooLog("ThematicalMapTools.checkMapInViewport('leftError')", e.x)), a > c.y ? (e.y -= c.y - a, d = !0, g.bajoodooLog("ThematicalMapTools.checkMapInViewport('bottomError')", e.y)) : n < c.y && (e.y -= c.y - n, d = !0, g.bajoodooLog("ThematicalMapTools.checkMapInViewport('topError')", e.y)), !0 === d && (g.bajoodooLog("checkMapInViewport", e, c, l), this.setViewBox(e, !1)), !0
						} catch (e) {
							return !1
						}
					}, e.prototype.getDistance = function (e, t) {
						try {
							return Math.sqrt(Math.pow(Math.abs(e.x - t.x), 2) + Math.pow(Math.abs(e.y - t.y), 2))
						} catch (e) {
							return 99999999
						}
					}, e.prototype.getInTheMiddle = function (e, t) {
						return {
							x: (e.x + t.x) / 2,
							y: (e.y + t.y) / 2
						}
					}, e.prototype.setViewBox = function (e, t) {
						if (void 0 === t && (t = !0), g.bajoodooLog("ThematicalMapTools.setViewBox()"), void 0 !== b.default.current) {
							var a = b.default.current.geometry,
								o = p("#geometry_" + a);
							o.exists() && o[0].setAttribute("viewBox", e.x + " " + e.y + " " + e.width + " " + e.height), v.default.actualViewBox = e, v.default.actualViewBox === v.default.initialViewBox ? p("#restore").css("color", "rgba(150, 150, 150, 0.87)") : p("#restore").css("color", "rgba(0, 0, 0, 0.87)"), v.default.actualZoomFactor === y.default.preset.minZoomFactor ? p("#zoomOut").css("color", "rgba(150, 150, 150, 0.87)") : p("#zoomOut").css("color", "rgba(0, 0, 0, 0.87)"), v.default.actualZoomFactor === y.default.preset.maxZoomFactor ? p("#zoomIn").css("color", "rgba(150, 150, 150, 0.87)") : p("#zoomIn").css("color", "rgba(0, 0, 0, 0.87)"), !0 === t && this.checkMapInViewport()
						}
						return !0
					}, e.prototype.getCenter = function (e, t, a) {
						void 0 === t && (t = null), void 0 === a && (a = !1), g.bajoodooLog("ThematicalMapTools.getCenter()");
						var o = {
							x: null,
							y: null
						};
						if (1 === e) o.x = v.default.actualViewBox.x + v.default.actualViewBox.width / 2, o.y = v.default.actualViewBox.y + v.default.actualViewBox.height / 2;
						else {
							if (2 !== e) throw new Error("no valid type given for ThematicalMapTools.getCenter()");
							var n = b.default.current.geometry,
								r = document.getElementById("geometry_" + n),
								i = r.createSVGPoint();
							i.x = a ? t.center.x : t.clientX, i.y = a ? t.center.y : t.clientY;
							var l = i.matrixTransform(r.getScreenCTM().inverse());
							o.x = l.x, o.y = l.y
						}
						return o
					}, e.prototype.zoomBackToInitial = function (e) {
						void 0 === e && (e = !1), g.bajoodooLog("ThematicalMapTools.zoomBackToInitial()"), v.default.actualZoomFactor = 100, this.setViewBox(v.default.initialViewBox, !1), !1 === e && v.default.emitter.emit(s.THEMATICALMAP_ZOOMED)
					}, e.prototype._panToRegion = function (e) {
						g.bajoodooLog("ThematicalMapTools.panToRegion(" + e + ")");
						var t = Number(p("#" + e).attr("fallbackcenterx")),
							a = Number(p("#" + e).attr("fallbackcentery")),
							o = {
								x: t - v.default.initialTransformMatrix.m5 + v.default.actualViewBox.width / 2,
								y: -(a - v.default.initialTransformMatrix.m6 + v.default.actualViewBox.height / 2),
								width: v.default.actualViewBox.width,
								height: v.default.actualViewBox.height
							};
						this.setViewBox(o)
					}, e.prototype.zoomToRegion = function (e) {
						g.bajoodooLog("ThematicalMapTools.zoomToRegion(" + e + ")"), this.zoomBackToInitial(!0), this._panToRegion(e);
						var t = document.getElementById(e).getBoundingClientRect(),
							a = this.getViewBox(),
							o = a.width / 4 / t.width,
							n = a.height / 4 / t.height,
							r = o < n ? o : n,
							i = 100 * r;
						i > y.default.preset.maxZoomFactor && (r = (i = y.default.preset.maxZoomFactor) / 100), g.bajoodooLog(i + "%");
						var l = {
							x: v.default.actualViewBox.x + (v.default.actualViewBox.width - v.default.actualViewBox.width / r) / 2,
							y: v.default.actualViewBox.y + (v.default.actualViewBox.height - v.default.actualViewBox.height / r) / 2,
							width: v.default.actualViewBox.width / r,
							height: v.default.actualViewBox.height / r
						};
						v.default.actualZoomFactor = i, this.setViewBox(l), v.default.emitter.emit(s.THEMATICALMAP_ZOOMED)
					}, e.prototype.zoomIn = function (e, t, a) {
						g.bajoodooLog("ThematicalMapTools.zoomIn()");
						var o = a / v.default.actualZoomFactor,
							n = {
								x: e - (e - v.default.actualViewBox.x) / o,
								y: t - (t - v.default.actualViewBox.y) / o,
								width: v.default.actualViewBox.width / o,
								height: v.default.actualViewBox.height / o
							};
						v.default.actualZoomFactor = a, this.setViewBox(n), v.default.emitter.emit(s.THEMATICALMAP_ZOOMED)
					}, e.prototype.zoomOut = function (e, t, a) {
						g.bajoodooLog("ThematicalMapTools.zoomOut()");
						var o = v.default.actualZoomFactor / a,
							n = {
								x: e - (e - v.default.actualViewBox.x) * o,
								y: t - (t - v.default.actualViewBox.y) * o,
								width: v.default.actualViewBox.width * o,
								height: v.default.actualViewBox.height * o
							};
						v.default.actualZoomFactor = a, this.setViewBox(n), v.default.emitter.emit(s.THEMATICALMAP_ZOOMED)
					}, e.prototype.zoom = function (e, t, a) {
						g.bajoodooLog("ThematicalMapTools.zoom(" + a + ")"), a > v.default.actualZoomFactor ? this.zoomIn(e, t, a) : a < v.default.actualZoomFactor && this.zoomOut(e, t, a)
					}, e.prototype.getViewBox = function () {
						g.bajoodooLog("ThematicalMapTools.getViewBox()");
						var e = b.default.current.geometry,
							t = p("#geometry_" + e)[0].getAttribute("viewBox");
						return t = t.split(" "), {
							x: Number(t[0]),
							y: Number(t[1]),
							width: Number(t[2]),
							height: Number(t[3])
						}
					}, e.prototype.getTransformMatrix = function () {
						g.bajoodooLog("ThematicalMapTools.getTransformMatrix()");
						var e = b.default.current.geometry,
							t = p("#geo_" + e)[0].getAttribute("transform");
						return t = (t = (t = a.trim(t)).substr(7, t.length - 8)).split(" "), {
							m1: Number(t[0]),
							m2: Number(t[1]),
							m3: Number(t[2]),
							m4: Number(t[3]),
							m5: Number(t[4]),
							m6: Number(t[5])
						}
					}, e.prototype.zoomInButtonClick = function () {
						if (g.bajoodooLog("ThematicalMapTools.zoomInButtonClick()"), v.default.actualZoomFactor < y.default.preset.maxZoomFactor) {
							var e = this.getCenter(1),
								t = v.default.actualZoomFactor * y.default.preset.zoomFactor;
							return t > y.default.preset.maxZoomFactor && (t = y.default.preset.maxZoomFactor), this.zoom(e.x, e.y, t), !0
						}
						return !1
					}, e.prototype.zoomOutButtonClick = function () {
						if (g.bajoodooLog("ThematicalMapTools.zoomOutButtonClick()"), v.default.actualZoomFactor > y.default.preset.minZoomFactor) {
							var e = this.getCenter(1),
								t = v.default.actualZoomFactor / y.default.preset.zoomFactor;
							return t < y.default.preset.minZoomFactor && (t = y.default.preset.minZoomFactor), this.zoom(e.x, e.y, t), !0
						}
						return !1
					}, e.prototype.zoomInDoubleClick = function (e, t) {
						if (void 0 === t && (t = !1), !1 === t && this.cancelBubble(e, "zoomInDoubleClick"), !1 === t && e.button, g.bajoodooLog("ThematicalMapTools.zoomInDoubleClick()"), v.default.actualZoomFactor < y.default.preset.maxZoomFactor) {
							var a = this.getCenter(2, e, !0),
								o = v.default.actualZoomFactor * y.default.preset.zoomFactor;
							return o > y.default.preset.maxZoomFactor && (o = y.default.preset.maxZoomFactor), this.zoom(a.x, a.y, o), !0
						}
						return !1
					}, e.prototype.zoomOutDoubleClick = function (e) {
						if (this.cancelBubble(e, "zoomOutDoubleClick"), 2 !== e.button) return !1;
						if (g.bajoodooLog("ThematicalMapTools.zoomOutDoubleClick()"), v.default.actualZoomFactor > y.default.preset.minZoomFactor) {
							var t = this.getCenter(2, e),
								a = v.default.actualZoomFactor / y.default.preset.zoomFactor;
							return a < y.default.preset.minZoomFactor && (a = y.default.preset.minZoomFactor), this.zoom(t.x, t.y, a), !0
						}
						return !1
					}, e.prototype.zoomInOutWheel = function (e) {
						this.cancelBubble(e, "zoomInOutWheel"), g.bajoodooLog("ThematicalMapTools.zoomInOutWheel()");
						var t, a = this.getCenter(2, e);
						return e.deltaY < 0 && v.default.actualZoomFactor < y.default.preset.maxZoomFactor ? ((t = v.default.actualZoomFactor * y.default.preset.zoomFactorWheel) > y.default.preset.maxZoomFactor && (t = y.default.preset.maxZoomFactor), this.zoom(a.x, a.y, t), !0) : 0 < e.deltaY && v.default.actualZoomFactor > y.default.preset.minZoomFactor && ((t = v.default.actualZoomFactor / y.default.preset.zoomFactorWheel) < y.default.preset.minZoomFactor && (t = y.default.preset.minZoomFactor), this.zoom(a.x, a.y, t), !0)
					}, e.prototype.checkRightClick = function (e) {
						if (this.cancelBubble(e, "checkRightClick"), 2 !== e.button) return !1;
						g.bajoodooLog("ThematicalMapTools.checkRightClick()");
						var t = p.now();
						return null !== this.lastRightClickTime && t - this.lastRightClickTime <= y.default.preset.maxDoubleClickGap ? this.zoomOutDoubleClick(e) : (this.lastRightClickTime = t, !1)
					}, e.prototype.onPinchStart = function (e) {
						g.bajoodooLog("ThematicalMapTools.onPinchStart()");
						var t = e.pointers[0],
							a = e.pointers[1],
							o = {
								x: t.screenX,
								y: t.screenY
							},
							n = {
								x: a.screenX,
								y: a.screenY
							};
						return this.lastMultiTouchCenterPosition = this.getInTheMiddle(o, n), this.lastMultiTouchDistance = this.getDistance(o, n), v.default.panningActivated = !0
					}, e.prototype.onPinchEnd = function (e) {
						return g.bajoodooLog("ThematicalMapTools.onPinchEnd()"), !(v.default.panningActivated = !1)
					}, e.prototype.onPanStart = function (e) {
						if (g.bajoodooLog("ThematicalMapTools.onPanStart()"), this.lastPanningPosition = {
								x: e.center.x,
								y: e.center.y
							}, v.default.panningActivated = !0, void 0 !== b.default.current) {
							var t = b.default.current.geometry;
							p("#geometry_" + t).css("cursor", "move")
						}
						return !1
					}, e.prototype.onPanEnd = function (e) {
						if (g.bajoodooLog("ThematicalMapTools.onPanEnd()"), this.lastPanningPosition = null, v.default.panningActivated = !1, void 0 !== b.default.current) {
							var t = b.default.current.geometry;
							p("#geometry_" + t).css("cursor", "default")
						}
						return !1
					}, e.prototype.onPinch = function (e) {
						g.bajoodooLog("ThematicalMapTools.onPinch()");
						var t = e.pointers[0],
							a = e.pointers[1],
							o = {
								x: t.screenX,
								y: t.screenY
							},
							n = {
								x: a.screenX,
								y: a.screenY
							},
							r = this.getInTheMiddle(o, n),
							i = this.getDistance(o, n),
							l = i / this.lastMultiTouchDistance;
						if (void 0 !== b.default.current) {
							var s = b.default.current.geometry,
								u = document.getElementById("geometry_" + s),
								c = u.createSVGPoint();
							c.x = this.lastMultiTouchCenterPosition.x, c.y = this.lastMultiTouchCenterPosition.y;
							var d = u.createSVGPoint();
							d.x = r.x, d.y = r.y;
							var p = c.matrixTransform(u.getScreenCTM().inverse()),
								f = d.matrixTransform(u.getScreenCTM().inverse()),
								h = {
									x: v.default.actualViewBox.x - (f.x - p.x),
									y: v.default.actualViewBox.y - (f.y - p.y),
									width: v.default.actualViewBox.width,
									height: v.default.actualViewBox.height
								};
							if (this.setViewBox(h), this.lastMultiTouchDistance = i, 1 < l || l < 1) {
								var m = v.default.actualZoomFactor * l;
								m < y.default.preset.minZoomFactor && (m = y.default.preset.minZoomFactor), m > y.default.preset.maxZoomFactor && (m = y.default.preset.maxZoomFactor), this.zoom(p.x, p.y, m)
							}
						}
						return this.lastMultiTouchCenterPosition.x = r.x, this.lastMultiTouchCenterPosition.y = r.y, !0
					}, e.prototype.onPan = function (e) {
						if (g.bajoodooLog("ThematicalMapTools.onPan()"), void 0 === b.default.current) return !1;
						var t = b.default.current.geometry,
							a = document.getElementById("geometry_" + t),
							o = a.createSVGPoint();
						o.x = this.lastPanningPosition.x, o.y = this.lastPanningPosition.y;
						var n = a.createSVGPoint();
						n.x = e.center.x, n.y = e.center.y;
						var r = o.matrixTransform(a.getScreenCTM().inverse()),
							i = n.matrixTransform(a.getScreenCTM().inverse()),
							l = {
								x: v.default.actualViewBox.x - (i.x - r.x),
								y: v.default.actualViewBox.y - (i.y - r.y),
								width: v.default.actualViewBox.width,
								height: v.default.actualViewBox.height
							};
						return this.setViewBox(l), this.lastPanningPosition.x = e.center.x, this.lastPanningPosition.y = e.center.y, !0
					}, e.prototype.setFullScreenState = function (e) {
						g.bajoodooLog("ThematicalMapTools.setFullScreenState(" + e + ")"), v.default.isFullScreen = e
					}, e.prototype.getBetterRulerValue = function (e, t) {
						var a;
						void 0 === t && (t = null), g.bajoodooLog("ThematicalMapTools.getBetterRulerValue(" + e + "," + t + ")"), a = null === t ? e * ((100 - y.default.preset.maxRulerDeviation) / 100) : t;
						for (var o = 0; Math.pow(10, ++o) < e;);
						var n, r = Math.pow(10, o);
						if ((n = r / 2) <= e && a <= n) return n;
						if ((n = 3 * r / 4) <= e && a <= n) return n;
						if ((n = r / 4) <= e && a <= n) return n;
						for (var i = 9; 0 < i; i--) {
							if ((n = r * i / 10) <= e) return a <= n ? n : n - -this.getBetterRulerValue(e - n, a - n)
						}
						return g.bajoodooLog("ThematicalMapTools.returnOriginalRulerValue(" + e + ")"), e
					}, e
				}();
			t.default = new e
		}).call(this, o(31))
	},
	17: function (e, t, r) {
		"use strict";
		var i = this && this.__assign || function () {
				return (i = Object.assign || function (e) {
					for (var t, a = 1, o = arguments.length; a < o; a++)
						for (var n in t = arguments[a]) Object.prototype.hasOwnProperty.call(t, n) && (e[n] = t[n]);
					return e
				}).apply(this, arguments)
			},
			a = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var l = r(18),
			s = r(10),
			o = r(39),
			u = r(19),
			n = r(25),
			c = r(30),
			d = r(752),
			p = function () {
				function e() {
					this.localizations = [], this.myCurrentLocalizationIda = null, this.myCurrentRealLocalizationIda = null, this.isLoading = !1, this.client = o.default.create({
						baseURL: _WWW,
						responseType: "json"
					})
				}
				return e.prototype.loadLocalization = function (a) {
					var o = this;
					if (c.bajoodooLog("LocalizationStore.loadLocalization(" + a + ")"), this.hasRealLocalization(a)) {
						s.default.emitter.emit("LANGUAGE_LOADED");
						var e = this.localizations.filter(function (e) {
							return e.realLocId === a
						});
						if (e.length) return e[0]
					}
					if (!this.isLoading) {
						this.isLoading = !0;
						var n = {
							id: a.toString(),
							type: l.LOADING_TYPE_LOCALIZATION,
							message: "Loading Language"
						};
						s.default.setIsLoading(n), this.client.get(s.default.paths._JSON + "language/" + a + ".json?" + r.h).then(function (e) {
							var t = u.default.language.ida;
							return o.currentLocalization = i({
								ida: t
							}, e.data[t]), o.setCurrentLocalizationIda(t), o.setCurrentRealLocalizationIda(a), o.addLocalization(o.currentLocalization), s.default.setNotLoading(n), s.default.emitter.emit("LANGUAGE_LOADED"), s.default.emitter.emit(l.LEGEND_RERENDER), o.isLoading = !1, o.currentLocalization
						}).catch(function (e) {
							this.isLoading = !1, c.bajoodooLog(e)
						})
					}
				}, e.prototype.changeLanguage = function (t) {
					var e = this;
					if (this.hasRealLocalization(t)) {
						var a = this.localizations.filter(function (e) {
							return e.realLocId === t
						});
						0 < a.length && (this.currentLocalization = a[0], this.setCurrentLocalizationIda(this.currentLocalization.ida), this.setCurrentRealLocalizationIda(t), s.default.emitter.emit("LANGUAGE_LOADED"))
					} else s.default.emitter.once("LANGUAGE_LOADED", function () {
						e.changeLanguage(t)
					}), console.log(t), this.loadLocalization(t)
				}, Object.defineProperty(e.prototype, "localization", {
					get: function () {
						return void 0 !== this.currentLocalization && this.currentLocalization.hasOwnProperty("messages") ? this.currentLocalization : new d.Localization({
							code: "",
							codeShort: "",
							errors: {},
							ida: 0,
							indicators: {},
							languages: {},
							messages: {},
							name: "",
							properties: {},
							realLocId: 0
						})
					},
					enumerable: !0,
					configurable: !0
				}), e.prototype.hasRealLocalization = function (t) {
					return 0 < this.localizations.filter(function (e) {
						return e.realLocId === t
					}).length
				}, e.prototype.hasLocalization = function (t) {
					return 0 < this.localizations.filter(function (e) {
						return e.ida === t
					}).length
				}, e.prototype.addLocalization = function (e) {
					this.hasLocalization(e.realLocId) || this.localizations.push(i({}, e))
				}, Object.defineProperty(e.prototype, "currentLocalizationIda", {
					get: function () {
						return this.myCurrentLocalizationIda
					},
					enumerable: !0,
					configurable: !0
				}), e.prototype.setCurrentLocalizationIda = function (e) {
					this.myCurrentLocalizationIda = e
				}, Object.defineProperty(e.prototype, "currentRealLocalizationIda", {
					get: function () {
						return this.myCurrentRealLocalizationIda
					},
					enumerable: !0,
					configurable: !0
				}), e.prototype.setCurrentRealLocalizationIda = function (e) {
					this.myCurrentRealLocalizationIda = e
				}, a([n.observable], e.prototype, "myCurrentLocalizationIda", void 0), a([n.observable], e.prototype, "myCurrentRealLocalizationIda", void 0), a([n.observable], e.prototype, "currentLocalization", void 0), a([n.computed], e.prototype, "localization", null), a([n.action], e.prototype, "hasLocalization", null), a([n.action], e.prototype, "addLocalization", null), a([n.computed], e.prototype, "currentLocalizationIda", null), a([n.action], e.prototype, "setCurrentLocalizationIda", null), a([n.computed], e.prototype, "currentRealLocalizationIda", null), a([n.action], e.prototype, "setCurrentRealLocalizationIda", null), e
			}();
		t.default = new p
	},
	18: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		}), t.DESIGN_LOADED = "DESIGN_LOADED", t.DESIGN_QUEUE_FINISHED = "DESIGN_QUEUE_FINISHED", t.GEOMETRYLABELS_AVAILABLE = "GEOMETRYLABELS_AVAILABLE", t.PROJECT_UPDATE = "PROJECT_UPDATE", t.MAP_UPDATE = "MAP_UPDATE", t.SEARCHINDEX_AVAILABLE = "SEARCHINDEX_AVAILABLE", t.PATTERN_LOADED = "PATTERN_LOADED", t.PATTERN_QUEUE_FINISHED = "PATTERN_QUEUE_FINISHED", t.LANGUAGE_LOADED = "LANGUAGE_LOADED", t.STRUCTURE_LOADED = "STRUCTURE_LOADED", t.STRUCTURE_UPDATED = "STRUCTURE_UPDATED", t.STRUCTURE_QUEUE_FINISHED = "STRUCTURE_QUEUE_FINISHED", t.STRUCTURE_TREE_TOGGLED = "STRUCTURE_TREE_TOGGLED", t.STRUCTURE_TREE_ROLLOVER = "STRUCTURE_TREE_ROLLOVER", t.CURRENT_STRUCTURE_UPDATED = "CURRENT_STRUCTURE_UPDATED", t.THEMATICALMAP_LOADED = "THEMATICALMAP_LOADED", t.THEMATICALMAP = "THEMATICALMAP_", t.THEMATICALMAP_ZOOMED = "THEMATICALMAP_ZOOMED", t.THEMATICALMAP_GEOMETRY_CHANGED = "THEMATICALMAP_GEOMETRY_CHANGED", t.THEMATICALMAP_GENERALLY_AVAILABLE = "THEMATICALMAP_GENERALLY_AVAILABLE", t.LEGEND_DONE = "LEGEND_DONE", t.TOPOGRAPHY_LOADED = "TOPOGRAPHY_LOADED", t.REGION_ROLLOVER = "REGION_ROLLOVER", t.PROJECTS_LOADED = "PROJECTS_LOADED", t.LIVE_MAP_DATA_AVAILABLE = "LIVE_MAP_DATA_AVAILABLE", t.LEGEND_RERENDER = "LEGEND_RERENDER", t.REACT_TOOLTIP = "REACT_TOOLTIP", t.INFO_VALUES_AVAILABLE = "INFO_VALUES_AVAILABLE", t.VISUALIZATION_CHORO = "choro", t.VISUALIZATION_QUALI = "quali", t.VISUALIZATION_SYM = "sym", t.VISUALIZATION_COMBINED = "combined", t.VISUALIZATION_SECT = "sect", t.VISUALIZATION_BAR = "bar", t.VISUALIZATION_TYPE_AREAL = "areal", t.VISUALIZATION_TYPE_SYMBOL = "symbol", t.DATASET_NAME = "name", t.DATASET_SOURCE = "source", t.DATASET_CLASSIFICATION = "classification", t.DATASET_SURVEY_DATE = "survey_date", t.DATASET_SURVEY_DATE_END = "survey_date_end", t.DATASET_VALUE_UNIT = "value_unit", t.DATASET_VALUE_UNIT_LOC = "value_unit_loc", t.DATASET_METADATA = "metadata", t.DATASET_FORMULA = "formula", t.DATASET_VALID_GEOMETRY = "valid_geometry", t.METADATA_CLASS_CALCULATIONS = "2", t.METADATA_CLASS_EUROSTAT = "6", t.METADATA_CLASS_BFS = "7", t.METADATA_CLASS_CITIES = "8", t.METADATA_CLASS_FOOTNOTE = "9", t.UNIT_WAS_CLICKED_IN_UNITLIST = "UNIT_WAS_CLICKED_IN_UNITLIST", t.WINDOW_RESIZED = "WINDOW_RESIZED", t.AUTOMATION_STARTED = "AUTOMATION_STARTED", t.AUTOMATION_STOPPED = "AUTOMATION_STOPPED", t.LIVE_MAP_STOP_CHECKING = "LIVE_MAP_STOP_CHECKING", t.LOADING_TYPE_MAP = "map", t.LOADING_TYPE_DATASET = "dataset", t.LOADING_TYPE_DESIGN = "design", t.LOADING_TYPE_GEOMETRYLABEL = "geometrylabel", t.LOADING_TYPE_PATTERN = "pattern", t.LOADING_TYPE_STRUCTURE = "structure", t.LOADING_TYPE_SEARCHINDEX = "searchindex", t.LOADING_TYPE_GEOMETRY = "geometry", t.LOADING_TYPE_TOPOGRAPHY = "topography", t.LOADING_TYPE_VISUALIZING = "visualizing", t.LOADING_TYPE_PROJECT = "project", t.LOADING_TYPE_PROJECTS = "projects", t.LOADING_TYPE_VECTORSYMBOLS = "vectorsymbols", t.LOADING_TYPE_LOCALIZATION = "localization"
	},
	19: function (e, t, n) {
		"use strict";
		var o = this && this.__assign || function () {
				return (o = Object.assign || function (e) {
					for (var t, a = 1, o = arguments.length; a < o; a++)
						for (var n in t = arguments[a]) Object.prototype.hasOwnProperty.call(t, n) && (e[n] = t[n]);
					return e
				}).apply(this, arguments)
			},
			a = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var r = n(18),
			i = n(750),
			l = n(30),
			s = n(25),
			u = n(17),
			c = n(375),
			d = n(83),
			p = n(39),
			f = n(10),
			h = function () {
				function e() {
					this.projectLoaded = !1, this._DS = "/", this.prodima = [], this.geometries = [], this.geosystems = [], this.topographies = [], this.systemPresets = {
						rollOverLineColors: ["fff", "999", "333"],
						rollOverMaxAlpha: 75,
						rollOverLineWidth: 3,
						maxDistanceDoubleTouch: 50,
						maxDoubleTouchGap: 500,
						maxDoubleClickGap: 300,
						minZoomFactor: 50,
						maxZoomFactor: 1600,
						zoomFactor: 2,
						zoomFactorWheel: 1.25,
						maxRulerDeviation: 20,
						regionMaxFillPercentage: 50
					}, this.client = p.default.create({
						baseURL: _WWW,
						responseType: "json"
					})
				}
				return e.prototype.subscribe = function (e, t) {}, e.prototype.unsubscribe = function (e, t) {}, e.prototype.addProject = function (e) {
					this.project = e
				}, e.prototype.getProject = function () {
					return this.project
				}, e.prototype.addProjectIda = function (e) {
					this.projectId = e
				}, Object.defineProperty(e.prototype, "projectIda", {
					get: function () {
						return this.projectId
					},
					enumerable: !0,
					configurable: !0
				}), e.prototype.setProdima = function (t) {
					var a = this;
					Object.keys(t).map(function (e) {
						return a.prodima.push(o({
							key: e
						}, t[e]))
					})
				}, e.prototype.addGeosystems = function (t) {
					Object.keys(t).map(function (e) {
						return c.default.add(t[e])
					})
				}, Object.defineProperty(e.prototype, "prodimas", {
					get: function () {
						return this.prodima
					},
					enumerable: !0,
					configurable: !0
				}), Object.defineProperty(e.prototype, "report", {
					get: function () {
						return this.prodima
					},
					enumerable: !0,
					configurable: !0
				}), Object.defineProperty(e.prototype, "isProjectLoaded", {
					get: function () {
						return this.projectLoaded
					},
					enumerable: !0,
					configurable: !0
				}), e.prototype.setIsProjectLoaded = function (e) {
					this.projectLoaded = e
				}, e.prototype.loadProject = function () {
					var a = this;
					l.bajoodooLog("ProjectStore.loadProject(" + f.default.vars.projectIda + ")");
					var o = {
						id: f.default.vars.projectIda.toString(),
						type: r.LOADING_TYPE_PROJECT,
						message: "Loading Project"
					};
					f.default.setIsLoading(o), this.client.get(f.default.paths._JSON + f.default.vars.projectIda + "/project.json?" + n.h, {
						onDownloadProgress: function (e) {
							o.percent = Math.round(100 * e.loaded / e.total), f.default.setLoadingPercent(o)
						}
					}).then(function (e) {
						a.addProjectIda(e.data.project), a.addProject(new i.Project(e.data)), a.refactorProjectLanguages(), a.setProdima(e.data.prodima), a.addGeosystems(e.data.geosystems);
						var t = a.getProject().languages.filter(function (e) {
							return e.codeShort === f.default.vars.lngShort
						})[0];
						a.setLanguage(t), d.default.loadDesign(a.getProject().design), u.default.loadLocalization(t.realLocId), a.setIsProjectLoaded(!0), f.default.setNotLoading(o)
					}).catch(function (e) {
						l.bajoodooLog(e.response)
					})
				}, e.prototype.refactorProjectLanguages = function () {
					var t = this.project,
						a = o({}, t.languages);
					t.languages = [], Object.keys(a).map(function (e) {
						return t.languages.push(o({
							ida: e
						}, a[e]))
					})
				}, Object.defineProperty(e.prototype, "language", {
					get: function () {
						return this.currentLanguage
					},
					enumerable: !0,
					configurable: !0
				}), e.prototype.setLanguage = function (e) {
					this.currentLanguage = e
				}, Object.defineProperty(e.prototype, "preset", {
					get: function () {
						return this.systemPresets
					},
					enumerable: !0,
					configurable: !0
				}), a([s.observable], e.prototype, "projectLoaded", void 0), a([s.observable], e.prototype, "projectId", void 0), a([s.observable], e.prototype, "prodima", void 0), a([s.observable], e.prototype, "geometries", void 0), a([s.observable], e.prototype, "geosystems", void 0), a([s.observable], e.prototype, "topographies", void 0), a([s.observable], e.prototype, "currentLanguage", void 0), a([s.computed], e.prototype, "projectIda", null), a([s.computed], e.prototype, "report", null), a([s.computed], e.prototype, "isProjectLoaded", null), a([s.action], e.prototype, "setIsProjectLoaded", null), a([s.computed], e.prototype, "language", null), a([s.action], e.prototype, "setLanguage", null), e
			}();
		t.default = new h
	},
	20: function (e, O, I) {
		"use strict";
		(function (o) {
			var t = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
			Object.defineProperty(O, "__esModule", {
				value: !0
			});
			var l = I(18),
				n = I(122),
				s = I(10),
				u = I(48),
				c = I(210),
				d = I(759),
				r = I(84),
				p = I(17),
				a = I(149),
				i = I(212),
				f = I(19),
				h = I(58),
				m = I(213),
				g = I(378),
				y = I(150),
				v = I(763),
				b = I(214),
				_ = I(148),
				T = I(25),
				E = I(30),
				S = I(379),
				L = I(764),
				e = function () {
					function e() {
						var t = this;
						this.availableVisMethods = {
							areal: ["choro", "quali"],
							symbolic: ["sym", "sect", "bar"]
						}, s.default.emitter.once(l.LANGUAGE_LOADED, function () {
							s.default.emitter.once(l.STRUCTURE_LOADED, function () {
								a.default.triggerCurrentPattern(s.default.vars.pattern), t.loadThematicalMap(s.default.vars.mapIda)
							}), h.default.prepareRootStructure(), i.default.loadProjects()
						}), s.default.emitter.on(l.THEMATICALMAP_LOADED, function () {
							if (void 0 !== t.current && void 0 !== h.default.getCurrentNode()) {
								var e = new S.LinksAndTitles;
								t.current.getCurrentMapTitle(), e.renderShortUrl(!0)
							}
						})
					}
					return e.prototype.loadThematicalMap = function (e, t) {
						var a = this;
						if (void 0 === t && (t = !1), E.bajoodooLog("ThematicalMapsCentral.loadThematicalMap(" + e + ")"), t || (s.default.animationStarted = !1), void 0 !== this.current && null !== this.current) {
							if (e == this.current.ida) return;
							this.lastGeosystemId = this.current.geosystem
						}
						s.default.canMapLegendBeRendered = !1;
						var o = {
							id: e.toString(),
							type: l.LOADING_TYPE_VISUALIZING,
							percent: null,
							message: p.default.localization.messages.VISUALISING
						};
						s.default.setIsLoading(o);
						if (g.default.has(e, !1)) {
							var n = g.default.get(e),
								r = f.default.getProject().geometries[n.geometry].vg;
							if (c.default.has(r, !1)) {
								var i = c.default.get(r);
								p.default.localization.ida;
								i.length
							}
						}
						setTimeout(function () {
							1 == g.default.has(e) && a.processThematicalMap(e)
						}, 2)
					}, Object.defineProperty(e.prototype, "current", {
						get: function () {
							return this.currentThematicalMap
						},
						enumerable: !0,
						configurable: !0
					}), e.prototype.unsetCurrent = function () {
						return this.currentThematicalMap = void 0, u.default.unsetDataInfo(), this
					}, Object.defineProperty(e.prototype, "currentIda", {
						get: function () {
							return this.currentThematicalMap.ida
						},
						enumerable: !0,
						configurable: !0
					}), e.prototype.renderThematicalMap = function (e) {
						E.bajoodooLog("ThematicalMapsCentral.renderThematicalMap(" + e + ")"), !1 !== r.default.isUndefined(this.currentGeometry) || s.default.animationStarted || this.lastGeosystemId === this.current.geosystem || (y.default.zoomBackToInitial(!0), s.default.actualZoomFactor = 100), s.default.currentGeometryIda = this.current.geometry, s.default.currentMapIda = this.currentMapId = this.current.ida, s.default.setSymbolState(!0), s.default.setArealState(!0), this.currentGeometry = d.default.get(this.current.geometry).svg, o("#mapContainer").html(this.currentGeometry), o("#mapContainer>svg").css("padding-top", s.default.appHeaderHeight);
						var t = p.default.localization;
						s.default.currentLocIda = t.ida, s.default.mapTitle = this.current.getCurrentMapTitle(), this.currentLocMapInfo = this.current.getCommonMapInfo(t.ida), m.default.makeInitialChangesToGeometry(), m.default.makeInitialChangesToLegend(), s.default.animationStarted || this.lastGeosystemId === this.current.geosystem ? y.default.setViewBox(s.default.actualViewBox, !1) : (s.default.initialViewBox = s.default.actualViewBox = y.default.getViewBox(), s.default.initialTransformMatrix = y.default.getTransformMatrix()), this.mVis = v.default, this.mVis.update(), this.mVis.showVisualization(), document.title = this.current.getCurrentMapTitle() + " [" + f.default.getProject().getGeometry(this.current.geometry).languages[t.ida].name + "]", s.default.treeDrawerOpen = !1, u.default.initDataInfo(), s.default.isLiveMap = 1 === this.current.properties.is_live_map, s.default.isLiveActive = 1 === this.current.properties.is_live_active, s.default.isMapLegendVisible = !s.default.isLiveMap || s.default.isLiveActive, s.default.isMapContentVisible = !s.default.isMapLegendVisible, this.mLeg = new L.ThematicalMapLegend(this.mVis), this.mLeg.showLegend();
						var a = {
							id: e.toString(),
							type: l.LOADING_TYPE_VISUALIZING
						};
						n.rebuild(), s.default.setNotLoading(a), s.default.emitter.emit(l.THEMATICALMAP_LOADED), s.default.emitter.emit(l.THEMATICALMAP + e), s.default.emitter.emit(l.WINDOW_RESIZED)
					}, e.prototype.showLegend = function () {
						void 0 !== this.mLeg && this.mLeg.showLegend()
					}, e.prototype.checkThematicalMapParts = function (e) {
						if (E.bajoodooLog("ThematicalMapsCentral.checkThematicalMapParts(" + e + ")"), null == e ? e = this.currentIda : e != this.currentIda && (this.currentThematicalMap = g.default.get(e)), E.bajoodooLog("ThematicalMapsCentral.checkThematicalMapParts(" + e + ")"), 0 == this.current.allNecessaryPartsLoaded) {
							for (var t in this.availableVisMethods)
								for (var a in this.availableVisMethods[t]) {
									var o = this.availableVisMethods[t][a];
									if (void 0 !== this.current.visualizations[o]) {
										for (var n in this.current.visualizations[o].datasets)
											if (0 == u.default.has(parseInt(n))) return E.bajoodooLog("waiting for dataset!"), !1;
										if ("sym" == o && void 0 !== this.current.visualizations[o].fill) {
											for (var n in this.current.visualizations[o].fill.datasets)
												if (0 == u.default.has(parseInt(n))) return E.bajoodooLog("waiting for combined dataset!"), !1
										} else if ("sym" == o && 4 == this.current.visualizations[o].spec.syms) {
											if (0 == _.default.has(this.current.visualizations[o].spec.vectorSymbol)) return E.bajoodooLog("waiting for vector symbol!"), !1
										} else if ("sect" == o) {
											for (var r in this.current.visualizations[o].sectors)
												if (n = this.current.visualizations[o].sectors[r].dataset, 0 == u.default.has(parseInt(n))) return E.bajoodooLog("waiting for sect dataset!"), !1
										} else if ("bar" == o)
											for (var r in this.current.visualizations[o].bars)
												if (n = this.current.visualizations[o].bars[r].dataset, 0 == u.default.has(parseInt(n))) return E.bajoodooLog("waiting for bar dataset!"), !1
									}
								}
							for (var i in this.current.topographies) {
								var l = parseInt(i.substr(1));
								if (1 === this.current.topographies["_" + l] && 0 == b.default.has(l, f.default.getProject().topographies[i].properties.type)) return E.bajoodooLog("waiting for topography!"), !1
							}
							if (0 == c.default.has(f.default.getProject().geometries[this.current.geometry].vg)) return E.bajoodooLog("waiting for geometryLabel!"), !1;
							if (0 == d.default.has(this.current.geometry)) return E.bajoodooLog("waiting for geometry!"), !1
						}
						this.renderThematicalMap(e)
					}, e.prototype.processThematicalMap = function (e) {
						for (var t in E.bajoodooLog("ThematicalMapsCentral.processThematicalMap(" + e + ")"), this.currentThematicalMap = g.default.get(e), d.default.has(this.current.geometry), c.default.has(f.default.getProject().geometries[this.current.geometry].vg), this.current.topographies) {
							var a = parseInt(t.substr(1));
							1 === this.current.topographies[a] && b.default.has(a, f.default.getProject().topographies[t].properties.type)
						}
						for (var o in this.availableVisMethods)
							for (var n in this.availableVisMethods[o]) {
								var r = this.availableVisMethods[o][n];
								if (void 0 !== this.current.visualizations[r]) {
									for (var i in this.current.visualizations[r].datasets) u.default.has(parseInt(i));
									if ("sym" == r && void 0 !== this.current.visualizations[r].fill)
										for (var i in this.current.visualizations[r].fill.datasets) u.default.has(parseInt(i));
									else if ("sym" == r && 4 == this.current.visualizations[r].spec.syms) _.default.has(this.current.visualizations[r].spec.vectorSymbol);
									else if ("sect" == r)
										for (var l in this.current.visualizations[r].sectors) i = this.current.visualizations[r].sectors[l].dataset, u.default.has(parseInt(i));
									else if ("bar" == r)
										for (var l in this.current.visualizations[r].bars) i = this.current.visualizations[r].bars[l].dataset, u.default.has(parseInt(i))
								}
							}
						this.checkThematicalMapParts(null)
					}, e.prototype.thematicalMapLoaded = function (e) {
						E.bajoodooLog("ThematicalMapsCentral.thematicalMapLoaded(" + e + ")"), this.processThematicalMap(e)
					}, e.prototype.geometryLoaded = function () {
						E.bajoodooLog("ThematicalMapsCentral.geometryLoaded()"), this.checkThematicalMapParts(null)
					}, e.prototype.geometryLabelLoaded = function () {
						E.bajoodooLog("ThematicalMapsCentral.geometryLabelLoaded()"), this.checkThematicalMapParts(null)
					}, e.prototype.topographyLoaded = function () {
						E.bajoodooLog("ThematicalMapsCentral.topographyLoaded()"), this.checkThematicalMapParts(null)
					}, e.prototype.datasetLoaded = function () {
						E.bajoodooLog("ThematicalMapsCentral.datasetLoaded()"), this.checkThematicalMapParts(null)
					}, e.prototype.vectorSymbolLoaded = function () {
						E.bajoodooLog("ThematicalMapsCentral.vectorSymbolLoaded()"), this.checkThematicalMapParts(null)
					}, e.prototype.redrawSymbols = function () {
						E.bajoodooLog("ThematicalMapsCentral.redrawSymbols()"), void 0 !== this.mVis && this.mVis.redrawSymbols(), "" !== s.default.currentRollOver && m.default.createRegionRollover(s.default.currentRollOver), 0, 0
					}, t([T.observable], e.prototype, "currentThematicalMap", void 0), t([T.action], e.prototype, "loadThematicalMap", null), t([T.computed], e.prototype, "current", null), t([T.action], e.prototype, "unsetCurrent", null), e
				}();
			O.default = new e
		}).call(this, I(31))
	},
	210: function (e, t, i) {
		"use strict";
		var a = this && this.__decorate || function (e, t, a, o) {
			var n, r = arguments.length,
				i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
			if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
			else
				for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
			return 3 < r && i && Object.defineProperty(t, a, i), i
		};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var l = i(10),
			o = i(39),
			s = i(17),
			u = i(19),
			c = i(20),
			n = i(25),
			d = i(30),
			p = i(758),
			f = i(18),
			r = function () {
				function e() {
					this.geometryLabels = [], this.client = o.default.create({
						baseURL: _WWW,
						responseType: "json"
					})
				}
				return e.prototype.has = function (e, t) {
					return void 0 === t && (t = !0), d.bajoodooLog("GeometryLabelStore.has(" + e + ")"), this.geometryLabels["_" + e] instanceof p.GeometryLabel ? 1 != this.geometryLabels["_" + e].isLoading && (l.default.emitter.emit(f.GEOMETRYLABELS_AVAILABLE, {
						geometryLabelIda: e
					}), !0) : (t && this.load(e), !1)
				}, e.prototype.get = function (e) {
					if (d.bajoodooLog("GeometryLabelStore.get(" + e + ")"), this.geometryLabels["_" + e] instanceof p.GeometryLabel && 1 != this.geometryLabels["_" + e].isLoading) return this.geometryLabels["_" + e];
					throw d.bajoodooLog("GeometryLabelStore.get(" + e + ") throws error"), new Error("geometryLabel not available")
				}, e.prototype.load = function (a) {
					var o = this;
					if (this.geometryLabels["_" + a] instanceof p.GeometryLabel && 1 == this.geometryLabels["_" + a].isLoading) d.bajoodooLog("GeometryLabelStore.load(" + a + ") - already loading!!!");
					else {
						d.bajoodooLog("GeometryLabelStore.load(" + a + ")");
						var n = s.default.localization,
							e = {
								ida: a,
								labels: {},
								isLoading: !0
							};
						this.geometryLabels["_" + a] = new p.GeometryLabel(e);
						var r = {
							id: a.toString(),
							type: f.LOADING_TYPE_GEOMETRYLABEL,
							message: n.messages.LOADING_GEOMETRY + [" (", a, ")"].join("")
						};
						l.default.setIsLoading(r), this.client.get(l.default.paths._JSON + u.default.projectIda + "/labels/" + a + ".json?" + i.h, {
							onDownloadProgress: function (e) {
								r.percent = Math.round(100 * e.loaded / e.total), l.default.setLoadingPercent(r)
							}
						}).then(function (e) {
							var t = {
								ida: a,
								labels: e.data[a],
								isLoading: !1,
								length: void 0 !== e.data[a][n.ida] ? Object.keys(e.data[a][n.ida]).length : null
							};
							o.geometryLabels["_" + a] = new p.GeometryLabel(t), c.default.geometryLabelLoaded(), l.default.setNotLoading(r), l.default.emitter.emit(f.GEOMETRYLABELS_AVAILABLE, {
								geometryLabelIda: a
							})
						}).catch(function (e) {
							throw d.bajoodooLog(e), new Error("geometryLabel loader error")
						})
					}
				}, e.prototype.getLabel = function (e, t) {
					try {
						var a = s.default.localization;
						return this.geometryLabels["_" + t].labels[a.ida]["label_" + e].value
					} catch (e) {
						return ""
					}
				}, e.prototype.provideSearchResult = function (e, n) {
					this.clearSearchResult();
					var t = this.get(e),
						a = s.default.localization,
						r = t.labels[a.ida];
					Object.keys(r).map(function (e, t) {
						if (-1 < r[e].value.toLowerCase().indexOf(n.toLowerCase()) && !1 === r[e].pseudo) {
							var a = e.split("_"),
								o = {
									ida: parseInt(a[1]),
									name: r[e].value,
									pseudo: r[e].pseudo
								};
							l.default.unitListSearchResult.push(o)
						}
					}), l.default.unitListSearchResult.length || l.default.searchResult.push({
						el: "units",
						count: 0 < l.default.unitListSearchResult.length
					})
				}, e.prototype.clearSearchResult = function () {
					l.default.unitListSearchResult = []
				}, a([n.action], e.prototype, "load", null), e
			}();
		t.default = new r
	},
	211: function (e, t, n) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var i = n(18),
			l = n(10),
			r = n(39),
			o = n(17),
			s = n(19),
			u = n(17),
			a = function () {
				function e() {
					this.searchindexes = [], this.isLoading = !1
				}
				return e.prototype.loadIndex = function (e) {
					var t = this,
						a = r.default.create({
							baseURL: _WWW,
							responseType: "json"
						});
					if (this.hasIndex(e)) return l.default.emitter.emit(i.SEARCHINDEX_AVAILABLE), !0;
					if (this.isLoading) return !1;
					this.isLoading = !0;
					var o = {
						id: e.toString(),
						type: i.LOADING_TYPE_SEARCHINDEX,
						message: u.default.localization.messages.LOADING_SEARCHINDEX
					};
					return l.default.setIsLoading(o), a.get(l.default.paths._JSON + s.default.projectIda + "/searchindex/" + e + ".json?" + n.h, {
						onDownloadProgress: function (e) {
							o.percent = Math.round(100 * e.loaded / e.total), l.default.setLoadingPercent(o)
						}
					}).then(function (n) {
						var r = {};
						Object.keys(n.data).map(function (e, t) {
							var a = n.data[e].split("\n"),
								o = e.split("_");
							r[o[0]] = a[0]
						}), t.searchindexes.push({
							codeShort: e,
							data: n.data,
							maptitles: r
						}), l.default.setNotLoading(o), t.isLoading = !1, l.default.emitter.emit(i.SEARCHINDEX_AVAILABLE)
					}).catch(function (e) {
						l.default.setNotLoading(o), t.isLoading = !1
					}), !0
				}, e.prototype.hasIndex = function (t) {
					return 0 < this.searchindexes.filter(function (e) {
						return e.codeShort === t
					}).length
				}, e.prototype.getIndex = function (t) {
					if (this.hasIndex(t)) {
						var e = this.searchindexes.filter(function (e) {
							return e.codeShort === t
						});
						return l.default.emitter.emit(i.SEARCHINDEX_AVAILABLE), e[0]
					}
					this.loadIndex(t)
				}, e.prototype.searchMap = function (e) {
					var t = o.default.localization,
						a = this.getIndex(t.codeShort);
					return void 0 !== a.maptitles ? a.maptitles[e] : e.toString()
				}, e.prototype.compareWithInput = function (r) {
					var e = o.default.localization,
						i = this.getIndex(e.codeShort),
						l = [],
						s = [];
					return Object.keys(i.data).filter(function (e, t) {
						if (-1 < i.data[e].toLowerCase().indexOf(r.toLowerCase())) {
							var a = e.split("_");
							if (a.length) {
								l.push(parseInt(a[0]));
								var o = i.data[e].split("\n");
								if (2 < o.length) {
									var n = o[1].split(" / ");
									s.push({
										ida: parseInt(a[0]),
										name: o[0],
										geounit: 1 < n.length ? n[1] : "",
										visualizations: o.slice(2).join("<br />")
									})
								}
							}
						}
					}), s
				}, e
			}();
		t.default = new a
	},
	212: function (e, t, a) {
		"use strict";
		var o = this && this.__assign || function () {
				return (o = Object.assign || function (e) {
					for (var t, a = 1, o = arguments.length; a < o; a++)
						for (var n in t = arguments[a]) Object.prototype.hasOwnProperty.call(t, n) && (e[n] = t[n]);
					return e
				}).apply(this, arguments)
			},
			n = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var r = a(10),
			i = a(39),
			l = a(17),
			s = a(25),
			u = a(18),
			c = function () {
				function e() {
					this.projects = [], this.pending = !0, this.areProjectsLoaded = !1, this.project = {}, this.client = i.default.create({
						baseURL: _WWW,
						responseType: "json"
					})
				}
				return e.prototype.loadProjects = function () {
					var t = this;
					if (this.hasProjects()) return r.default.emitter.emit(u.PROJECTS_LOADED), this.projects;
					this.pending = !0, this.client.get(r.default.paths._JSON + "projects.json?" + a.h).then(function (e) {
						t.setProjects(e.data), t.pending = !1, t.areProjectsLoaded = !0, r.default.emitter.emit(u.PROJECTS_LOADED)
					}).catch(function (e) {})
				}, e.prototype.hasProjects = function () {
					return this.areProjectsLoaded
				}, e.prototype.getProjects = function () {
					return this.projects
				}, e.prototype.setProjects = function (t) {
					var a = this;
					Object.keys(t).map(function (e) {
						a.projects.push(o({
							key: e
						}, t[e]))
					})
				}, e.prototype.provideSearchResult = function (a) {
					this.clearSearchResult();
					var e = this.getProjects(),
						o = l.default.localization;
					e.map(function (e, t) {
						-1 < e.languages[o.ida].name.toLowerCase().indexOf(a.toLowerCase()) && r.default.projectListSearchResult.push({
							ida: e.key,
							name: e.languages[o.ida].name
						})
					})
				}, e.prototype.clearSearchResult = function () {
					r.default.projectListSearchResult = []
				}, n([s.observable], e.prototype, "pending", void 0), n([s.observable], e.prototype, "areProjectsLoaded", void 0), e
			}();
		t.ProjectsStore = c, t.default = new c
	},
	213: function (e, t, o) {
		"use strict";
		(function (f) {
			Object.defineProperty(t, "__esModule", {
				value: !0
			});
			var h = o(10),
				m = o(48),
				g = o(19),
				d = o(20),
				n = o(150),
				p = o(214),
				r = o(30),
				i = o(18),
				a = o(122);
			f.fn.exists = function () {
				return 0 < this.length
			};

			var e = function () {
				function e() {
					var o = this;

					h.default.emitter.on(i.UNIT_WAS_CLICKED_IN_UNITLIST, function (e) {
						var t = e.unit_ida,
							a = f("path[data-region=" + t + "]");
							//console.log(a + "musa");
						o.createRegionRollover(f(a).attr("id"), !0), h.default.currentMarkedRegion = f(a).attr("id"), n.default.zoomToRegion(f(a).attr("id"))
					})
					//$("#relief").remove();
				}
				return e.prototype.makeInitialChangesToLegend = function () {
					r.bajoodooLog("ThematicalMapCommon.makeInitialChangesToLegend()"), !1 === f("#arealLegend").exists() ? (f("<div/>", {
						id: "arealLegend"
					}).appendTo(f("#tML")), f("<div/>", {
						id: "choroLegend"
					}).appendTo(f("#arealLegend")), f("<div/>", {
						id: "qualiLegend"
					}).appendTo(f("#arealLegend"))) : (f("#arealLegend").fadeTo(100, 1), f("#choroLegend").html(null), f("#qualiLegend").html(null)), !1 === f("#symbolLegend").exists() ? (f("<div/>", {
						id: "symbolLegend"
					}).appendTo(f("#tML")), f("<div/>", {
						id: "symLegend"
					}).appendTo(f("#symbolLegend")), f("<div/>", {
						id: "sectLegend"
					}).appendTo(f("#symbolLegend")), f("<div/>", {
						id: "barLegend"
					}).appendTo(f("#symbolLegend"))) : (f("#symbolLegend").fadeTo(100, 1), f("#symLegend").html(null), f("#sectLegend").html(null), f("#barLegend").html(null)), !1 === f("#dataSourceLegend").exists() && f("<div/>", {
						id: "dataSourceLegend"
					}).appendTo(f("#tML"))

				}, e.prototype.handleMouseOverRegion = function (e) {
					a.show(e);
					var t = f(e.currentTarget).attr("id");
					this.createRegionRollover(t, !1)
				}, e.prototype.handleMouseLeaveRegion = function (t) {
					void 0 === t && (t = !1), f("#symbolContainer").attr("opacity", "1"), f("#rollOverContainer").children().each(function (e) {
						(-1 === f(this).attr("id").indexOf("ro_" + h.default.currentMarkedRegion + "_") || t) && f(this).fadeOut(0, function () {
							f(this).remove()
						})
					}), f("#rollOverSymbolsContainer").children().each(function (e) {
						(-1 === f(this).attr("id").indexOf("roS_" + h.default.currentMarkedRegion) || t) && f(this).fadeOut(0, function () {
							f(this).remove()
						})
					}), f("#rollOverOutlineContainer").children().each(function (e) {
						(-1 === f(this).attr("id").indexOf("roO_" + h.default.currentMarkedRegion) || t) && f(this).fadeOut(0, function () {
							f(this).remove()
						})
					}), h.default.currentRollOver = ""
				}, e.prototype.createRegionRollover = function (e, t, a) {
					var o = this;
					void 0 === t && (t = !1), void 0 === a && (a = !0);
					var n = e.split("_");
					n.shift(), n.shift();
					var r = n.join("_");
					a && (f("#rollOverContainer").children().each(function (e) {
						-1 === f(this).attr("id").indexOf("ro_" + h.default.currentMarkedRegion) && f(this).fadeOut(0, function () {
							f(this).remove()
						})
					}), f("#rollOverSymbolsContainer").children().each(function (e) {
						-1 === f(this).attr("id").indexOf("roS_" + h.default.currentMarkedRegion) && f(this).fadeOut(0, function () {
							f(this).remove()
						})
					}), f("#rollOverOutlineContainer").children().each(function (e) {
						-1 === f(this).attr("id").indexOf("roO_" + h.default.currentMarkedRegion) && f(this).fadeOut(0, function () {
							f(this).remove()
						})
					}), h.default.currentRollOver = e, m.default.currentRegionInfo = m.default.getDataInfo().getRegionInfo(r));
					for (var i = 0, l = 0, s = g.default.preset.rollOverLineColors; l < s.length; l++) {
						var u = s[l],
							c = 0 == i;
						g.default.preset.rollOverLineColors.length;
						if (f("#" + e).clone().attr("id", "ro_" + e + "_" + i).attr("style", "pointer-events: none;").attr("fill", c ? "#CCCCCC" : "#FF0000").attr("fill-opacity", c ? "0.8" : "0").attr("stroke", "#" + u).attr("stroke-width", g.default.preset.rollOverLineWidth * (g.default.preset.rollOverLineColors.length - i) - 1).attr("stroke-opacity", g.default.preset.rollOverMaxAlpha * (i + 1) / g.default.preset.rollOverLineColors.length / 100).appendTo(f("#rollOverContainer")), t)
							for (var d = 0, p = g.default.preset.rollOverLineColors; d < p.length; d++) {
								p[d];
								f("#ro_" + e + "_0").addClass("pulse")
							}
						h.default.isSymbolOn && !t && f("#_" + r + "_group").length && (a && f("#roS" + i).remove(), f("#_" + r + "_group").clone().attr("id", "roS" + r).attr("style", "pointer-events: none;").appendTo(f("#rollOverSymbolsContainer")), f("#" + e).clone().attr("id", "roO" + r).attr("fill-opacity", 0).attr("style", "pointer-events: none;").appendTo(f("#rollOverOutlineContainer"))), i++
					}
					t && (clearTimeout(this.currentTimeout), this.currentTimeout = setTimeout(function () {
						h.default.currentMarkedRegion = null, o.handleMouseLeaveRegion(!0)
					}, 5e3))
				}, e.prototype.makeInitialChangesToGeometry = function () {
					r.bajoodooLog("ThematicalMapCommon.makeInitialChangesToGeometry()");
					var a = this,
						e = d.default.current.geometry,
						t = f("#geometry_" + d.default.current.geometry);
					!1 === t.hasClass("mapSVG") && t.addClass("mapSVG"), t.find("#bg").attr("fill-opacity", 0).attr("stroke-width", 1).attr("stroke-opacity", 0), t.find("#relief").attr("visibility", 1 == d.default.current.properties.show_relief ? "visible" : "hidden"), t.find("#relief").on("mousedown", function () {
						return !1
					});
					var o = 1,
						n = 999999; - 1 < [1229, 1231, 1232, 1339, 1341, 1258, 1259, 1405, 1406, 1410, 1411].indexOf(e) && (o = 2, n = 666666), this.regionLogCounter = 0, h.default.validIds = [], f.each(t.find("[id^=geo_" + e + "_]"), function () {
						f(this).attr("ref", f(this).attr("id")).attr("data-tip", "tooltip").attr("fill", "#cccccc").attr("fill-opacity", 0).attr("stroke", "#" + n).attr("stroke-width", o).unbind("mouseenter").unbind("mouseleave").on("mouseenter", function (e) {
							return a.handleMouseOverRegion(e)
						}).on("mouseleave", function (e) {
							return a.handleMouseLeaveRegion()
						});
						var e = this.id.split("_");
						e.shift(), e.shift();
						var t = e.join("_");
						h.default.validIds.push(t), f(this).attr("data-region", t)
					}), h.default.emitter.emit(i.REGION_ROLLOVER), this.prepareTopographies(e), !1 === f("#symbolContainer").exists() ? (f(document.createElementNS("http://www.w3.org/2000/svg", "g")).attr({
						id: "symbolContainer"
					}).appendTo(f("#geometry_" + e)), f("#symbolContainer").attr("transform", f("#geo_" + e).attr("transform"))) : f("#symbolContainer").children().each(function () {
						f(this).remove()
					}), !1 === f("#rollOverContainer").exists() ? (f(document.createElementNS("http://www.w3.org/2000/svg", "g")).attr({
						id: "rollOverContainer"
					}).appendTo(f("#geometry_" + e)), f("#rollOverContainer").attr("transform", f("#geo_" + e).attr("transform"))) : f("#rollOverContainer").children().each(function () {
						f(this).remove()
					}), !1 === f("#rollOverSymbolsContainer").exists() ? (f(document.createElementNS("http://www.w3.org/2000/svg", "g")).attr({
						id: "rollOverSymbolsContainer"
					}).appendTo(f("#geometry_" + e)), f("#rollOverSymbolsContainer").attr("transform", f("#geo_" + e).attr("transform"))) : f("#rollOverSymbolsContainer").children().each(function () {
						f(this).remove()
					}), !1 === f("#rollOverOutlineContainer").exists() ? (f(document.createElementNS("http://www.w3.org/2000/svg", "g")).attr({
						id: "rollOverOutlineContainer"
					}).appendTo(f("#geometry_" + e)), f("#rollOverOutlineContainer").attr("transform", f("#geo_" + e).attr("transform"))) : f("#rollOverOutlineContainer").children().each(function () {
						f(this).remove()
					})
				}, e.prototype.prepareTopographies = function (a) {
					var o = this,
						n = g.default.getProject().topographies,
						r = d.default.current.topographies,
						i = g.default.getProject().geometries,
						l = new Array,
						s = new Array,
						u = new Array,
						c = new Array;
					f.each(n, function (e, t) {
						n[e].properties.geosystem == i[a].geosystem && 1 != n[e].properties.is_folder && (l.push(e), c.push(1 == n[e].properties.is_visible), s.push(n[e].properties.type), u.push(n[e].properties.class))
					}), !1 === f("#topoContainer").exists() && (f(document.createElementNS("http://www.w3.org/2000/svg", "g")).attr({
						id: "topoContainer"
					}).appendTo(f("#geometry_" + a)), f.each(l.reverse(), function (e, t) {
						f("#topo_" + s[e] + "_" + t).exists() || f(document.createElementNS("http://www.w3.org/2000/svg", "g")).attr({
							id: "topo_" + s[e] + t + "_placeholder"
						}).appendTo(f("#topoContainer")), h.default.setTopoState(s[e], parseInt(t.substr(1)), !1), 1 == r[t] && 1 == p.default.has(parseInt(t.substr(1)), s[e]) && o.addTopography(s[e], parseInt(t.substr(1)))
					}))
				}, e.prototype.addTopography = function (e, t) {
					r.bajoodooLog("ThematicalMapsCentral.addTopography(" + e + "_" + t + ")"), f("#invisibleWorker").html(p.default.get(t, e).svg);
					var a = f("#invisibleWorker>svg>g");
					a.attr("visibility", "visible"), h.default.setTopoState(e, t, !0), a.attr("style", "pointer-events: none;"), f("#topo_" + e + "_" + t + "_placeholder").replaceWith(a), f("#invisibleWorker").html(null)
				}, e
			}();
			t.default = new e
		}).call(this, o(31))
	},
	214: function (e, n, r) {
		"use strict";
		(function (l) {
			var t = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
			Object.defineProperty(n, "__esModule", {
				value: !0
			});
			var s = r(10),
				a = r(39),
				u = r(17),
				c = r(19),
				d = r(20),
				o = r(25),
				p = r(761),
				f = r(18),
				h = r(30),
				e = function () {
					function e() {
						this.topographies = [], this.client = a.default.create({
							baseURL: _WWW,
							responseType: "document"
						})
					}
					return e.prototype.has = function (e, t) {
						return h.bajoodooLog("TopographyStore.has(" + e + ", '" + t + "')"), this.topographies[t + e] instanceof p.Topography ? 1 != this.topographies[t + e].isLoading : (this.load(e, t), !1)
					}, e.prototype.get = function (e, t) {
						if (h.bajoodooLog("TopographyStore.get(" + e + ", '" + t + "')"), this.topographies[t + e] instanceof p.Topography && 1 != this.topographies[t + e].isLoading) return s.default.emitter.emit(f.TOPOGRAPHY_LOADED, {
							ida: e,
							type: t
						}), this.topographies[t + e];
						throw h.bajoodooLog("TopographyStore.get(" + e + ", '" + t + "') throws error"), new Error("topography not available")
					}, e.prototype.load = function (o, n) {
						var r = this;
						if (this.topographies[n + o] instanceof p.Topography && 1 == this.topographies[n + o].isLoading) h.bajoodooLog("TopographyStore.load(" + o + ", '" + n + ") - already loading!!!");
						else {
							this.topographies[n + o] instanceof p.Topography && s.default.emitter.emit(f.TOPOGRAPHY_LOADED, {
								ida: o,
								type: n
							}), h.bajoodooLog("TopographyStore.load(" + o + ", '" + n + "')");
							var e = u.default.localization,
								t = {
									ida: o,
									topoType: n,
									svg: "",
									isLoading: !0
								};
							this.topographies[n + o] = new p.Topography(t);
							var i = {
								id: n.toString() + o.toString(),
								type: f.LOADING_TYPE_TOPOGRAPHY,
								message: e.messages.LOADING_TOPOGRAPHY + [" (", o, ")"].join("")
							};
							s.default.setIsLoading(i), this.client.get(s.default.paths._JSON + c.default.projectIda + "/topographies/" + n + "/" + o + ".svg", {
								onDownloadProgress: function (e) {
									i.percent = Math.round(100 * e.loaded / e.total), s.default.setLoadingPercent(i)
								}
							}).then(function (e) {
								e.data.getElementsByTagName("image")[0].setAttribute("xlink:href","https://www.atlas.bfs.admin.ch/img/xshared/relief/1/0.jpg");
								var t = l(l(e.data)[0].childNodes)[1].outerHTML || (new XMLSerializer).serializeToString(l(l(e.data)[0].childNodes)[1]),
									a = {
										ida: o,
										topoType: n,
										svg: t,
										isLoading: !1
									};
								r.topographies[n + o] = new p.Topography(a), d.default.topographyLoaded(), s.default.setNotLoading(i), s.default.emitter.emit(f.TOPOGRAPHY_LOADED, {
									ida: o,
									type: n
								})
							}).catch(function (e) {
								throw h.bajoodooLog(e), new Error("topography loader error")
							})
						}
					}, t([o.observable], e.prototype, "topographies", void 0), t([o.action], e.prototype, "load", null), e
				}();
			n.default = new e
		}).call(this, r(31))
	},
	30: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
			return (o = Object.setPrototypeOf || {
					__proto__: []
				}
				instanceof Array && function (e, t) {
					e.__proto__ = t
				} || function (e, t) {
					for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
				})(e, t)
		}, function (e, t) {
			function a() {
				this.constructor = e
			}
			o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
		});
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var r = a(79),
			i = a(31),
			l = a(18),
			s = a(1),
			u = a(32),
			c = a(446),
			d = a(10),
			p = a(184),
			f = a(19),
			h = a(28);
		a(85), a(943), a(1108);
		var m = h.createMuiTheme({
			palette: {
				primary: {
					main: "#f5f5f5"
				},
				secondary: {
					main: "#d00b1c"
				}
			},
			typography: {
				useNextVariants: !0,
				h6: {
					fontSize: "1.1em"
				},
				subtitle1: {
					fontSize: "1em"
				},
				body2: {
					fontSize: "0.95em"
				},
				body1: {
					fontSize: "0.9em"
				}
			}
		});

		function g() {
			for (var e = [], t = 0; t < arguments.length; t++) e[t] = arguments[t]
		}
		t.bajoodooLog = g;
		var y = VARS.mapId.split("_"),
			v = {
				isOffline: "http" !== location.protocol.substr(0, 4).toLowerCase(),
				isPreview: 1 == VARS.preview,
				previewPath: 1 == VARS.preview ? "preview/" : "",
				projectIda: VARS.projectId,
				lngShort: VARS.lng,
				appVersion: parseInt(VARS.appversion),
				openingTimestamp: i.now(),
				mapIda: y.length ? y[0] : 0,
				pattern: y.length ? y : [0],
				liveMapRefreshTime: 30,
				isEmbedded: void 0 !== VARS.embedded ? VARS.embedded : -1 < location.search.indexOf("?e"),
				isMobile: !1,
				isExtraSmall: !1,
				isSmall: !1,
				isMedium: !1,
				isLarge: !1,
				isExtraLarge: !1
			},
			b = {
				_WWW: _WWW,
				_IMG: null,
				_DS: "/",
				_PREVIEW: "preview",
				_MEDIAFOLDER: "media",
				_CORE: "core",
				_PROJECTS: "projects",
				_SWF: null,
				_XML: null,
				_JSON: null,
				_MEDIA: null,
				_CSS: null,
				_JS: null,
				_ICON: null
			};
		b._IMG = b._WWW + "img" + b._DS, b._SWF = b._WWW + "swf" + b._DS, b._XML = b._WWW + (v.isPreview ? b._PREVIEW + b._DS : "") + "xml" + b._DS, b._JSON = b._WWW + (v.isPreview ? b._PREVIEW + b._DS : "") + "json" + b._DS, b._MEDIA = b._WWW + (v.isPreview ? b._PREVIEW + b._DS + b._MEDIAFOLDER + b._DS : b._CORE + b._DS + b._PROJECTS + b._DS);

		var _ = function (a) {
				function e(e) {
					var t = a.call(this, e) || this;
					return d.default.vars = v, d.default.paths = b, d.default.emitter.setMaxListeners(500), t
				}
				return n(e, a), e.prototype.componentDidMount = function () {
					i(window).on("resize", r.debounce(this.windowResized, 200)), f.default.loadProject()
				}, e.prototype.render = function () {
					return this.windowResized(), s.createElement(h.MuiThemeProvider, {
						theme: m
					}, s.createElement(p.default, null), s.createElement(c.default, null))
				}, e.prototype.windowResized = function () {
					g("windowResized"), d.default.emitter.emit(l.WINDOW_RESIZED)
				}, e
			}(s.Component),
			T = document.getElementById("root");
		u.render(s.createElement(_, null), T)
	},
	375: function (e, t, a) {
		"use strict";
		var o = this && this.__decorate || function (e, t, a, o) {
			var n, r = arguments.length,
				i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
			if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
			else
				for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
			return 3 < r && i && Object.defineProperty(t, a, i), i
		};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var n = a(25),
			r = a(30),
			i = a(751),
			l = function () {
				function e() {
					this.geosystems = []
				}
				return e.prototype.get = function (e) {
					if (r.bajoodooLog("GeosystemStore.get(" + e + ")"), this.geosystems["_" + e] instanceof i.Geosystem) return this.geosystems["_" + e];
					throw r.bajoodooLog("GeosystemStore.get(" + e + ") throws error"), new Error("geosystem not available")
				}, e.prototype.add = function (e) {
					if (this.geosystems["_" + e.IDA] instanceof i.Geosystem) r.bajoodooLog("GeosystemStore.add(" + e.IDA + ") - already added!!!");
					else {
						r.bajoodooLog("GeosystemStore.add(" + e.IDA + ")");
						var t = {
							ida: parseInt(e.IDA),
							characterSym: e.CHARACTER_SYM.substr(0, 1).toUpperCase(),
							isPseudo: 1 == e.IS_PSEUDO,
							centerXdegree: Number(e.CENTER_X_DEGREE),
							centerYdegree: Number(e.CENTER_Y_DEGREE),
							offsetX: Number(e.OFFSET_X),
							offsetY: Number(e.OFFSET_Y),
							ratioM2PX: Number(e.RATIO_M_TO_PX),
							xMin: Number(e.X_MIN),
							xMax: Number(e.X_MAX),
							yMin: Number(e.Y_MAX),
							yMax: Number(e.Y_MIN)
						};
						this.geosystems["_" + e.IDA] = new i.Geosystem(t)
					}
				}, o([n.observable], e.prototype, "geosystems", void 0), o([n.action], e.prototype, "add", null), e
			}();
		t.default = new l
	},
	376: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = function () {
			this.showId = !1, this.showPercentage = !1, this.datasets = []
		};
		t.InfoObject = o
	},
	377: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = a(18),
			n = a(17),
			i = a(79),
			r = function () {
				function r(e) {
					for (var t in this.languages = [], this.spatialValuesSortable = [], this.pseudoValuesSortable = [], this.unspecifiedIds = [], e) this[t] = e[t]
				}
				return r.prototype.getInfoByLabel = function (e) {
					return !this.info.hasOwnProperty(e) || i.isUndefined(e) ? "" : this.info[e]
				}, r.prototype.getCurrentFormulaElements = function () {
					var e = n.default.localization.ida;
					return this.languages[e].formula
				}, r.prototype.getCurrentVG = function () {
					var e = n.default.localization.ida;
					return this.languages[e].valid_geometry
				}, r.prototype.getCurrent = function (e) {
					var t = n.default.localization.ida;
					return null === this.languages[t] || void 0 === this.languages[t] ? "" : this.languages[t][e]
				}, r.prototype.getCurrentName = function () {
					var e = n.default.localization.ida;
					return this.languages[e].name
				}, r.prototype.isClassificationAnObject = function () {
					var e = Object.keys(this.getCurrent(o.DATASET_CLASSIFICATION)).filter(function (e) {
						return !isNaN(Number(i.replace(e, ".", "")))
					});
					return i.isPlainObject(this.getCurrent(o.DATASET_CLASSIFICATION)) && 0 < e.length
				}, r.prototype.resetValues = function () {
					return this.spatialValues = {}, this.pseudoValues = {}, this.spatialValuesSortable = [], this.pseudoValuesSortable = [], this.unspecifiedIds = [], this
				}, r.prototype.parseValues = function () {
					var o, n = this;
					this.hasContent() && (this.resetValues(), Object.keys(this.values).map(function (e, t) {
						var a = n.values[e];
						a.value == r.DATA_MISSING_IDENTIFIER ? (o = null, 0 == a.pseudoId ? n.containsSpatialUnspecifiedValues = !0 : n.containsPseudoUnspecifiedValues = !0, n.unspecifiedIds.push(e)) : o = a.value == r.DATA_PROTECTION_IDENTIFIER ? (0 == a.pseudoId ? n.containsSpatialPrivacy = !0 : n.containsPseudoPrivacy = !0, !1) : a.value, 0 == a.pseudoId ? (n.spatialValues[e] = o, n.spatialValuesSortable.push({
							unit: e,
							value: o,
							pseudo: !1
						})) : (n.pseudoValues[e] = o, n.pseudoValuesSortable.push({
							unit: e,
							value: o,
							pseudo: !0
						}), n.containsPseudoValues = !0)
					}), this.spatialValuesSortable.sort(this.sortNumeric), this.pseudoValuesSortable.sort(this.sortNumeric), this.spatialValuesSortable.reverse(), this.pseudoValuesSortable.reverse())
				}, r.prototype.sortNumeric = function (e, t) {
					return e.value > t.value ? 1 : e.value < t.value ? -1 : 0
				}, r.prototype.getValuesObject = function (e, t, a) {
					void 0 === e && (e = !1), void 0 === t && (t = !1), void 0 === a && (a = !1);
					var o = new Object;
					if (!this.hasContent()) return o;
					if (this.spatialValuesSortable.length || this.parseValues(), e && t && (o = this.pseudoValues), t || e || (o = this.spatialValues), !t && e) {
						for (var n in this.spatialValues) o[n] = this.spatialValues[n];
						for (var r in this.pseudoValues) o[r] = this.pseudoValues[r]
					}
					return a && this.unspecifiedIds.map(function (e) {
						null != o[e] && delete o[e]
					}), o
				}, r.prototype.hasContent = function () {
					return void 0 !== this.values && 0 < Object.keys(this.values).length
				}, r.DATA_PROTECTION_IDENTIFIER = !1, r.DATA_MISSING_IDENTIFIER = null, r
			}();
		t.Dataset = r
	},
	378: function (e, t, n) {
		"use strict";
		var r = this && this.__assign || function () {
				return (r = Object.assign || function (e) {
					for (var t, a = 1, o = arguments.length; a < o; a++)
						for (var n in t = arguments[a]) Object.prototype.hasOwnProperty.call(t, n) && (e[n] = t[n]);
					return e
				}).apply(this, arguments)
			},
			a = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var i = n(18),
			o = n(39),
			l = n(17),
			s = n(19),
			u = n(20),
			c = n(25),
			d = n(30),
			p = n(762),
			f = n(10),
			h = function () {
				function e() {
					this.thematicalMaps = [], this.client = o.default.create({
						baseURL: _WWW,
						responseType: "json"
					})
				}
				return e.prototype.has = function (e, t) {
					return void 0 === t && (t = !0), d.bajoodooLog("ThematicalMapStore.has(" + e + ")"), this.thematicalMaps["_" + e] instanceof p.ThematicalMap ? 1 != this.thematicalMaps["_" + e].isLoading : (t && this.load(e), !1)
				}, e.prototype.removeMap = function (e) {
					this.thematicalMaps["_" + e] instanceof p.ThematicalMap && delete this.thematicalMaps["_" + e]
				}, e.prototype.get = function (e) {
					if (d.bajoodooLog("ThematicalMapStore.get(" + e + ")"), this.thematicalMaps["_" + e] instanceof p.ThematicalMap && 1 != this.thematicalMaps["_" + e].isLoading) return this.thematicalMaps["_" + e];
					throw d.bajoodooLog("ThematicalMapStore.get(" + e + ") throws error"), new Error("thematicalMap not available")
				}, e.prototype.load = function (t) {
					var a = this;
					if (this.thematicalMaps["_" + t] instanceof p.ThematicalMap && 1 == this.thematicalMaps["_" + t].isLoading) d.bajoodooLog("ThematicalMapStore.load(" + t + ") - already loading!!!");
					else {
						d.bajoodooLog("ThematicalMapStore.load(" + t + ")");
						var e = l.default.localization,
							o = {
								id: t.toString(),
								type: i.LOADING_TYPE_MAP,
								message: e.messages.LOADING_MAP + [" (", t, ")"].join("")
							};
						f.default.setIsLoading(o), this.client.get(f.default.paths._JSON + s.default.projectIda + "/maps/" + t + ".json?" + n.h, {
							onDownloadProgress: function (e) {
								o.percent = Math.round(100 * e.loaded / e.total), f.default.setLoadingPercent(o)
							}
						}).then(function (e) {
							//console.log(f.default.paths._JSON + s.default.projectIda + "/maps/" + t + ".json?" + n.h);
							a.thematicalMaps["_" + t] = new p.ThematicalMap(r({
								ida: t
							}, e.data[t])), a.thematicalMaps["_" + t].isLoading = !1, a.thematicalMaps["_" + t].allNecessaryPartsLoaded = !1, u.default.thematicalMapLoaded(t), f.default.setNotLoading(o)
						}).catch(function (e) {
							throw d.bajoodooLog(e), f.default.setNotLoading(o), new Error("thematicalMap loader error")
						})
					}
				}, a([c.observable], e.prototype, "thematicalMaps", void 0), a([c.action], e.prototype, "load", null), e
			}();
		t.default = new h
	},
	379: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = a(19),
			n = a(20),
			r = a(17),
			i = a(58),
			l = function () {
				function e() {
					this.p = o.default.getProject(), this.m = n.default.current, this.l = r.default.localization
				}
				return e.prototype.renderShortUrl = function (e) {
					void 0 === e && (e = !1);
					var t = this.prepareShareUrl(e);
					return decodeURIComponent(t)
				}, e.prototype.prepareShareUrl = function (e) {
					void 0 === e && (e = !1);
					var t = e ? o.default._DS : this.l.messages.URL + o.default._DS,
						a = this.getShortElements();
					return encodeURIComponent(t + a)
				}, e.prototype.prepareShareTitle = function () {
					return encodeURIComponent(this.p.isMapsProject() ? this.p.currentLanguage().name + " - " + this.m.getCurrentMapTitle() : this.getIndicatorPageTitle())
				}, e.prototype.getShortElements = function () {
					var e = "";
					if (e = "maps" + o.default._DS + this.p.project + o.default._DS + this.l.codeShort + o.default._DS, this.p.isMapsProject()) {
						var t = i.default.getCurrentNode();
						e += t.ida + "_" + t.parents.reverse().slice(0, -1).join("_") + o.default._DS, e += this.m.ida + ".html"
					} else this.p.isIndicatorProject();
					return e
				}, e.prototype.getIFrameCode = function (e) {
					var t;
					return t = "<iframe \n", t += '\tsrc="' + r.default.localization.messages.URL + o.default._DS + e + '"\n', t += '\tframeborder="0"\n', t += '\tmarginheight="0"\n', t += '\tmarginwidth="0"\n', t += '\tscrolling="auto"\n', t += '\tstyle="\n', t += "\t\twidth: 100%;\n", t += "\t\theight: 600px;\n", t += "\t\tbackground-color: #A5A5A5;\n", t += "\t\tmargin: 0;\n", t += "\t\tpadding: 0;\n", t += "\t\tcursor: auto;\n", t += '\t"\n>\n', t += "</iframe>"
				}, e.prototype.getEmbedCode = function (e, t) {
					return '<a href="' + this.l.messages.URL + o.default._DS + e + '" target="_blank">' + encodeURIComponent(t) + "</a>"
				}, e.prototype.getIndicatorPageTitle = function () {
					return ""
				}, e.prototype.openWindow = function (e, t, a, o, n) {
					void 0 === t && (t = "sharePopup"), void 0 === a && (a = 640), void 0 === o && (o = 480), void 0 === n && (n = "location=no,menubar=no,personalbar=no,resizeable=no,scrollbars=no,status=no,toolbar=no"), null == a && (a = window.screen.availWidth - 12), null == o && (o = window.screen.availHeight - 64);
					var r = (window.screen.availWidth - a) / 2,
						i = (window.screen.availHeight - o) / 2;
					n = n.length ? n + "," : n, window.open(e, t, n + "width=" + a + ",height=" + o + ",left=" + r + ",top=" + i + ",screenX=" + r + ",screenY=" + i + ",top=" + i).focus()
				}, e
			}();
		t.LinksAndTitles = l
	},
	380: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
			return (o = Object.setPrototypeOf || {
					__proto__: []
				}
				instanceof Array && function (e, t) {
					e.__proto__ = t
				} || function (e, t) {
					for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
				})(e, t)
		}, function (e, t) {
			function a() {
				this.constructor = e
			}
			o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
		});
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var r = a(1),
			i = a(28),
			l = a(151),
			s = a(17),
			u = a(19),
			c = a(20),
			d = a(29),
			p = a(83),
			f = a(10),
			h = i.withStyles(function (e) {
				var t = e.typography,
					a = (e.mixins, e.spacing, e.palette);
				e.shadows;
				return {
					root: {
						width: "100%"
					},
					heading: {
						fontSize: t.pxToRem(15),
						flexBasis: "100%",
						flexShrink: 0
					},
					secondaryHeading: {
						fontSize: t.pxToRem(15),
						color: a.text.secondary
					}
				}
			})(d.observer(function (e) {
				function t() {
					var o = null !== e && e.apply(this, arguments) || this;
					return o.state = {
						expanded: ""
					}, o.handleChange = function (a) {
						return function (e, t) {
							o.setState({
								expanded: !!t && a
							}), setTimeout(function () {
								f.default.modalTime = Date.now()
							}, 1e3)
						}
					}, o
				}
				return n(t, e), t.prototype.componentWillMount = function () {
					this.p = u.default.getProject(), this.m = c.default.current, this.l = s.default.localization, this.modes = p.default.current.data.properties.modes.imprint.elements["@modes"].split(",")
				}, t.prototype.render = function () {
					var t = this,
						a = this.props.classes,
						o = this.state.expanded;
					return r.createElement("div", null, r.createElement(i.Typography, {
						variant: "h6",
						id: "share-title"
					}, this.l.messages.IMPRINT_COPYRIGHT), r.createElement("hr", null), this.modes.map(function (e) {
						return r.createElement(i.ExpansionPanel, {
							expanded: o === e,
							onChange: t.handleChange(e),
							key: e
						}, r.createElement(i.ExpansionPanelSummary, {
							expandIcon: r.createElement(l.default, null)
						}, r.createElement(i.Typography, {
							className: a.heading
						}, t.l.messages["IMPRINT_" + e.toUpperCase()])), r.createElement(i.ExpansionPanelDetails, null, r.createElement(i.Typography, {
							className: a.heading,
							key: "formatted" + e,
							dangerouslySetInnerHTML: {
								__html: t.p.currentLanguage()[e]
							}
						})))
					}))
				}, t
			}(r.Component)));
		t.default = h
	},
	381: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
				return (o = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			}),
			r = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var i = a(1),
			l = a(17),
			s = a(19),
			u = a(10),
			c = a(29),
			d = a(28),
			p = a(380),
			f = function (t) {
				function e(e) {
					return t.call(this, e) || this
				}
				return n(e, t), e.prototype.componentDidMount = function () {
					this.isMnt = !0
				}, e.prototype.componentWillUnmount = function () {
					this.isMnt = !1
				}, e.prototype.handleClick = function () {
					u.default.modalContent = i.createElement(p.default, null), u.default.modalWidth = 700, u.default.modalOpen = !0
				}, e.prototype.renderInBottomBar = function () {
					var e = this,
						t = (this.props.classes, l.default.localization, s.default.getProject());
					return i.createElement("div", null, i.createElement(d.Typography, {
						variant: "caption",
						component: "a",
						onClick: function () {
							return e.handleClick()
						},
						className: "href"
					}, void 0 !== t ? t.currentLanguage().copyright_short : null))
				}, e.prototype.renderInMenu = function () {
					var e = this,
						t = this.props.classes,
						a = (l.default.localization, s.default.getProject());
					return i.createElement("div", {
						className: t.copyrightBox
					}, i.createElement(d.Typography, {
						variant: "caption",
						component: "a",
						onClick: function () {
							return e.handleClick()
						},
						className: "href"
					}, void 0 !== a ? a.currentLanguage().copyright_short : null))
				}, e.prototype.render = function () {
					return "bottom" == this.props.location ? this.renderInBottomBar() : this.renderInMenu()
				}, e = r([c.observer], e)
			}(i.Component);
		t.default = d.withStyles(function (e) {
			return {
				copyrightBox: {
					marginTop: 0,
					padding: "10px 24px"
				}
			}
		})(f)
	},
	382: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
				return (o = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			}),
			r = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var i = a(1),
			l = a(20),
			s = a(17),
			u = a(19),
			c = a(10),
			d = a(29),
			p = a(18),
			f = a(28),
			h = a(48),
			m = function (a) {
				function e(e) {
					var t = a.call(this, e) || this;
					return t.state = {}, t.state = {
						sources: []
					}, c.default.emitter.on(p.THEMATICALMAP_LOADED, function () {
						t.renderSources()
					}), c.default.emitter.on(p.LANGUAGE_LOADED, function () {
						t.renderSources()
					}), t
				}
				return n(e, a), e.prototype.componentDidMount = function () {
					this.isMnt = !0, this.renderSources()
				}, e.prototype.componentWillUnmount = function () {
					this.isMnt = !1
				}, e.prototype.componentWillUpdate = function () {}, e.prototype.renderSources = function () {
					if (this.isMnt) {
						var e = u.default.getProject(),
							t = s.default.localization,
							a = [];
						if (e.project === e.INDICATORS_IDENTIFIER);
						else {
							var o = l.default.current;
							if (void 0 === o) return null;
							if (!o.isKeydataMap)
								if (void 0 !== o.currentLanguage().merged_datasource) a.push(o.currentLanguage().merged_datasource);
								else {
									var n = void 0,
										r = void 0;
									if (o.hasVisualization(p.VISUALIZATION_CHORO) && (n = parseInt(Object.keys(o.getVisualization(p.VISUALIZATION_CHORO).datasets)[0]), void 0 !== (r = h.default.get(n).languages[t.ida].source) && 0 < r.length && -1 === a.indexOf(r) && a.push(r)), o.hasVisualization(p.VISUALIZATION_QUALI) && (n = parseInt(Object.keys(o.getVisualization(p.VISUALIZATION_QUALI).datasets)[0]), void 0 !== (r = h.default.get(n).languages[t.ida].source) && 0 < r.length && -1 === a.indexOf(r) && a.push(r)), o.hasVisualization(p.VISUALIZATION_SYM) && (n = parseInt(Object.keys(o.getVisualization(p.VISUALIZATION_SYM).datasets)[0]), void 0 !== (r = h.default.get(n).languages[t.ida].source) && 0 < r.length && -1 === a.indexOf(r) && a.push(r)), o.hasVisualization(p.VISUALIZATION_SECT)) {
										for (var i = 0; i < Object.keys(o.getVisualization(p.VISUALIZATION_SECT).datasets).length; i++) n = parseInt(o.getVisualization(p.VISUALIZATION_SECT).datasets[i]), void 0 !== (r = h.default.get(n).languages[t.ida].source) && 0 < r.length && -1 === a.indexOf(r) && a.push(r);
										for (i = 0; i < Object.keys(o.getVisualization(p.VISUALIZATION_SECT).sectors).length; i++) n = parseInt(o.getVisualization(p.VISUALIZATION_SECT).sectors[i].dataset), void 0 !== (r = h.default.get(n).languages[t.ida].source) && 0 < r.length && -1 === a.indexOf(r) && a.push(r)
									}
									if (o.hasVisualization(p.VISUALIZATION_BAR))
										for (i = 0; i < Object.keys(o.getVisualization(p.VISUALIZATION_BAR).sectors).length; i++) n = parseInt(o.getVisualization(p.VISUALIZATION_BAR).bars[i].dataset), void 0 !== (r = h.default.get(n).languages[t.ida].source) && 0 < r.length && -1 === a.indexOf(r) && a.push(r)
								}
						}
						a.length || a.push(t.messages.INFO_NO_SOURCES), this.setState({
							sources: a
						})
					}
				}, e.prototype.renderInBottomBar = function () {
					this.props.classes;
					return i.createElement("div", null, i.createElement(f.Typography, {
						variant: "caption"
					}, s.default.localization.messages.SOURCE, ":", this.state.sources.map(function (e, t) {
						return i.createElement("span", {
							key: "source" + t
						}, e)
					})))
				}, e.prototype.renderInMenu = function () {
					var e = this.props.classes;
					return i.createElement("div", {
						className: e.sourcesBox
					}, i.createElement(f.Typography, {
						variant: "caption"
					}, s.default.localization.messages.SOURCE, ":"), this.state.sources.map(function (e, t) {
						return i.createElement(f.Typography, {
							variant: "caption",
							key: "source" + t
						}, e)
					}))
				}, e.prototype.render = function () {
					this.props.classes;
					return "bottom" == this.props.location ? this.renderInBottomBar() : this.renderInMenu()
				}, e = r([d.observer], e)
			}(i.Component);
		t.default = f.withStyles(function (e) {
			return {
				sourcesBox: {
					marginTop: 0,
					padding: "10px 24px"
				}
			}
		})(m)
	},
	40: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = a(447);
		t.bajoodoo = o.default;
		var n = a(498);
		t.SwissX = n.default;
		var r = a(499);
		t.lustat = r.default;
		var i = a(500);
		t.LayersOutline = i.default;
		var l = a(501);
		t.MapInfo = l.default;
		var s = a(502);
		t.MapOutlineInfo = s.default;
		var u = a(503);
		t.MapLegend = u.default;
		var c = a(504);
		t.Fullscreen = c.default;
		var d = a(505);
		t.FullscreenExit = d.default;
		var p = a(506);
		t.Plus = p.default;
		var f = a(507);
		t.Minus = f.default;
		var h = a(508);
		t.Restore = h.default;
		var m = a(509);
		t.Layers = m.default;
		var g = a(510);
		t.Menu = g.default;
		var y = a(511);
		t.ShapeOutline = y.default;
		var v = a(512);
		t.ChevronUp = v.default;
		var b = a(513);
		t.ChevronRight = b.default;
		var _ = a(514);
		t.ChevronDown = _.default;
		var T = a(515);
		t.ChevronLeft = T.default;
		var E = a(516);
		t.Map = E.default;
		var S = a(517);
		t.MapOutline = S.default;
		var L = a(518);
		t.MapMarker = L.default;
		var O = a(519);
		t.Close = O.default;
		var I = a(520);
		t.Magnify = I.default;
		var A = a(521);
		t.FileTree = A.default;
		var w = a(522);
		t.File = w.default;
		var C = a(523);
		t.FileExcel = C.default;
		var P = a(524);
		t.FileWord = P.default;
		var N = a(525);
		t.FilePdf = N.default;
		var M = a(526);
		t.FileZip = M.default;
		var D = a(527);
		t.FileOutline = D.default;
		var R = a(528);
		t.ShareVariant = R.default;
		var k = a(529);
		t.Table = k.default;
		var j = a(530);
		t.InformationOutline = j.default;
		var x = a(531);
		t.ArrowExpandAll = x.default;
		var V = a(532);
		t.ArrowCollapseAll = V.default;
		var B = a(533);
		t.RadioboxMarked = B.default;
		var z = a(534);
		t.LinkVariant = z.default;
		var F = a(535);
		t.RadioTower = F.default;
		var U = a(536);
		t.ClipboardOutline = U.default;
		var W = a(537);
		t.ContentCopy = W.default;
		var H = a(538);
		t.FacebookBox = H.default;
		var G = a(539);
		t.GooglePlusBox = G.default;
		var Y = a(540);
		t.TwitterBox = Y.default;
		var Z = a(541);
		t.Video = Z.default;
		var q = a(542);
		t.ControlsPlay = q.default;
		var X = a(543);
		t.ControlsStop = X.default;
		var K = a(544);
		t.ControlsSkipNext = K.default;
		var J = a(545);
		t.ControlsSkipForward = J.default;
		var Q = a(546);
		t.ControlsSkipPrevious = Q.default;
		var $ = a(547);
		t.ControlsSkipBackward = $.default;
		var ee = a(548);
		t.Settings = ee.default;
		var te = a(549);
		t.OpenInNew = te.default;
		var ae = a(550);
		t.EyeOutline = ae.default;
		var oe = a(551);
		t.EyeOffOutline = oe.default
	},
	446: function (e, a, O) {
		"use strict";
		(function (o) {
			var n, t = this && this.__extends || (n = function (e, t) {
					return (n = Object.setPrototypeOf || {
							__proto__: []
						}
						instanceof Array && function (e, t) {
							e.__proto__ = t
						} || function (e, t) {
							for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
						})(e, t)
				}, function (e, t) {
					function a() {
						this.constructor = e
					}
					n(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
				}),
				r = this && this.__assign || function () {
					return (r = Object.assign || function (e) {
						for (var t, a = 1, o = arguments.length; a < o; a++)
							for (var n in t = arguments[a]) Object.prototype.hasOwnProperty.call(t, n) && (e[n] = t[n]);
						return e
					}).apply(this, arguments)
				},
				i = this && this.__decorate || function (e, t, a, o) {
					var n, r = arguments.length,
						i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
					if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
					else
						for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
					return 3 < r && i && Object.defineProperty(t, a, i), i
				};
			Object.defineProperty(a, "__esModule", {
				value: !0
			});
			var l = O(5),
				s = O(40),
				u = O(1),
				c = O(552),
				d = O(10),
				p = O(768),
				f = O(769),
				h = O(771),
				m = O(772),
				g = O(212),
				y = O(19),
				v = O(786),
				b = O(920),
				_ = O(30),
				T = O(28),
				E = O(25),
				S = O(29),
				L = O(18);
			O(85);
			var e = function (a) {
				function e(e) {
					var t = a.call(this, e) || this;
					return t.handleSnackbarClose = function () {
						_.bajoodooLog("handleSnackbarClose"), d.default.snackbarOpen = !1
					}, t.state = {
						isProjectLoaded: !1,
						areProjectsLoaded: !1,
						loading: !1,
						isInputOpen: !1,
						left: !1,
						snackbarOpen: d.default.snackbarOpen,
						snackbarTransition: u.createElement(T.Slide, r({
							direction: "up"
						}, e)),
						snackbarText: d.default.snackbar
					}, t
				}
				return t(e, a), e.prototype.componentDidMount = function () {
					var t = this;
					this.areProjectsLoaded = g.default.areProjectsLoaded, this.setState({
						loading: !this.props.areProjectsLoaded
					}), d.default.emitter.on(L.WINDOW_RESIZED, function () {
						t.onResize()
					}), o(document).keydown(function (e) {
						t.handleKeydown(e)
					})
				}, e.prototype.componentDidCatch = function (e, t) {
					console.log(e, t)
				}, e.prototype.componentDidUpdate = function () {
					var e = this;
					setTimeout(function () {
						return e.onResize()
					}, 10)
				}, e.prototype.onResize = function () {
					var e = this.props.theme,
						t = o("#modalpaper").outerWidth(),
						a = o("#modalpaper").outerHeight();
					o("#modalpaper").css("left", window.innerWidth / 2 - t / 2), o("#modalpaper").css("top", window.innerHeight / 2 - a / 2), o("#modalpaper").css("transform", "inherit"), o("#modalpaper").css("width", 0 < d.default.modalWidth ? window.innerWidth < e.breakpoints.values.sm ? window.innerWidth - 20 : d.default.modalWidth : "auto"), o("#modalpaper").css("max-height", window.innerHeight - 40), o("#modalpaper").css("max-width", window.innerWidth < e.breakpoints.values.sm ? window.innerWidth - 20 : window.innerWidth / 1.1), d.default.windowWidth = o(window).width(), d.default.windowHeight = o(window).height(), d.default.vars.isExtraSmall = o(window).width() < e.breakpoints.values.xs, d.default.vars.isSmall = o(window).width() < e.breakpoints.values.sm && o(window).width() >= e.breakpoints.values.xs, d.default.vars.isMedium = o(window).width() < e.breakpoints.values.md && o(window).width() >= e.breakpoints.values.sm, d.default.vars.isLarge = o(window).width() < e.breakpoints.values.lg && o(window).width() >= e.breakpoints.values.md, d.default.vars.isExtraLarge = o(window).width() < e.breakpoints.values.xl && o(window).width() >= e.breakpoints.values.lg, d.default.vars.isMobile = d.default.vars.isExtraSmall || d.default.vars.isSmall || d.default.vars.isMedium
				}, e.prototype.renderModalControl = function () {
					var e = this,
						t = this.props.classes;
					return u.createElement("div", {
						className: t.closeIcon,
						onClick: function () {
							return e.handleModalClose()
						}
					}, u.createElement(s.Close, null))
				}, e.prototype.getModalStyle = function () {
					var e = this.props.theme;
					return {
						top: "50%",
						left: "50%",
						transform: "translate(-50%, -50%)",
						maxHeight: d.default.windowHeight - 20,
						maxWidth: window.innerWidth < e.breakpoints.values.sm ? window.innerWidth - 20 : window.innerWidth / 1.4,
						width: 0 < d.default.modalWidth ? window.innerWidth < e.breakpoints.values.sm ? window.innerWidth - 20 : d.default.modalWidth : "auto",
						overflow: "auto"
					}
				}, e.prototype.renderModal = function () {
					var t = this,
						e = this.props.classes;
					return u.createElement(T.Modal, {
						id: "modal",
						className: l(e.modal, "modal" + d.default.modalTime),
						"aria-labelledby": "simple-modal-title",
						"aria-describedby": "simple-modal-description",
						open: d.default.modalOpen,
						onClose: function (e) {
							return t.handleModalClose()
						}
					}, u.createElement("div", {
						id: "modalpaper",
						className: e.paper,
						style: this.getModalStyle()
					}, this.renderModalControl(), d.default.modalContent))
				}, e.prototype.handleModalClose = function () {
					d.default.modalWidth = 0, d.default.modalOpen = !1
				}, e.prototype.handleKeydown = function (e) {
					e.originalEvent.ctrlKey && e.originalEvent.shiftKey && "v" == e.originalEvent.key.toLowerCase() && (d.default.setSnackbarText("Version: 1.0.3\nBuild: " + O.h), d.default.snackbarOpen = !0)
				}, e.prototype.renderApp = function () {
					return y.default.isProjectLoaded ? u.createElement("div", {
						id: "app"
					}, d.default.vars.isEmbedded ? null : u.createElement(c.default, null), u.createElement(v.default, null), u.createElement(b.default, null), u.createElement(m.default, null), u.createElement(p.default, null)) : null
				}, e.prototype.render = function () {
					return u.createElement("div", null, u.createElement(h.default, null), this.renderModal(), this.renderApp(), u.createElement(f.default, null), u.createElement(T.Snackbar, {
						open: d.default.snackbarOpen,
						onClose: this.handleSnackbarClose,
						TransitionComponent: this.state.Transition,
						ContentProps: {
							"aria-describedby": "message-id"
						},
						message: u.createElement("span", {
							id: "message-id",
							className: "snackbarText"
						}, d.default.snackbar)
					}))
				}, i([E.observable], e.prototype, "areProjectsLoaded", void 0), e = i([S.observer], e)
			}(u.Component);
			a.default = T.withStyles(function (e) {
				return {
					root: {},
					modal: {
						maxHeight: window.innerHeight,
						maxWidth: window.innerWidth
					},
					paper: {
						backgroundColor: e.palette.background.paper,
						boxShadow: e.shadows[5],
						padding: 10,
						position: "relative"
					},
					closeIcon: {
						position: "absolute",
						right: 10,
						top: 10,
						cursor: "pointer"
					}
				}
			}, {
				withTheme: !0
			})(e)
		}).call(this, O(31))
	},
	447: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = r(a(1)),
			n = r(a(80));

		function r(e) {
			return e && e.__esModule ? e : {
				default: e
			}
		}
		t.default = (0, n.default)(o.default.createElement("g", null, o.default.createElement("path", {
			d: "M 2,2 V 22 H 7.5822427 V 18.938868 H 7.9902518 V 22 H 22 V 2 Z m 0.4801218,15.918256 h 0.4082455 v 1.225909 c 0.1706418,-0.128362 0.3821427,-0.205297 0.6121318,-0.205297 0.5635554,0 1.0203773,0.456786 1.0203773,1.020378 0,-0.563592 0.4568154,-1.020378 1.0203772,-1.020378 0.2300564,0 0.4416909,0.07708 0.6123664,0.205535 v -0.205535 h 0.40801 v 1.020378 1.020377 H 6.15362 v -0.205531 c -0.1706764,0.128446 -0.38231,0.205531 -0.6123664,0.205531 -0.5635618,0 -1.0203772,-0.456849 -1.0203772,-1.020377 0,0.563528 -0.4568219,1.020377 -1.0203773,1.020377 -0.2299891,0 -0.44149,-0.07694 -0.6121318,-0.205298 v 0.205298 H 2.4801218 Z m 11.3265612,0 h 0.408245 v 3.061367 h -0.408245 v -0.205531 c -0.17068,0.128446 -0.382274,0.205531 -0.612368,0.205531 -0.56351,0 -1.020377,-0.456849 -1.020377,-1.020377 0,0.563528 -0.456803,1.020377 -1.020376,1.020377 -0.563584,0 -1.020377,-0.456849 -1.020377,-1.020377 0,0.563528 -0.4567305,1.020377 -1.0203768,1.020377 -0.5635018,0 -1.0203773,-0.456849 -1.0203773,-1.020377 0,-0.563592 0.4568755,-1.020378 1.0203773,-1.020378 0.5636463,0 1.0203768,0.456786 1.0203768,1.020378 0,-0.563592 0.456793,-1.020378 1.020377,-1.020378 0.563573,0 1.020376,0.456786 1.020376,1.020378 0,-0.563592 0.456867,-1.020378 1.020377,-1.020378 0.230095,0 0.441688,0.07708 0.612368,0.205535 z m -7.0409303,2.41e-4 h 0.4082446 v 2.448764 H 6.7657527 Z m 1.0203773,0.40801 c 0.1127191,0 0.2041218,0.09138 0.2041218,0.204122 0,0.112682 -0.091403,0.204122 -0.2041218,0.204122 -0.1127264,0 -0.2038891,-0.09144 -0.2038891,-0.204122 0,-0.112746 0.091159,-0.204122 0.2038891,-0.204122 z m 7.551118,0.612367 c 0.563574,0 1.020377,0.456785 1.020377,1.020378 0,-0.563593 0.456803,-1.020378 1.020378,-1.020378 0.563582,0 1.020612,0.456785 1.020612,1.020378 0,0.563527 -0.45703,1.020376 -1.020612,1.020376 -0.563575,0 -1.020378,-0.456849 -1.020378,-1.020376 0,0.563527 -0.456803,1.020376 -1.020377,1.020376 -0.563511,0 -1.020377,-0.456849 -1.020377,-1.020376 0,-0.563593 0.456866,-1.020378 1.020377,-1.020378 z M 3.5004991,19.346885 c -0.3381555,0 -0.6121318,0.274211 -0.6121318,0.612367 0,0.338127 0.2739763,0.612132 0.6121318,0.612132 0.3381282,0 0.6123673,-0.274006 0.6123673,-0.612132 0,-0.338158 -0.2742391,-0.612367 -0.6123673,-0.612367 z m 2.0407545,0 c -0.3381236,0 -0.6121327,0.274211 -0.6121327,0.612367 0,0.338127 0.2740091,0.612132 0.6121327,0.612132 0.3381546,0 0.6123664,-0.274006 0.6123664,-0.612132 0,-0.338158 -0.2742109,-0.612367 -0.6123664,-0.612367 z m 3.5715546,0 c -0.3380927,0 -0.6123673,0.274211 -0.6123673,0.612367 0,0.338127 0.2742764,0.612132 0.6123673,0.612132 0.3381645,0 0.6121318,-0.274006 0.6121318,-0.612132 0,-0.338158 -0.2739673,-0.612367 -0.6121318,-0.612367 z m 2.0407538,0 c -0.338165,0 -0.612133,0.274211 -0.612133,0.612367 0,0.338127 0.273968,0.612132 0.612133,0.612132 0.338164,0 0.612366,-0.274006 0.612366,-0.612132 0,-0.338158 -0.274201,-0.612367 -0.612366,-0.612367 z m 2.040753,0 c -0.338092,0 -0.612132,0.274211 -0.612132,0.612367 0,0.338127 0.27404,0.612132 0.612132,0.612132 0.338165,0 0.612368,-0.274006 0.612368,-0.612132 0,-0.338158 -0.274201,-0.612367 -0.612368,-0.612367 z m 2.142933,0 c -0.338093,0 -0.612133,0.274211 -0.612133,0.612367 0,0.338127 0.27404,0.612132 0.612133,0.612132 0.338155,0 0.612132,-0.274006 0.612132,-0.612132 0,-0.338158 -0.273978,-0.612367 -0.612132,-0.612367 z m 2.040755,0 c -0.338154,0 -0.612134,0.274211 -0.612134,0.612367 0,0.338127 0.273978,0.612132 0.612134,0.612132 0.338092,0 0.612366,-0.274006 0.612366,-0.612132 0,-0.338158 -0.274276,-0.612367 -0.612366,-0.612367 z M 6.9698745,20.571378 c 0.11272,0 0.2041228,0.0914 0.2041228,0.204122 0,0.112709 -0.091403,0.204122 -0.2041228,0.204122 -0.112719,0 -0.2041218,-0.09141 -0.2041218,-0.204122 0,-0.112729 0.091403,-0.204122 0.2041218,-0.204122 z m 11.6326275,0 c 0.112671,0 0.204122,0.0914 0.204122,0.204122 0,0.112709 -0.09145,0.204122 -0.204122,0.204122 -0.112737,0 -0.203888,-0.09141 -0.203888,-0.204122 0,-0.112729 0.09115,-0.204122 0.203888,-0.204122 z"
		})))
	},
	48: function (e, t, r) {
		"use strict";
		var a = this && this.__decorate || function (e, t, a, o) {
			var n, r = arguments.length,
				i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
			if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
			else
				for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
			return 3 < r && i && Object.defineProperty(t, a, i), i
		};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var i = r(18),
			l = r(39),
			s = r(17),
			u = r(19),
			c = r(20),
			o = r(25),
			d = r(30),
			n = r(753),
			p = r(377),
			f = r(376),
			h = r(10),
			m = function () {
				function e() {
					this.datasets = [], this.dataInfo = null, this.currentRegionInfo = new f.InfoObject
				}
				return e.prototype.has = function (e) {
					return d.bajoodooLog("DatasetStore.has(" + e + ")"), this.datasets["_" + e] instanceof p.Dataset ? 1 != this.datasets["_" + e].isLoading && (h.default.emitter.emit("DATASET" + e), !0) : (this.load(e), !1)
				}, e.prototype.removeDataset = function (e) {
					void 0 !== this.datasets["_" + e] && delete this.datasets["_" + e]
				}, e.prototype.get = function (e) {
					if (d.bajoodooLog("DatasetStore.get(" + e + ")"), this.datasets["_" + e] instanceof p.Dataset && 1 != this.datasets["_" + e].isLoading) return this.datasets["_" + e];
					throw d.bajoodooLog("DatasetStore.get(" + e + ") throws error"), new Error("dataset not available")
				}, e.prototype.getValue = function (e, t) {
					try {
						return this.get(e).values["|" + t].value
					} catch (e) {
						return null
					}
				}, e.prototype.load = function (t) {
					var a = this;
					if (this.datasets["_" + t] instanceof p.Dataset && 1 == this.datasets["_" + t].isLoading) d.bajoodooLog("DatasetStore.load(" + t + ") - already loading!!!");
					else {
						d.bajoodooLog("DatasetStore.load(" + t + ")");
						var e = s.default.localization,
							o = {
								ida: t,
								info: null,
								languages: [],
								meta_ext: {},
								standardVis: {},
								values: {},
								type: null,
								isLoading: !0
							};
						this.datasets["_" + t] = new p.Dataset(o);
						var n = {
							id: t.toString(),
							type: i.LOADING_TYPE_DATASET,
							message: e.messages.LOADING_DATASET + [" (", t, ")"].join("")
						};
						h.default.setIsLoading(n), l.default.create({
							baseURL: _WWW,
							responseType: "json"
						}).get(h.default.paths._JSON + u.default.projectIda + "/data/" + t + ".json?" + r.h, {
							onDownloadProgress: function (e) {
								n.percent = Math.round(100 * e.loaded / e.total), h.default.setLoadingPercent(n)
							}
						}).then(function (e) {
							a.datasets["_" + t] = new p.Dataset(e.data), a.datasets["_" + t].ida = t, a.datasets["_" + t].isLoading = !1;
							a.datasets["_" + t].info.isAbsolute = 1 == a.datasets["_" + t].info.isAbsolute, c.default.datasetLoaded(), h.default.emitter.emit("DATASET" + t), h.default.setNotLoading(n)
						}).catch(function (e) {
							throw d.bajoodooLog(e), new Error("dataset loader error")
						})
					}
				}, e.prototype.addVirtualDataset = function (e) {
					d.bajoodooLog("DatasetStore.addVirtualDataset()"), this.deleteVirtualDataset();
					try {
						return this.datasets._0 = e, !0
					} catch (e) {
						throw d.bajoodooLog("DatasetStore.addVirtualDataset() throws error"), new Error("addVirtualDataset malfunction")
					}
				}, e.prototype.deleteVirtualDataset = function () {
					d.bajoodooLog("DatasetStore.deleteVirtualDataset()");
					try {
						return this.datasets._0 instanceof p.Dataset && delete this.datasets._0, !0
					} catch (e) {
						throw d.bajoodooLog("DatasetStore.deleteVirtualDataset() throws error"), new Error("deleteVirtualDataset malfunction")
					}
				}, e.prototype.initDataInfo = function () {
					return this.dataInfo = new n.DataInfo, !0
				}, e.prototype.unsetDataInfo = function () {
					this.dataInfo = null
				}, e.prototype.getDataInfo = function () {
					return this.dataInfo
				}, a([o.observable], e.prototype, "dataInfo", void 0), a([o.observable], e.prototype, "currentRegionInfo", void 0), a([o.action], e.prototype, "load", null), e
			}();
		t.default = new m
	},
	498: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = r(a(1)),
			n = r(a(80));

		function r(e) {
			return e && e.__esModule ? e : {
				default: e
			}
		}
		t.default = (0, n.default)(o.default.createElement("g", null, o.default.createElement("path", {
			d: "m 3.2682988,3.721412 c 0,0 3.2640307,-1.4303515 8.7318082,-1.4303515 5.467776,0 8.731807,1.4303515 8.731807,1.4303515 0,0 0.291061,6.328482 -0.947191,9.910188 -2.187941,6.29771 -7.784616,8.063201 -7.784616,8.063201 0,0 -5.5966745,-1.765491 -7.7846165,-8.063201 C 2.9772393,10.049894 3.2682988,3.721412 3.2682988,3.721412 Z M 10.253745,9.2765065 H 6.1789025 v 3.4927225 h 4.0748425 v 4.074844 h 3.492724 v -4.074844 h 4.074842 V 9.2765065 H 13.746469 V 5.201663 h -3.492724 z"
		})))
	},
	499: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = r(a(1)),
			n = r(a(80));

		function r(e) {
			return e && e.__esModule ? e : {
				default: e
			}
		}
		t.default = (0, n.default)(o.default.createElement("g", null, o.default.createElement("path", {
			d: "m 14.394516,1 h 0.887097 C 15.722147,1 16.08,1.3578531 16.08,1.7983871 v 1.2419355 c 0,0.440534 -0.357853,0.7983871 -0.798387,0.7983871 h -0.887097 c -0.440534,0 -0.798387,-0.3578531 -0.798387,-0.7983871 V 1.7983871 C 13.596129,1.3578531 13.953982,1 14.394516,1 M 5.8783871,4.1935484 h 0.8870968 c 0.440534,0 0.7983871,0.3578531 0.7983871,0.7983871 V 6.233871 c 0,0.440534 -0.3578531,0.7983871 -0.7983871,0.7983871 H 5.8783871 C 5.4378531,7.0322581 5.08,6.674405 5.08,6.233871 V 4.9919355 c 0,-0.440534 0.3578531,-0.7983871 0.7983871,-0.7983871 m 2.8387097,0 h 0.8870967 c 0.4405345,0 0.7983875,0.3578531 0.7983875,0.7983871 V 6.233871 c 0,0.440534 -0.357853,0.7983871 -0.7983875,0.7983871 H 8.7170968 c -0.4405341,0 -0.7983871,-0.3578531 -0.7983871,-0.7983871 V 4.9919355 c 0,-0.440534 0.357853,-0.7983871 0.7983871,-0.7983871 m 2.8387092,0 h 0.887097 c 0.440534,0 0.798387,0.3578531 0.798387,0.7983871 V 6.233871 c 0,0.440534 -0.357853,0.7983871 -0.798387,0.7983871 h -0.887097 c -0.440534,0 -0.798387,-0.3578531 -0.798387,-0.7983871 V 4.9919355 c 0,-0.440534 0.357853,-0.7983871 0.798387,-0.7983871 m 2.83871,0 h 0.887097 c 0.440534,0 0.798387,0.3578531 0.798387,0.7983871 V 6.233871 c 0,0.440534 -0.357853,0.7983871 -0.798387,0.7983871 h -0.887097 c -0.440534,0 -0.798387,-0.3578531 -0.798387,-0.7983871 V 4.9919355 c 0,-0.440534 0.357853,-0.7983871 0.798387,-0.7983871 M 5.8783871,7.3870968 h 0.8870968 c 0.440534,0 0.7983871,0.357853 0.7983871,0.7983871 v 1.2419355 c 0,0.440534 -0.3578531,0.7983866 -0.7983871,0.7983866 H 5.8783871 C 5.4378531,10.225806 5.08,9.8679534 5.08,9.4274194 V 8.1854839 c 0,-0.4405341 0.3578531,-0.7983871 0.7983871,-0.7983871 m 2.8387097,0 h 0.8870967 c 0.4405345,0 0.7983875,0.357853 0.7983875,0.7983871 v 1.2419355 c 0,0.440534 -0.357853,0.7983866 -0.7983875,0.7983866 H 8.7170968 c -0.4405341,0 -0.7983871,-0.3578526 -0.7983871,-0.7983866 V 8.1854839 c 0,-0.4405341 0.357853,-0.7983871 0.7983871,-0.7983871 m 2.8387092,0 h 0.887097 c 0.440534,0 0.798387,0.357853 0.798387,0.7983871 v 1.2419355 c 0,0.440534 -0.357853,0.7983866 -0.798387,0.7983866 h -0.887097 c -0.440534,0 -0.798387,-0.3578526 -0.798387,-0.7983866 V 8.1854839 c 0,-0.4405341 0.357853,-0.7983871 0.798387,-0.7983871 m 2.83871,0 h 0.887097 c 0.440534,0 0.798387,0.357853 0.798387,0.7983871 v 1.2419355 c 0,0.440534 -0.357853,0.7983866 -0.798387,0.7983866 h -0.887097 c -0.440534,0 -0.798387,-0.3578526 -0.798387,-0.7983866 V 8.1854839 c 0,-0.4405341 0.357853,-0.7983871 0.798387,-0.7983871 M 8.7170968,10.580645 h 0.8870967 c 0.4405345,0 0.7983875,0.357853 0.7983875,0.798387 v 1.241936 c 0,0.440534 -0.357853,0.798387 -0.7983875,0.798387 H 8.7170968 c -0.4405341,0 -0.7983871,-0.357853 -0.7983871,-0.798387 v -1.241936 c 0,-0.440534 0.357853,-0.798387 0.7983871,-0.798387 m 2.8387092,0 h 0.887097 c 0.440534,0 0.798387,0.357853 0.798387,0.798387 v 1.241936 c 0,0.440534 -0.357853,0.798387 -0.798387,0.798387 h -0.887097 c -0.440534,0 -0.798387,-0.357853 -0.798387,-0.798387 v -1.241936 c 0,-0.440534 0.357853,-0.798387 0.798387,-0.798387 m 2.83871,0 h 0.887097 c 0.440534,0 0.798387,0.357853 0.798387,0.798387 v 1.241936 c 0,0.440534 -0.357853,0.798387 -0.798387,0.798387 h -0.887097 c -0.440534,0 -0.798387,-0.357853 -0.798387,-0.798387 v -1.241936 c 0,-0.440534 0.357853,-0.798387 0.798387,-0.798387 m 2.83871,0 h 0.887097 c 0.440534,0 0.798387,0.357853 0.798387,0.798387 v 1.241936 c 0,0.440534 -0.357853,0.798387 -0.798387,0.798387 h -0.887097 c -0.440534,0 -0.798387,-0.357853 -0.798387,-0.798387 v -1.241936 c 0,-0.440534 0.357853,-0.798387 0.798387,-0.798387 m -8.5161292,3.193549 h 0.8870967 c 0.4405345,0 0.7983875,0.357853 0.7983875,0.798387 v 1.241935 c 0,0.440534 -0.357853,0.798387 -0.7983875,0.798387 H 8.7170968 c -0.4405341,0 -0.7983871,-0.357853 -0.7983871,-0.798387 v -1.241935 c 0,-0.440534 0.357853,-0.798387 0.7983871,-0.798387 m 2.8387092,0 h 0.887097 c 0.440534,0 0.798387,0.357853 0.798387,0.798387 v 1.241935 c 0,0.440534 -0.357853,0.798387 -0.798387,0.798387 h -0.887097 c -0.440534,0 -0.798387,-0.357853 -0.798387,-0.798387 v -1.241935 c 0,-0.440534 0.357853,-0.798387 0.798387,-0.798387 m -5.6774189,3.193548 h 0.8870968 c 0.440534,0 0.7983871,0.357853 0.7983871,0.798387 v 1.241936 c 0,0.440534 -0.3578531,0.798387 -0.7983871,0.798387 H 5.8783871 C 5.4378531,19.806452 5.08,19.448599 5.08,19.008065 v -1.241936 c 0,-0.440534 0.3578531,-0.798387 0.7983871,-0.798387 m 2.8387097,0 h 0.8870967 c 0.4405345,0 0.7983875,0.357853 0.7983875,0.798387 v 1.241936 c 0,0.440534 -0.357853,0.798387 -0.7983875,0.798387 H 8.7170968 c -0.4405341,0 -0.7983871,-0.357853 -0.7983871,-0.798387 v -1.241936 c 0,-0.440534 0.357853,-0.798387 0.7983871,-0.798387 M 5.8783871,20.16129 h 0.8870968 c 0.440534,0 0.7983871,0.357853 0.7983871,0.798387 v 1.241936 C 7.563871,22.642147 7.2060179,23 6.7654839,23 H 5.8783871 C 5.4378531,23 5.08,22.642147 5.08,22.201613 v -1.241936 c 0,-0.440534 0.3578531,-0.798387 0.7983871,-0.798387 z"
		})))
	},
	500: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = r(a(1)),
			n = r(a(80));

		function r(e) {
			return e && e.__esModule ? e : {
				default: e
			}
		}
		t.default = (0, n.default)(o.default.createElement("g", null, o.default.createElement("path", {
			d: "M 12 2 L 3 9 L 4.6308594 10.269531 L 12 16 L 19.359375 10.269531 L 21 9 L 12 2 z M 12 4.5527344 L 17.785156 9.0527344 L 16.732422 9.8691406 L 12 13.552734 L 7.2617188 9.8691406 L 6.2148438 9.0527344 L 12 4.5527344 z M 19.369141 12.800781 L 12 18.539062 L 4.6191406 12.810547 L 3 14.070312 L 12 21.070312 L 21 14.070312 L 19.369141 12.800781 z"
		})))
	},
	501: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = r(a(1)),
			n = r(a(80));

		function r(e) {
			return e && e.__esModule ? e : {
				default: e
			}
		}
		t.default = (0, n.default)(o.default.createElement("g", null, o.default.createElement("path", {
			d: "M 9 3 L 3.359375 4.9003906 C 3.159375 4.9703906 3 5.1508594 3 5.3808594 L 3 20.5 C 3 20.776142 3.2238576 21 3.5 21 C 3.55 21 3.6001562 21.000703 3.6601562 20.970703 L 9 18.900391 L 13.160156 20.359375 C 13.060156 19.919375 13 19.46 13 19 C 13 18.77 12.999063 18.540781 13.039062 18.300781 L 9 16.900391 L 9 5 L 15 7.0996094 L 15 14.560547 C 16.07 13.600547 17.47 13 19 13 C 19.7 13 20.37 13.129375 21 13.359375 L 21 3.5 C 21 3.2238576 20.776142 3 20.5 3 L 20.339844 3 L 15 5.0996094 L 9 3 z M 18 15 L 18 16.601562 L 20 16.601562 L 20 15 L 18 15 z M 18 18 L 18 23 L 20 23 L 20 18 L 18 18 z"
		})))
	},
	502: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = r(a(1)),
			n = r(a(80));

		function r(e) {
			return e && e.__esModule ? e : {
				default: e
			}
		}
		t.default = (0, n.default)(o.default.createElement("g", null, o.default.createElement("path", {
			d: "M 9 3 L 3.359375 4.9003906 C 3.149375 4.9703906 3 5.1508594 3 5.3808594 L 3 20.5 A 0.5 0.5 0 0 0 3.5 21 L 3.6601562 20.970703 L 9 18.900391 L 13.169922 20.359375 A 5.9729552 5.9729552 0 0 1 13.083984 19.976562 A 5.9729552 5.9729552 0 0 1 13.011719 19.384766 A 5.9729552 5.9729552 0 0 1 12.998047 18.960938 A 5.9729552 5.9729552 0 0 1 13.027344 18.365234 A 5.9729552 5.9729552 0 0 1 13.052734 18.197266 L 10 17.130859 L 10 5.4707031 L 14 6.8691406 L 14 15.65625 A 5.9729552 5.9729552 0 0 1 14.041016 15.589844 A 5.9729552 5.9729552 0 0 1 14.402344 15.113281 A 5.9729552 5.9729552 0 0 1 14.808594 14.677734 A 5.9729552 5.9729552 0 0 1 15.257812 14.283203 A 5.9729552 5.9729552 0 0 1 15.744141 13.935547 A 5.9729552 5.9729552 0 0 1 16 13.789062 L 16 6.859375 L 19 5.6992188 L 19 12.990234 A 5.9729552 5.9729552 0 0 1 19.566406 13.019531 A 5.9729552 5.9729552 0 0 1 20.15625 13.107422 A 5.9729552 5.9729552 0 0 1 20.736328 13.255859 A 5.9729552 5.9729552 0 0 1 21 13.351562 L 21 3.5 A 0.5 0.5 0 0 0 20.5 3 L 20.339844 3.0292969 L 15 5.0996094 L 9 3 z M 8 5.4492188 L 8 17.150391 L 5 18.310547 L 5 6.4609375 L 8 5.4492188 z M 18 15 L 18 16.601562 L 20 16.601562 L 20 15 L 18 15 z M 18 18 L 18 23 L 20 23 L 20 18 L 18 18 z"
		})))
	},
	503: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = r(a(1)),
			n = r(a(80));

		function r(e) {
			return e && e.__esModule ? e : {
				default: e
			}
		}
		t.default = (0, n.default)(o.default.createElement("g", null, o.default.createElement("path", {
			d: "M 9 3 L 3.359375 4.9003906 C 3.149375 4.9703906 3 5.1508594 3 5.3808594 L 3 20.5 A 0.5 0.5 0 0 0 3.5 21 L 3.6601562 20.970703 L 9 18.900391 L 15 21 L 20.640625 19.099609 C 20.850625 19.029609 21 18.849141 21 18.619141 L 21 3.5 A 0.5 0.5 0 0 0 20.5 3 L 20.339844 3.0292969 L 15 5.0996094 L 9 3 z M 8 5.4492188 L 8 17.150391 L 5 18.310547 L 5 6.4609375 L 8 5.4492188 z M 10 5.4707031 L 14 6.8691406 L 14 18.529297 L 10 17.130859 L 10 5.4707031 z M 19 5.6992188 L 19 17.539062 L 16 18.550781 L 16 6.859375 L 19 5.6992188 z M 18.462891 6.5078125 L 16.574219 7.2402344 L 16.574219 9.3867188 L 18.462891 8.65625 L 18.462891 6.5078125 z M 18.462891 9.2578125 L 16.574219 9.9902344 L 16.574219 12.136719 L 18.462891 11.40625 L 18.462891 9.2578125 z M 18.462891 12.007812 L 16.574219 12.740234 L 16.574219 14.886719 L 18.462891 14.15625 L 18.462891 12.007812 z M 18.462891 14.757812 L 16.574219 15.490234 L 16.574219 17.636719 L 18.462891 16.90625 L 18.462891 14.757812 z"
		})))
	},
	552: function (e, a, b) {
		"use strict";
		(function (t) {
			var o, n = this && this.__extends || (o = function (e, t) {
					return (o = Object.setPrototypeOf || {
							__proto__: []
						}
						instanceof Array && function (e, t) {
							e.__proto__ = t
						} || function (e, t) {
							for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
						})(e, t)
				}, function (e, t) {
					function a() {
						this.constructor = e
					}
					o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
				}),
				r = this && this.__decorate || function (e, t, a, o) {
					var n, r = arguments.length,
						i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
					if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
					else
						for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
					return 3 < r && i && Object.defineProperty(t, a, i), i
				};
			Object.defineProperty(a, "__esModule", {
				value: !0
			});
			var i = b(1),
				l = b(28),
				s = b(10),
				u = b(20),
				c = b(19),
				d = b(17),
				p = b(380),
				f = b(767),
				h = b(79),
				m = b(18),
				g = b(29),
				y = b(85),
				v = (b(39), b(83)),
				e = function (a) {
					function e(e) {
						var t = a.call(this, e) || this;
						return t.state = {}, t.handleMiniHamburgerClick = function (e) {
							t.setState({
								anchorEl: e.currentTarget
							})
						}, t.handleMiniHamburgerMenuClose = function () {
							t.setState({
								anchorEl: null
							})
						}, t.state = {
							treecrumb: null,
							anchorEl: null
						}, t
					}
					return n(e, a), e.prototype.componentDidMount = function () {
						var e = this;
						s.default.emitter.on(m.WINDOW_RESIZED, function () {
							e.updateHeaderHeight()
						}), this.updateHeaderHeight(), this.fetchTreecrumb()
					}, e.prototype.componentDidUpdate = function () {
						this.updateHeaderHeight()
					}, e.prototype.componentWillUpdate = function () {}, e.prototype.updateHeaderHeight = function () {
						s.default.appHeaderHeight = s.default.vars.isEmbedded ? 0 : t("#appHeader").outerHeight()
					}, e.prototype.handleImprint = function () {
						s.default.modalContent = i.createElement(p.default, null), s.default.modalWidth = 700, s.default.modalOpen = !0
					}, e.prototype.handleGlossary = function () {
						s.default.modalContent = i.createElement(f.default, null), s.default.modalWidth = 700, s.default.modalOpen = !0
					}, e.prototype.handlePortal = function () {
						location.href = null !== v.default.current ? v.default.current.data.elements.headerbar.link.url.toString().replace(/#lng#/, d.default.localization.codeShort) : _WWW
					}, e.prototype.handleContact = function () {
						var e = /(\w|[_.\-])+@((\w|-)+\.)+\w{2,}/;
						e = RegExp(e), null != d.default.localization.messages["CONTACT_ADDRESS_" + c.default.getProject().design] && e.test(d.default.localization.messages["CONTACT_ADDRESS_" + c.default.getProject().design]) ? location.href = "mailto:" + d.default.localization.messages["CONTACT_ADDRESS_" + c.default.getProject().design] : (s.default.setSnackbarText(d.default.localization.messages.CONTACT_ADDRESS_UNAVAILABLE), s.default.snackbarOpen = !0)
					}, e.prototype.renderPathElements = function (e, t) {
						if (0 == t) return null;
						var a = [];
						return a.push(i.createElement("span", {
							key: "path" + t,
							className: "href"
						}, e[d.default.localization.ida])), a.push(this.renderSeparator(t)), a
					}, e.prototype.renderSeparator = function (e) {
						return e < s.default.mapPath.length - 1 ? i.createElement("span", {
							key: "sep" + e
						}, " / ") : null
					}, e.prototype.toggleSearchMenu = function () {
						s.default.treeDrawerOpen = !0
					}, e.prototype.renderMapInformationSimple = function () {
						return i.createElement("strong", null, void 0 !== u.default.current ? u.default.current.languages[d.default.localization.ida].name : null)
					}, e.prototype.renderMapInformation = function () {
						var a = this;
						this.props.classes;
						return u.default.current ? i.createElement("div", null, s.default.vars.isMobile ? null : i.createElement("h4", {
							onClick: function () {
								return a.toggleSearchMenu()
							}
						}, s.default.mapPath.length ? s.default.mapPath.map(function (e, t) {
							return a.renderPathElements(e, t)
						}) : ""), i.createElement("h3", null, u.default.current.languages[d.default.localization.ida].name)) : null
					}, e.prototype.renderProjectHeader = function () {
						var e = this.props.classes,
							t = [i.createElement("span", {
								key: "appname",
								className: e.mobileH2Small
							}, d.default.localization.messages.PLATFORM_STATATLAS)];
						return void 0 !== d.default.localization && t.push(i.createElement("span", {
							key: "projectname",
							className: e.mobileH2Small
						}, c.default.getProject().currentLanguage().name)), t
					}, e.prototype.renderMobile = function () {
						var e = this;
						this.props.classes;
						return i.createElement("section", {
							className: "nav-mobile"
						}, i.createElement("div", {
							className: "table-row"
						}, i.createElement("div", {
							className: "nav-mobile-header"
						}, i.createElement("div", {
							className: "table-row"
						}, null !== v.default.current ? i.createElement("span", {
							className: "nav-mobile-logo"
						}, i.createElement("img", {
							src: _WWW + v.default.current.data.elements.headerbar.imagepath + v.default.current.data.elements.headerbar.images.logo.mobile,
							alt: void 0 !== d.default.localization ? d.default.localization.messages["BUTTON_PORTAL_" + v.default.current.ida] : null,
							title: void 0 !== d.default.localization ? d.default.localization.messages["BUTTON_PORTAL_" + v.default.current.ida] : null,
							onClick: function () {
								return e.handlePortal()
							},
							className: "href"
						})) : null, i.createElement("h2", null, this.renderProjectHeader(), this.renderMapInformationSimple()))), i.createElement("div", {
							className: "table-cell dropdown"
						}, i.createElement("a", {
							href: "#",
							className: "nav-mobile-menu dropdown-toggle",
							"data-toggle": "dropdown",
							"aria-haspopup": "true",
							"aria-expanded": "false"
						}, i.createElement("span", {
							className: "icon icon--menu"
						})), i.createElement("div", {
							className: "drilldown dropdown-menu",
							role: "menu"
						}, i.createElement("div", {
							className: "drilldown-container"
						}, i.createElement("nav", {
							className: "nav-page-list"
						}, i.createElement("ul", {
							role: "menu"
						}, null !== v.default.current && 1 === v.default.current.data.elements.headerbar.link.enabled ? i.createElement("li", {
							role: "presentation"
						}, i.createElement("a", {
							className: "nav-link",
							href: null !== v.default.current ? v.default.current.data.elements.headerbar.link.url : _WWW,
							role: "menuitem",
							title: null !== v.default.current ? v.default.current.data.elements.headerbar.link.url : _WWW
						}, void 0 !== d.default.localization.messages["BUTTON_PORTAL_" + v.default.current.ida] ? d.default.localization.messages["BUTTON_PORTAL_" + v.default.current.ida] : "")) : null, i.createElement("li", {
							role: "presentation"
						}, i.createElement("a", {
							className: "nav-link",
							"data-toggle": "legal-tab",
							href: "#",
							role: "menuitem",
							onClick: function () {
								return e.handleGlossary()
							}
						}, void 0 !== d.default.localization.messages.TOOLBAR_GLOSSARY ? d.default.localization.messages.TOOLBAR_GLOSSARY : "")), i.createElement("li", {
							role: "presentation"
						}, i.createElement("a", {
							className: "nav-link",
							"data-toggle": "copyright-tab",
							href: "#",
							role: "menuitem",
							onClick: function () {
								return e.handleImprint()
							}
						}, void 0 !== d.default.localization.messages.TOOLBAR_IMPRINT ? d.default.localization.messages.TOOLBAR_IMPRINT : "")), i.createElement("li", {
							role: "presentation"
						}, i.createElement("a", {
							className: "nav-link",
							href: "#",
							role: "menuitem",
							onClick: function () {
								return e.handleContact()
							}
						}, void 0 !== d.default.localization.messages.TOOLBAR_CONTACT ? d.default.localization.messages.TOOLBAR_CONTACT : "")), i.createElement("li", {
							role: "presentation"
						}, i.createElement("span", {
							className: "loc-link"
						}, void 0 !== d.default.localization.messages.BUTTON_LANGUAGE ? d.default.localization.messages.BUTTON_LANGUAGE : ""), this.renderLanguageSwitch()))))))))
					}, e.prototype.renderCommons = function () {
						var e = this;
						return i.createElement("div", {
							className: "col-5 d-none d-lg-block"
						}, i.createElement("ul", {
							className: "nav navbar-nav nav-tab",
							style: {
								float: "right",
								display: "inline"
							}
						}, i.createElement("li", {
							className: "nav-item"
						}, i.createElement("a", {
							className: "nav-link href",
							"data-toggle": "imprint-tab",
							href: "#",
							"aria-controls": "imprint",
							"aria-selected": "false",
							onClick: function () {
								return e.handleGlossary()
							}
						}, void 0 !== d.default.localization.messages.TOOLBAR_GLOSSARY ? d.default.localization.messages.TOOLBAR_GLOSSARY : "")), i.createElement("li", {
							className: "nav-item"
						}, i.createElement("a", {
							className: "nav-link",
							"data-toggle": "copyright-tab",
							href: "#",
							"aria-controls": "copyright",
							"aria-selected": "false",
							onClick: function () {
								return e.handleImprint()
							}
						}, void 0 !== d.default.localization.messages.TOOLBAR_IMPRINT ? d.default.localization.messages.TOOLBAR_IMPRINT : ""))))
					}, e.prototype.renderCommonsOld = function () {
						var e = this,
							t = this.props.classes;
						return s.default.vars.isEmbedded ? null : i.createElement("div", {
							className: t.listHorizontal
						}, i.createElement(l.Button, {
							className: t.headerButton,
							onClick: function () {
								return e.handleContact()
							}
						}, void 0 !== d.default.localization.messages.TOOLBAR_CONTACT ? d.default.localization.messages.TOOLBAR_CONTACT : ""), i.createElement(l.Button, {
							className: t.headerButton,
							onClick: function () {
								return e.handleGlossary()
							}
						}, void 0 !== d.default.localization.messages.TOOLBAR_GLOSSARY ? d.default.localization.messages.TOOLBAR_GLOSSARY : ""), i.createElement(l.Button, {
							className: t.headerButton,
							onClick: function () {
								return e.handleImprint()
							}
						}, void 0 !== d.default.localization.messages.TOOLBAR_IMPRINT ? d.default.localization.messages.TOOLBAR_IMPRINT : ""))
					}, e.prototype.switchLanguage = function (t, e) {
						var a = c.default.getProject().languages.filter(function (e) {
							return e.ida == t
						});
						a.length && (c.default.setLanguage(a[0]), d.default.changeLanguage(e), s.default.emitter.emit(m.LEGEND_RERENDER))
					}, e.prototype.renderLanguageSwitch = function () {
						var t = this,
							a = (this.props.classes, c.default.getProject()),
							o = (d.default.localization, []);
						return Object.keys(a.languages).map(function (e, t) {
							o.push({
								ida: parseInt(a.languages[e].ida),
								real_ida: parseInt(a.languages[e].realLocId),
								codeShort: h.upperCase(a.languages[e].codeShort.toString())
							})
						}), i.createElement("nav", {
							className: "nav-lang"
						}, i.createElement("ul", null, o.reverse().map(function (e) {
							return i.createElement("li", {
								key: e.ida
							}, i.createElement("a", {
								className: e.ida == d.default.localization.ida ? "active href" : "href",
								onClick: function () {
									return t.switchLanguage(e.ida, e.real_ida)
								}
							}, e.codeShort.toUpperCase()))
						})))
					}, e.prototype.fetchTreecrumb = function () {
						c.default.getProject().design;
						s.default.emitter.on(m.LANGUAGE_LOADED, function () {})
					}, e.prototype.render = function () {
						var e = this,
							t = this.props.classes,
							a = this.state.anchorEl;
						s.default.projectListSearchResult.length;
						return i.createElement(l.AppBar, {
							id: "appHeader",
							className: t.appBar
						}, i.createElement("div", {
							className: "mod mod-layout"
						}, i.createElement("div", {
							className: "clearfix nav-open"
						}, void 0 !== d.default.localization ? this.state.treecrumb : null, i.createElement("section", {
							className: "nav-services clearfix"
						}, i.createElement("h2", null, null !== v.default.current && void 0 !== d.default.localization.messages["BUTTON_PORTAL_" + v.default.current.ida] ? d.default.localization.messages["BUTTON_PORTAL_" + v.default.current.ida] : ""), i.createElement("h2", {
							className: "sr-only"
						}, null !== v.default.current && void 0 !== d.default.localization.messages["BUTTON_PORTAL_" + v.default.current.ida] ? d.default.localization.messages["BUTTON_PORTAL_" + v.default.current.ida] : ""), s.default.vars.isMobile ? null : this.renderLanguageSwitch(), i.createElement("nav", {
							className: "nav-service"
						}, i.createElement("a", {
							href: "#",
							className: "nav-mini-menu dropdown-toggle",
							"aria-haspopup": "true",
							"aria-expanded": "false",
							"aria-owns": a ? "simple-menu" : void 0,
							onClick: this.handleMiniHamburgerClick
						}, i.createElement("span", {
							className: "icon icon--menu"
						})), i.createElement(l.Menu, {
							id: "simple-menu",
							anchorEl: a,
							open: Boolean(a),
							onClose: this.handleMiniHamburgerMenuClose
						}, i.createElement(l.MenuItem, {
							onClick: function () {
								return e.handlePortal()
							},
							className: t.miniMenuItem
						}, null !== v.default.current && void 0 !== d.default.localization.messages["BUTTON_PORTAL_" + v.default.current.ida] ? d.default.localization.messages["BUTTON_PORTAL_" + v.default.current.ida] : ""), i.createElement(l.MenuItem, {
							onClick: function () {
								return e.handleGlossary()
							},
							className: t.miniMenuItem
						}, void 0 !== d.default.localization.messages.TOOLBAR_GLOSSARY ? d.default.localization.messages.TOOLBAR_GLOSSARY : ""), i.createElement(l.MenuItem, {
							onClick: function () {
								return e.handleImprint()
							},
							className: t.miniMenuItem
						}, void 0 !== d.default.localization.messages.TOOLBAR_IMPRINT ? d.default.localization.messages.TOOLBAR_IMPRINT : ""), i.createElement(l.MenuItem, {
							onClick: function () {
								return e.handleContact()
							},
							className: t.miniMenuItem
						}, void 0 !== d.default.localization.messages.TOOLBAR_CONTACT ? d.default.localization.messages.TOOLBAR_CONTACT : ""))))), i.createElement("div", {
							className: "mod mod-logo d-none d-lg-block"
						}, i.createElement("table", {
							className: "logotable"
						}, i.createElement("tbody", null, i.createElement("tr", null, i.createElement("td", null, null !== v.default.current ? i.createElement("img", {
							src: _WWW + v.default.current.data.elements.headerbar.imagepath + v.default.current.data.elements.headerbar.images.logo.name,
							alt: void 0 !== d.default.localization ? d.default.localization.messages["BUTTON_PORTAL_" + v.default.current.ida] : null,
							title: void 0 !== d.default.localization ? d.default.localization.messages["BUTTON_PORTAL_" + v.default.current.ida] : null,
							onClick: function () {
								return e.handlePortal()
							},
							className: "href"
						}) : null), i.createElement("td", null, i.createElement("h2", null, null !== v.default.current && void 0 !== d.default.localization ? d.default.localization.messages["CLIENT_" + v.default.current.ida] : "", i.createElement("br", null), void 0 !== d.default.localization ? c.default.getProject().currentLanguage().name : "")), i.createElement("td", null, this.renderMapInformation())))))), i.createElement("div", {
							className: "mod mod-layout"
						}, i.createElement("div", {
							className: "mod mod-mainnavigation"
						}, i.createElement("nav", {
							className: "nav-main yamm navbar",
							id: "main-navigation",
							role: "navigation"
						}, i.createElement("h2", {
							className: "sr-only"
						}, d.default.localization.messages.BUTTON_NAVIGATION), this.renderMobile()))))
					}, e = r([g.observer], e)
				}(i.Component);
			a.default = l.withStyles(function (e) {
				var t, a;
				return {
					typography: {
						margin: 2 * e.spacing.unit
					},
					fixedHeader: {
						position: "fixed",
						overflow: "hidden"
					},
					appBar: {
						zIndex: e.zIndex.drawer + 1,
						backgroundColor: y.white
					},
					toolBar: {
						display: "block",
						padding: 10,
						background: "none"
					},
					mapInfo: {
						padding: "1rem 1.6rem"
					},
					listHorizontal: {
						display: "inline-block",
						width: "auto"
					},
					inlineDiv: (t = {
						width: "50%",
						display: "inline-block"
					}, t[e.breakpoints.down("lg")] = {
						display: "block",
						width: "100%"
					}, t),
					commonData: (a = {
						textAlign: "right"
					}, a[e.breakpoints.up("lg")] = {
						float: "right"
					}, a),
					headerButton: {
						padding: 0,
						paddingLeft: 10,
						minWidth: "auto",
						minHeight: "auto"
					},
					miniMenuItem: {
						fontSize: "0.8em"
					},
					mobileH2Small: {
						fontSize: "0.9em",
						lineHeight: "0.9em",
						display: "block"
					}
				}
			})(e)
		}).call(this, b(31))
	},
	58: function (e, t, l) {
		"use strict";
		var s = this && this.__assign || function () {
				return (s = Object.assign || function (e) {
					for (var t, a = 1, o = arguments.length; a < o; a++)
						for (var n in t = arguments[a]) Object.prototype.hasOwnProperty.call(t, n) && (e[n] = t[n]);
					return e
				}).apply(this, arguments)
			},
			a = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var u = l(10),
			c = l(39),
			d = l(17),
			n = l(149),
			p = l(19),
			o = l(211),
			i = l(20),
			r = l(25),
			f = l(18),
			h = l(17),
			m = function () {
				function e() {
					var e = this;
					this.structures = [], this.rootStructure = [], this.currentStructureNode = null, this.pending = !1, this.queue = [], this.queuedata = [], u.default.emitter.on(f.CURRENT_STRUCTURE_UPDATED, function () {
						e.toggleStructureElements(1)
					})
				}
				return e.prototype.toggleStructureElements = function (t) {
					var a = this;
					if (t >= this.currentStructures.length) return this.currentStructures = [], void(this.currentStructureNode = null);
					var e = [],
						o = [];
					if (null === this.currentStructureNode ? e = this.rootStructure.filter(function (e) {
							return e.ida == a.currentStructures[t]
						}) : null !== this.currentStructureNode.children && (e = this.currentStructureNode.children.filter(function (e) {
							return e.ida == a.currentStructures[t]
						}), o = this.currentStructureNode.children.filter(function (e) {
							return e.map_ida == a.currentStructures[t]
						})), e.length) {
						var n = e[0];
						n.toggled = !0, n.loading = !0, n.colorized = !0, u.default.emitter.once(f.STRUCTURE_LOADED, function (e) {
							e.structure_ida === n.ida && ((a.currentStructureNode = n).colorized = !0, a.toggleStructureElements(++t))
						}), this.loadStructure(n.ida, !1, n)
					} else if (o.length) {
						o[0].colorized = !0
					}
				}, e.prototype.clearCurrentStructureNode = function () {
					this.currentStructureNode = null
				}, e.prototype.autoToggleTree = function (e, t, a) {
					e.active = !0, e.children && (e.toggled = t, 0 === e.children.length && (e.loading = !0, this.currentNode = e, this.loadStructure(e.ida, !1, e)), 1 === e.children.length ? this.autoToggleTree(e.children[0], t, a) : 1 < e.children.length && this.searchAutoToggleMap(e.children)), e.map_ida && (this.node = e, this.decolorizeStructure(this.rootStructure), this.colorizeStructure(e, 1, this.rootStructure), a && (e.colorized = !0, u.default.animationStarted || void 0 === i.default.current || i.default.current.ida == e.map_ida || (this.setCurrentStructures(e.parents.concat([e.map_ida])), u.default.emitter.emit(f.CURRENT_STRUCTURE_UPDATED), i.default.loadThematicalMap(e.map_ida)))), u.default.emitter.emit(f.STRUCTURE_UPDATED)
				}, e.prototype.searchAutoToggleMap = function (e) {
					var t = !1,
						a = null;
					e.filter(function (e) {
						return e.map_ida == i.default.current.ida && !0 === e.toggled
					});
					if (void 0 !== i.default.current && void 0 !== p.default.getProject().geometries) {
						if (!t)
							for (var o = 0; o < e.length; o++)
								if ((a = e[o]).info.GEOMETRY === i.default.current.geometry) {
									t = !0;
									break
								}
						if (void 0 !== p.default.getProject().geometries[i.default.current.geometry]) {
							if (!t)
								for (o = 0; o < e.length; o++)
									if (a.info.VALID_GEOMETRY === p.default.getProject().geometries[i.default.current.geometry].vg) {
										t = !0;
										break
									}
							if (!t)
								for (o = 0; o < e.length; o++)
									if (a.info.GEOUNIT === p.default.getProject().geometries[i.default.current.geometry].geounit) {
										t = !0;
										break
									}
						}
						if (!t)
							for (o = 0; o < e.length; o++)
								if (i.default.current.geosystem === a.info.GEOSYSTEM) {
									t = !0;
									break
								}
						t && this.autoToggleTree(a, !0, !0)
					}
				}, e.prototype.decolorizeStructure = function (e) {
					var t = this;
					e.map(function (e) {
						e.colorized = !1, Array.isArray(e.children) && e.children.length && t.decolorizeStructure(e.children)
					})
				}, e.prototype.colorizeStructure = function (t, a, e) {
					var o = this,
						n = e.filter(function (e) {
							return void 0 !== t.parents[a] && e.ida === t.parents[a]
						});
					n.length && n.map(function (e) {
						e.colorized = !0, e.children.length && o.colorizeStructure(t, a + 1, e.children)
					})
				}, e.prototype.getStructure = function (t) {
					if (this.hasStructure(t)) {
						var e = this.structures.filter(function (e) {
							return e.ida === t
						});
						return e.length ? e[0] : null
					}
					this.loadStructure(t)
				}, e.prototype.loadStructure = function (a, o, n) {
					var r, i = this;
					if (void 0 === o && (o = !1), void 0 === n && (n = null), !0 !== this.pending)
						if (this.pending = !0, this.hasStructure(a)) {
							(r = this.structures.filter(function (e) {
								return a === e.ida
							})).length && (r = r[0]);
							var e = this.buildChildren(r, o, n);
							this.treatStructure(r, e, o, n), this.finishLoading(a)
						} else {
							var t = {
								id: a.toString(),
								type: f.LOADING_TYPE_STRUCTURE,
								message: h.default.localization.messages.LOADING_PROJECT_STRUCTURE + [" (", a, ")"].join("")
							};
							u.default.setIsLoading(t), c.default.create({
								baseURL: _WWW,
								responseType: "json"
							}).get(u.default.paths._JSON + p.default.projectIda + "/structures/" + a + ".json?" + l.h, {
								onDownloadProgress: function (e) {
									t.percent = Math.round(100 * e.loaded / e.total), u.default.setLoadingPercent(t)
								}
							}).then(function (e) {
								r = s({}, e.data), i.structures.push(r);
								var t = i.buildChildren(r, o, n);
								i.treatStructure(r, t, o, n), i.finishLoading(a)
							}).catch(function (e) {
								i.finishLoading(a)
							})
						}
					else this.queue.indexOf(a) < 0 && (this.queue.push(a), this.queuedata.push({
						structureIda: a,
						isRoot: o,
						node: n
					}))
				}, e.prototype.finishLoading = function (e) {
					if (0 <= this.queue.indexOf(e) && this.queue.splice(this.queue.indexOf(e), 1), this.pending = !1, u.default.setNotLoading({
							id: e.toString(),
							type: f.LOADING_TYPE_STRUCTURE
						}), u.default.emitter.emit("STRUCTURE" + e, {
							structure_ida: e
						}), u.default.emitter.emit(f.STRUCTURE_LOADED, {
							structure_ida: e
						}), u.default.emitter.emit(f.STRUCTURE_UPDATED), 0 < this.queue.length) {
						var t = this.queue.shift(),
							a = this.queuedata.filter(function (e) {
								return e.structureIda == t
							});
						a.length && this.loadStructure(a[0].structureIda, a[0].isRoot, a[0].node)
					} else u.default.emitter.emit(f.STRUCTURE_QUEUE_FINISHED)
				}, e.prototype.buildChildren = function (r, i, l) {
					var s = [];
					return Object.keys(r.children).map(function (e, t) {
						var a = r.children[e],
							o = d.default.currentLocalizationIda,
							n = i ? [r.ida] : null !== l && void 0 !== l.parents && l.parents.length ? l.parents.slice(0) : [];
						null !== l && n.push(l.ida), s.push({
							ida: parseInt(0 === e.indexOf("_") ? e.substr(1) : e),
							name: a[o],
							toggled: !1,
							loading: !1,
							children: [],
							parents: n,
							translation: a,
							info: []
						})
					}), s
				}, e.prototype.treatStructure = function (e, t, a, n) {
					if (void 0 === n && (n = null), a) 1 === (this.rootStructure = t).length && this.autoToggleTree(t[0], !0, !0);
					else if (null !== n) {
						if (n.children = t, n.info = e.info, n.loading = !1, Object.keys(n.info).length) {
							var r = [];
							Object.keys(n.info).map(function (e, t) {
								var a = p.default.getProject().geometries,
									o = (d.default.currentLocalizationIda, n.info[e]);
								r.push({
									name: null,
									order_id: a[o.GEOMETRY].order_id,
									map_ida: parseInt(e),
									map_title: e,
									toggled: !0,
									loading: !1,
									children: null,
									parents: n.parents.slice(0).concat([n.ida]),
									translation: a[o.GEOMETRY].languages,
									info: o,
									colorized: !1
								})
							}), r.sort(function (e, t) {
								return e.order_id - t.order_id
							}), this.currentNode = n, u.default.mapPath = this.mapPath, n.children = r
						}
						if (1 === n.children.length) this.autoToggleTree(n.children[0], !0, !0);
						else if (1 < n.children.length && void 0 !== i.default.current)
							if (Object.keys(n.info).length) this.searchAutoToggleMap(n.children);
							else
								for (var o in n.children) - 1 < this.currentStructures.indexOf(n.children[o].ida) && !n.children[o].toggled && this.autoToggleTree(n.children[o], !0, !0)
					}
				}, e.prototype.getStructures = function () {
					return this.structures
				}, e.prototype.getMapTitleForNode = function (t) {
					u.default.emitter.once(f.SEARCHINDEX_AVAILABLE, function () {
						var e = o.default.getIndex(d.default.localization.codeShort);
						t.map_title = e.maptitles[t.map_ida], u.default.emitter.emit(f.STRUCTURE_UPDATED)
					}), o.default.loadIndex(d.default.localization.codeShort)
				}, e.prototype.hasStructure = function (t) {
					return 0 < this.structures.filter(function (e) {
						return e.ida === t
					}).length
				}, e.prototype.prepareRootStructure = function () {
					var e = this;
					if (n.default.hasPattern(0)) this.setupRootStructure();
					else {
						u.default.emitter.once(f.PATTERN_LOADED, function () {
							n.default.hasPattern(0) && e.setupRootStructure()
						});
						n.default.loadPattern(0)
					}
				}, e.prototype.setupRootStructure = function () {
					var e = n.default.getPattern(0).elements[0].split("_").map(function (e) {
							return parseInt(e)
						}),
						t = e[e.length - 1];
					this.loadStructure(t, !0)
				}, e.prototype.setCurrentStructures = function (e) {
					this.currentStructures = e
				}, e.prototype.getCurrentStructures = function () {
					return this.currentStructures
				}, e.prototype.startFlagRootStructure = function (e, t) {
					var a = this;
					void 0 === t && (t = []);
					n.default.clearQueue(), this.clearFlags(this.rootStructure), u.default.emitter.once(f.PATTERN_QUEUE_FINISHED, function () {
						a.prepareFlagPatterns(e)
					}), e.map(function (e) {
						n.default.loadPattern(e)
					}), t.length
				}, e.prototype.prepareFlagPatterns = function (e) {
					var o = [];
					e.map(function (e) {
						var t = n.default.getPattern(e);
						void 0 !== t && t.hasOwnProperty("elements") && t.elements.map(function (e) {
							e.split("_").reverse().slice(1, -1).map(function (e, t) {
								var a = parseInt(e);
								o[t] || (o[t] = {}), o[t][a] ? o[t][a]++ : o[t][a] = 1
							})
						})
					}), this.traverseRootStructure(o, 0, this.rootStructure)
				}, e.prototype.traverseRootStructure = function (a, o, e) {
					var n = this;
					e.map(function (t, e) {
						a[o] && a[o][t.ida] ? t.flag = a[o][t.ida] : t.flag = 0, n.getStructures().filter(function (e) {
							return e.ida === t.ida
						}).map(function (e) {
							e.flag = t.flag
						}), t.children && t.children.length && n.traverseRootStructure(a, o + 1, t.children)
					}), u.default.emitter.emit(f.STRUCTURE_UPDATED)
				}, e.prototype.clearFlags = function (e) {
					var a = this;
					void 0 === e && (e = this.rootStructure), u.default.foundMapsSearchResult = [], this.getStructures().map(function (e) {
						e.flag = 0
					}), e.map(function (e, t) {
						e.flag = 0, e.children && e.children.length ? a.clearFlags(e.children) : u.default.emitter.emit(f.STRUCTURE_UPDATED)
					})
				}, Object.defineProperty(e.prototype, "node", {
					set: function (e) {
						this.currentNode = e
					},
					enumerable: !0,
					configurable: !0
				}), e.prototype.getCurrentNode = function () {
					return this.currentNode
				}, Object.defineProperty(e.prototype, "mapPath", {
					get: function () {
						var a = this,
							e = this.getCurrentNode(),
							o = [];
						d.default.localization.ida;
						return e.parents.map(function (t) {
							var e = a.structures.filter(function (e) {
								return t == e.ida
							});
							e.length && o.push(e[0].translation)
						}), o
					},
					enumerable: !0,
					configurable: !0
				}), a([r.observable], e.prototype, "structures", void 0), a([r.observable], e.prototype, "currentNode", void 0), a([r.action], e.prototype, "node", null), a([r.computed], e.prototype, "mapPath", null), e
			}();
		t.default = new m
	},
	750: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var r = a(17),
			o = a(19),
			n = function () {
				function e(a) {
					var o = this;
					this.NUMERIC_PROJECT_IDENTIFIER = "standard", this.INDICATORS_IDENTIFIER = "indicators", this.CITY_ATLAS_IDENTIFIER = "17", Object.keys(a).map(function (e, t) {
						o[e] = a[e]
					})
				}
				return e.prototype.isMapsProject = function () {
					return !isNaN(this.project)
				}, e.prototype.isIndicatorProject = function () {
					return this.project == this.INDICATORS_IDENTIFIER
				}, e.prototype.getGeometry = function (e) {
					return this.geometries[e]
				}, e.prototype.getCurrentProdimaChapterByChapterId = function (a) {
					var o = this,
						t = r.default.localization.ida,
						n = a;
					void 0 !== this.prodima && Object.keys(this.prodima).filter(function (e, t) {
						return o.prodima[e].identifier === a
					}).map(function (e) {
						n = o.prodima[e].languages[t].name
					});
					return n
				}, e.prototype.isUACityAtlasProject = function () {
					return this.project == this.CITY_ATLAS_IDENTIFIER
				}, e.prototype.currentLanguage = function () {
					var t = r.default.localization.ida,
						e = this.languages.filter(function (e) {
							return e.ida === t
						});
					return e.length ? e[0] : o.default.language
				}, e
			}();
		t.Project = n
	},
	751: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = function (e) {
			for (var t in e) this[t] = e[t]
		};
		t.Geosystem = o
	},
	752: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = function (e) {
			for (var t in e) this[t] = e[t]
		};
		t.Localization = o
	},
	753: function (e, a, o) {
		"use strict";
		(function (b) {
			Object.defineProperty(a, "__esModule", {
				value: !0
			});
			var h = o(754),
				e = o(755),
				_ = o(756),
				c = o(376),
				m = o(18),
				T = o(17),
				E = o(84),
				S = o(20),
				d = o(19),
				p = o(210),
				n = o(30),
				L = o(48),
				f = o(79),
				O = o(172),
				i = o(10);
			b.fn.exists = function () {
				return 0 < this.length
			};
			var t = function () {
				function t() {
					this.stores = {}, this.infoValues = new e.InfoValueObject, this.setInfoValues()
				}
				return t.prototype.getRegionInfo = function (a) {
					var o = this,
						e = S.default.current,
						t = e.geometry,
						n = d.default.getProject().geometries[t].vg;
					if (null === a) try {
						var r = p.default.get(n),
							i = T.default.localization.ida;
						Object.keys(r.labels[i]).map(function (e) {
							!1 === r.labels[i][e].pseudo && (a = e.substr(6))
						})
					} catch (e) {
						return new c.InfoObject
					}
					var l = new c.InfoObject;
					l.unitId = a, l.showId = !e.isKeydataMap && void 0 !== e.properties && 1 === e.properties.showids, l.label = p.default.getLabel(a, n), l.showPercentage = !1, l.data = [];
					l.datasets = [];
					var s = S.default.current.visualizations,
						u = E.default.getVisOrder();
					return Object.keys(u).map(function (e, t) {
						u[e].map(function (e, t) {
							f.isUndefined(s[e]) || o["get" + f.upperFirst(e) + "Info"](a, s[e], l)
						})
					}), l
				}, t.prototype.getBarInfo = function (n, r, i) {
					var l, s, u, c, d, p, f = T.default.localization;
					s = r.spec.fill_transparency;
					var h = O.findIconDefinition({
						prefix: "fas",
						iconName: "chart-bar"
					});
					Object.keys(r.bars).map(function (e, t) {
						var a = r.bars[e];
						if (!1 === (c = L.default.getValue(a.dataset, n))) d = f.messages.PRIVACY_SHORT, u = S.default.current.properties.nodatacolor;
						else if (!0 === E.default.isNull(c)) {
							var o = S.default.current.properties.missing_data_label;
							d = f.properties.MISSING_DATA_LABELS[o].SHORT, u = S.default.current.properties.nodatacolor
						} else d = E.default.showValue(c, parseInt(r.spec.decimal_places)), u = a.color;
						p = a.languages[f.ida].name, (l = new _.DataTable).index = t, l.type = "bar", l.dataset = a.dataset, i.datasets.push(a.dataset), l.symbol = h, l.label = p, l.color = u, l.alpha = s, l.value = c, l.fvalue = d, i.data.push(l)
					})
				}, t.prototype.getSectInfo = function (i, l, s) {
					var u, c, d, p, f, h = T.default.localization,
						m = new _.DataTable;
					if (0 < b(l.datasets).jsonObjCount() ? (d = L.default.getValue(parseInt(Object.keys(l.datasets)[0]), i), m.dataset = parseInt(Object.keys(l.datasets)[0])) : (d = L.default.getValue(0, i), m.dataset = 0), s.datasets.push(m.dataset), !(u = 100) === d) p = h.messages.PRIVACY_SHORT, c = S.default.current.properties.nodatacolor;
					else if (!0 === E.default.isNull(d)) {
						var e = S.default.current.properties.missing_data_label;
						p = h.properties.MISSING_DATA_LABELS[e].SHORT, c = S.default.current.properties.nodatacolor
					} else p = E.default.showValue(d, parseInt(l.spec.decimal_places)), c = l.spec.ln_color;
					f = l.languages[h.ida].name;
					var t = O.findIconDefinition({
						prefix: "far",
						iconName: "circle"
					});
					m.index = 0, m.type = "sect", m.symbol = t, m.label = f, m.color = c, m.alpha = u, m.value = d, m.fvalue = p, s.data.push(m), u = l.spec.fill_transparency;
					for (var g = 1 == l.spec.show_percentage, y = 0, a = 0, o = Object.keys(l.sectors); a < o.length; a++) {
						var n, r = o[a];
						if (!1 === (n = L.default.getValue(l.sectors[r].dataset, i))) {
							y = !1;
							break
						}!1 === E.default.isNull(n) && (!0 === E.default.isUndefined(y) && (y = 0), y -= -n)
					}
					var v = 360 / b(l.sectors).jsonObjCount();
					Object.keys(l.sectors).map(function (e, t) {
						if (!1 === (d = L.default.getValue(l.sectors[e].dataset, i)) || !1 === y) p = h.messages.PRIVACY_SHORT, c = S.default.current.properties.nodatacolor;
						else if (!0 === E.default.isNull(d)) {
							var a = S.default.current.properties.missing_data_label;
							p = h.properties.MISSING_DATA_LABELS[a].SHORT, c = S.default.current.properties.nodatacolor
						} else p = E.default.showValue(d, parseInt(l.spec.decimal_places_sectors)), c = l.sectors[e].color;
						var o;
						if (f = l.sectors[e].languages[h.ida].name, !0 === g)
							if (!1 === d || !1 === y) o = h.messages.PRIVACY_SHORT;
							else if (!0 === E.default.isNull(d) || y <= 0) {
							a = S.default.current.properties.missing_data_label;
							o = h.properties.MISSING_DATA_LABELS[a].SHORT
						} else o = E.default.showValue(d / y * 100, parseInt(l.spec.decimal_places_percent)) + "%";
						var n = v * (t - 1),
							r = O.findIconDefinition({
								prefix: "fas",
								iconName: "chart-pie"
							});
						(m = new _.DataTable).rotation = n, m.alpha = u, m.color = c, m.dataset = l.sectors[e].dataset, s.datasets.push(m.dataset), m.index = t + 1, m.type = "sect", m.label = f, m.value = d, m.fvalue = p, m.symbol = r, m.percentage = d / y * 100, m.fpercentage = o, s.data.push(m)
					})
				}, t.prototype.getSymInfo = function (e, t, a) {
					var o, n, r, i, l, s, u, c = T.default.localization;
					if (r = L.default.getValue(parseInt(Object.keys(t.datasets)[0]), e), o = t.spec.fill_transparency, !1 === r) i = c.messages.PRIVACY_SHORT, n = S.default.current.properties.nodatacolor;
					else if (!0 === E.default.isNull(r)) {
						var d = S.default.current.properties.missing_data_label;
						i = c.properties.MISSING_DATA_LABELS[d].SHORT, n = S.default.current.properties.nodatacolor
					} else i = E.default.showValue(r, parseInt(t.spec.decimal_places)), n = Number(r) < 0 ? t.spec.fill_color_negative : t.spec.fill_color;
					l = t.languages[c.ida].name, 1 == t.spec.syms ? s = "circle" : 2 == t.spec.syms ? s = "square" : 3 == t.spec.syms ? s = "triangle" : 4 == t.spec.syms && (s = "shield"), void 0 !== t.fill ? (s = {
						prefix: "far",
						iconName: s
					}, u = O.findIconDefinition(s), !1 !== r && !1 === E.default.isNull(r) && (n = Number(r) < 0 ? t.spec.ln_color_negative : t.spec.ln_color)) : (s = {
						prefix: "fas",
						iconName: s
					}, u = O.findIconDefinition(s));
					var p = new _.DataTable;
					p.alpha = o, p.color = n, p.dataset = parseInt(Object.keys(t.datasets)[0]), a.datasets.push(p.dataset), p.fvalue = i, p.index = 0, p.label = l, p.symbol = u, p.type = "sym", p.value = r, a.data.push(p), 0 !== t.spec.rotation && (p.rotation = t.spec.rotation), void 0 !== t.fill && this.getChoroInfo(e, t.fill, a)
				}, t.prototype.getQualiInfo = function (e, a, t) {
					var o, n, r, i, l, s = T.default.localization,
						u = new _.DataTable;
					if (!100 === (n = L.default.getValue(parseInt(Object.keys(a.datasets)[0]), e))) r = s.messages.PRIVACY_SHORT, o = S.default.current.properties.nodatacolor;
					else if (!0 === E.default.isNull(n)) {
						var c = S.default.current.properties.missing_data_label;
						r = s.properties.MISSING_DATA_LABELS[c].SHORT, o = S.default.current.properties.nodatacolor
					} else {
						var d;
						b.each(a.values, function (e, t) {
							if (d = !0, n === t.value) return 1 === t.show && (o = t.color, d = !1, r = a.datavalues.languages[s.ida]["|" + e]), !1
						}), !0 === d && (o = a.spec.fill_color_remainder, r = a.languages[s.ida].remainder)
					}
					i = a.languages[s.ida].name, l = {
						prefix: "fas",
						iconName: "square-full"
					};
					var p = O.findIconDefinition(l);
					u.alpha = 100, u.color = o, u.dataset = parseInt(Object.keys(a.datasets)[0]), t.datasets.push(u.dataset), u.fvalue = r, u.index = 0, u.label = i, u.symbol = p, u.type = "quali", u.value = n, t.data.push(u)
				}, t.prototype.getChoroInfo = function (e, t, a) {
					var o, n, r, i, l, s = T.default.localization;
					n = L.default.getValue(parseInt(Object.keys(t.datasets)[0]), e);
					var u = t.spec.colours.split(",").reverse(),
						c = t.spec.class_limits.split(",");
					if (!100 === n) r = s.messages.PRIVACY_SHORT, o = S.default.current.properties.nodatacolor;
					else if (!0 === E.default.isNull(n)) {
						var d = S.default.current.properties.missing_data_label;
						r = s.properties.MISSING_DATA_LABELS[d].SHORT, o = S.default.current.properties.nodatacolor
					} else {
						var p = !1,
							f = c.length + 1;
						b.each(c, function (e, t) {
							if (Number(n) < Number(t)) return p = !0, o = u[e], !1;
							--f
						}), !1 === p && (o = u[u.length - 1]), r = !1 === E.default.isUndefined(t.classlabels.languages) && !1 === E.default.isUndefined(t.classlabels.languages[s.ida][f]) ? t.classlabels.languages[s.ida][f].name : E.default.showValue(n, parseInt(t.spec.decimal_places))
					}
					i = t.languages[s.ida].name, l = {
						prefix: "fas",
						iconName: "square-full"
					};
					var h = O.findIconDefinition(l),
						m = new _.DataTable;
					m.alpha = 100, m.color = o, m.dataset = parseInt(Object.keys(t.datasets)[0]), a.datasets.push(m.dataset), m.fvalue = r, m.index = 0, m.label = i, m.symbol = h, m.type = "choro", m.value = n, a.data.push(m)
				}, t.prototype.getAttendantInfo = function (e) {}, t.prototype.setInfoValues = function () {
					var a = this,
						o = new e.InfoValueObject,
						n = S.default.current.visualizations,
						r = E.default.getVisOrder();
					Object.keys(r).map(function (e, t) {
						r[e].map(function (e, t) {
							f.isUndefined(n[e]) || a["get" + f.upperFirst(e) + "InfoValue"](n[e], o)
						})
					}), i.default.emitter.emit(m.INFO_VALUES_AVAILABLE), this.infoValues = o
				}, t.prototype.getInfoValues = function () {
					return this.infoValues
				}, t.prototype.getChoroInfoValue = function (e, t, a) {
					void 0 === a && (a = m.VISUALIZATION_CHORO);
					var o = parseInt(Object.keys(e.datasets)[0]),
						n = E.default.getInfoValue(o, 1 === e.info_without_pseudo),
						r = E.default.getInfoValueString(o, 1 === e.info_without_pseudo, e.spec.decimal_places),
						i = new h.InfoValueElement;
					i.infoLabel = E.default.getInfoLabel(parseInt(e.vis_data_info)), i.infoValue = n, i.infoValueString = r, i.dataset_ida = o, t.visualizations[a].push(i), t.infoLabels.push(i.infoLabel), t.datasetsOrdered.push({
						dataset_ida: o,
						visType: a
					})
				}, t.prototype.getQualiInfoValue = function (e, t) {
					var a = parseInt(Object.keys(e.datasets)[0]);
					t.datasetsOrdered.push({
						dataset_ida: a,
						visType: m.VISUALIZATION_QUALI
					})
				}, t.prototype.getSymInfoValue = function (e, t) {
					var a = parseInt(Object.keys(e.datasets)[0]),
						o = E.default.getInfoValue(a, 1 === e.info_without_pseudo),
						n = E.default.getInfoValueString(a, 1 === e.info_without_pseudo, e.spec.decimal_places),
						r = new h.InfoValueElement;
					r.infoLabel = E.default.getInfoLabel(parseInt(e.vis_data_info)), r.infoValue = o, r.infoValueString = n, r.dataset_ida = a, t.visualizations[m.VISUALIZATION_SYM].push(r), t.infoLabels.push(r.infoLabel), t.datasetsOrdered.push({
						dataset_ida: a,
						visType: m.VISUALIZATION_SYM
					}), void 0 !== e.fill && this.getChoroInfoValue(e.fill, t, m.VISUALIZATION_COMBINED)
				}, t.prototype.getSectInfoValue = function (i, l) {
					var s = this,
						u = !1,
						c = !1;
					E.default.createVirtualDataset0(i);
					var d = 0;
					if (d = 0 == Object.keys(i.datasets).length ? 0 : parseInt(Object.keys(i.datasets)[0]), 0 < Object.keys(i.datasets).length) {
						c = 1 === i.spec.is_related;
						var t = d,
							e = E.default.getInfoValue(t, 1 === i.info_without_pseudo),
							a = E.default.getInfoValueString(t, 1 === i.info_without_pseudo, i.spec.decimal_places),
							o = new h.InfoValueElement;
						o.dataset_ida = t, o.infoLabel = E.default.getInfoLabel(parseInt(i.vis_data_info)), o.infoValue = e, o.infoValueString = a, o.sector = !1, o.total = !0, o.percent = 100, l.visualizations[m.VISUALIZATION_SECT].push(o), l.infoLabels.push(o.infoLabel), 0 === l.datasetsOrdered.filter(function (e) {
							return e.dataset_ida === t && e.visType === m.VISUALIZATION_SECT
						}).length && l.datasetsOrdered.push({
							dataset_ida: t,
							visType: m.VISUALIZATION_SECT
						})
					}
					var p = null,
						f = 0;
					Object.keys(i.sectors).map(function (e, t) {
						var a = parseInt(i.sectors[e].dataset);
						f += E.default.getInfoValue(a, 1 === i.info_without_pseudo_sectors)
					}), Object.keys(i.sectors).map(function (e, t) {
						var a = parseInt(i.sectors[e].dataset),
							o = E.default.getInfoValue(a, 1 === i.info_without_pseudo_sectors),
							n = E.default.getInfoValueString(a, 1 === i.info_without_pseudo_sectors, i.spec.decimal_places_sectors),
							r = new h.InfoValueElement;
						r.dataset_ida = a, r.infoLabel = E.default.getInfoLabel(parseInt(i.vis_data_info_sectors)), r.infoValue = o, r.infoValueString = n, r.sector = !0, r.total = null, 0 !== a ? s.isNumericInfoValue(r.infoValue) ? (null === p && (p = 0), p += parseFloat(r.infoValue)) : s.isPrivacyValue(r.infoValue) && (u = !0) : (r.infoValue = E.default.getInfoValue(d, i.info_without_pseudo) - p, r.infoValueString = E.default.getInfoValueStringByValue(r.infoValue, 1 === i.info_without_pseudo_sectors, i.spec.decimal_places_sectors)), r.percent = u ? -1 : E.default.betterRounding((0 !== a ? E.default.getInfoValue(a, 1 === i.info_without_pseudo_sectors) : E.default.getInfoValue(d, 1 === i.info_without_pseudo) - p) / (!0 === c ? E.default.getInfoValue(d, 1 === i.info_without_pseudo) : f) * 100, i.spec.decimal_places_percent), l.visualizations[m.VISUALIZATION_SECT].push(r), l.infoLabels.push(r.infoLabel), 0 === l.datasetsOrdered.filter(function (e) {
							return e.dataset_ida === a && e.visType === m.VISUALIZATION_SECT
						}).length && l.datasetsOrdered.push({
							dataset_ida: a,
							visType: m.VISUALIZATION_SECT
						})
					}), u && (p = f = !1), n.bajoodooLog("ROWS", l)
				}, t.prototype.getBarInfoValue = function (i, l) {
					Object.keys(i.bars).map(function (e, t) {
						var a = i.bars[e].dataset,
							o = E.default.getInfoValue(a, 1 === i.info_without_pseudo),
							n = E.default.getInfoValueString(a, 1 === i.info_without_pseudo, i.spec.decimal_places),
							r = new h.InfoValueElement;
						r.infoLabel = E.default.getInfoLabel(parseInt(i.vis_data_info)), r.infoValue = o, r.infoValueString = n, r.dataset_ida = a, l.visualizations[m.VISUALIZATION_BAR].push(r), l.infoLabels.push(r.infoLabel), l.datasetsOrdered.push({
							dataset_ida: a,
							visType: m.VISUALIZATION_BAR
						})
					})
				}, t.prototype.getInfoLabel = function (e) {
					return E.default.getInfoLabel(e)
				}, t.prototype.getInfoValue = function (e, t) {
					return E.default.getInfoValue(e, t)
				}, t.prototype.getInfoValueString = function (e, t, a) {
					return E.default.getInfoValueString(e, t, a)
				}, t.prototype.getInfoValueStringByValue = function (e, t, a) {
					return E.default.getInfoValueStringByValue(e, t, a)
				}, t.prototype.isNumericInfoValue = function (e) {
					return !1 !== e && null !== e && !isNaN(parseFloat(e))
				}, t.prototype.isPrivacyValue = function (e) {
					return !1 === e
				}, t.addChoroRealValueUnit = function (e) {
					t.realValueUnits.push(e)
				}, t.clearChoroRealValueUnits = function () {
					t.realValueUnits = new Array
				}, t.isChoroRealValueUnit = function (e) {
					return -1 < t.realValueUnits.indexOf(e)
				}, t.INFO_TYPE_TOTAL = 1, t.INFO_TYPE_AVERAGE = 2, t.INFO_TYPE_REAL_GEOSYSTEM = 3, t.INFO_TYPE_GEOREF = 4, t.INFO_TYPE_ALL_CITIES = 5, t.INFO_TYPE_EU27 = 6, t.INFO_TYPE_PSEUDO_GEOSYSTEM = 7, t.INFO_TYPE_EU28 = 8, t.realValueUnits = [], t
			}();
			a.DataInfo = t
		}).call(this, o(31))
	},
	754: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = function () {
			this.total = !1, this.sector = !1, this.percent = -1, this.missingPercent = "", this.hasPrivacy = !1
		};
		t.InfoValueElement = o
	},
	755: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = function () {
			function e() {
				this.visualizations = {
					bar: [],
					choro: [],
					combined: [],
					quali: [],
					sect: [],
					sym: []
				}, this.infoLabels = [], this.datasetsOrdered = []
			}
			return e.prototype.getVisualizationDatasetElement = function (e, t) {
				var a = this.visualizations[e].filter(function (e) {
					return e.dataset_ida === t
				});
				return a.length ? a[0] : null
			}, e
		}();
		t.InfoValueObject = o
	},
	756: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = function () {};
		t.DataTable = o
	},
	757: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = function (e) {
			for (var t in e) this[t] = e[t]
		};
		t.VectorSymbol = o
	},
	758: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = function (e) {
			for (var t in this.length = null, e) this[t] = e[t]
		};
		t.GeometryLabel = o
	},
	759: function (e, r, f) {
		"use strict";
		(function (i) {
			var t = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
			Object.defineProperty(r, "__esModule", {
				value: !0
			});
			var a = f(18),
				o = f(39),
				l = f(17),
				s = f(19),
				u = f(20),
				n = f(25),
				c = f(30),
				d = f(760),
				p = f(10),
				e = function () {
					function e() {
						this.geometries = [], this.regionTooltip = !0, this.client = o.default.create({
							baseURL: _WWW,
							responseType: "document"
						})
					}
					return e.prototype.has = function (e) {
						return c.bajoodooLog("GeometryStore.has(" + e + ")"), this.geometries["_" + e] instanceof d.Geometry ? 1 != this.geometries["_" + e].isLoading : (this.load(e), !1)
					}, e.prototype.get = function (e) {
						if (c.bajoodooLog("GeometryStore.get(" + e + ")"), this.geometries["_" + e] instanceof d.Geometry && 1 != this.geometries["_" + e].isLoading) return this.geometries["_" + e];
						throw c.bajoodooLog("GeometryStore.get(" + e + ") throws error"), new Error("geometry not available")
					}, e.prototype.getOrNull = function (e) {
						return this.geometries["_" + e] instanceof d.Geometry ? 1 != this.geometries["_" + e].isLoading ? this.geometries["_" + e] : void 0 : null
					}, e.prototype.load = function (o) {
						var n = this;
						if (this.geometries["_" + o] instanceof d.Geometry && 1 == this.geometries["_" + o].isLoading) c.bajoodooLog("GeometryStore.load(" + o + ") - already loading!!!");
						else {
							c.bajoodooLog("GeometryStore.load(" + o + ")");
							var e = l.default.localization,
								t = {
									ida: o,
									svg: "",
									isLoading: !0
								};
							this.geometries["_" + o] = new d.Geometry(t);
							var r = {
								id: o.toString(),
								type: a.LOADING_TYPE_GEOMETRY,
								message: e.messages.LOADING_GEOMETRY + [" (", o, ")"].join("")
							};
							p.default.setIsLoading(r), this.client.get(p.default.paths._JSON + s.default.projectIda + "/geometries/" + o + ".svg", {
								onDownloadProgress: function (e) {
									c.bajoodooLog("progress", e.srcElement.getResponseHeader("Content-Type"), e.loaded, e.total), r.percent = Math.round(100 * e.loaded / e.total), p.default.setLoadingPercent(r)
								}
							}).then(function (e) {
								e.data.getElementsByTagName("image")[0].setAttribute("xlink:href","https://www.atlas.bfs.admin.ch/img/xshared/relief/1/0.jpg");
								var t = i(i(e.data)[0].childNodes)[1].outerHTML || (new XMLSerializer).serializeToString(i(i(e.data)[0].childNodes)[1]),
									a = {
										ida: o,
										svg: t,
										isLoading: !1
									};
								n.geometries["_" + o] = new d.Geometry(a), u.default.geometryLoaded(), p.default.setNotLoading(r)
							}).catch(function (e) {
								c.bajoodooLog(e)
							})
						}
					}, Object.defineProperty(e.prototype, "toolTip", {
						set: function (e) {
							this.regionTooltip = e
						},
						enumerable: !0,
						configurable: !0
					}), Object.defineProperty(e.prototype, "showToolTip", {
						get: function () {
							return this.regionTooltip
						},
						enumerable: !0,
						configurable: !0
					}), t([n.observable], e.prototype, "geometries", void 0), t([n.observable], e.prototype, "regionTooltip", void 0), t([n.observable], e.prototype, "currentRollOver", void 0), t([n.action], e.prototype, "load", null), t([n.action], e.prototype, "toolTip", null), t([n.computed], e.prototype, "showToolTip", null), e
				}();
			r.default = new e
		}).call(this, f(31))
	},
	760: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = function (e) {
			for (var t in e) this[t] = e[t]
		};
		t.Geometry = o
	},
	761: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = function (e) {
			for (var t in e) this[t] = e[t]
		};
		t.Topography = o
	},
	762: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = a(17),
			n = a(19),
			r = a(10),
			i = function () {
				function e(e) {
					for (var t in this.KEYDATA_MODE_PREFIX = "90000", e) this[t] = e[t];
					this.isKeydataMap = 0 === this.ida.toString().indexOf(this.KEYDATA_MODE_PREFIX)
				}
				return e.prototype.getCommonMapInfo = function (e) {
					return {
						mapId: this.ida,
						title: this.languages[e].name,
						online: this.languages[e].firstact,
						lastMod: this.languages[e].lastmod,
						source: this.languages[e].merged_datasource
					}
				}, e.prototype.getXLSX = function () {
					var e = o.default.localization;
					if (r.default.vars.isPreview) var t = _WWW + "preview/media/" + n.default.getProject().project + "/xshared/xlsx/" + this.ida + "_" + e.realLocId + ".xlsx";
					else t = _WWW + "core/projects/" + n.default.getProject().project + "/xshared/xlsx/" + this.ida + "_" + e.realLocId + ".xlsx";
					return t
				}, e.prototype.getPDF = function () {
					var e = o.default.localization;
					if (r.default.vars.isPreview) var t = _WWW + "preview/media/" + n.default.getProject().project + "/xshared/pdf/" + this.ida + "_" + e.realLocId + ".pdf";
					else t = _WWW + "core/projects/" + n.default.getProject().project + "/xshared/pdf/" + this.ida + "_" + e.realLocId + ".pdf";
					return t
				}, e.prototype.hasXLSX = function () {
					var e = o.default.localization.ida;
					return (!1 !== this.languages[e].xlsx || r.default.vars.isPreview) && (0 === this.properties.is_live_map || this.properties.is_live_active)
				}, e.prototype.hasPDF = function () {
					var e = o.default.localization.ida;
					return (!1 !== this.languages[e].pdf || r.default.vars.isPreview) && (0 === this.properties.is_live_map || this.properties.is_live_active)
				}, e.prototype.hasDownloadDocs = function () {
					var e = o.default.localization;
					return 0 < this.languages[e.ida].downloads.length
				}, e.prototype.getDownloadDocs = function () {
					var e = o.default.localization;
					return this.hasDownloadDocs() ? this.languages[e.ida].downloads : []
				}, e.prototype.getCurrentMapSpecial = function () {
					var e = o.default.localization.ida;
					return this.languages[e].special
				}, e.prototype.getCurrentMapTitle = function () {
					var e = o.default.localization.ida;
					return void 0 !== this.languages[e] ? this.languages[e].name : ""
				}, e.prototype.currentLanguage = function () {
					var e = o.default.localization.ida;
					return this.languages[e]
				}, e.prototype.getDatasetIds = function () {
					return this.getDatasets()
				}, e.prototype.getDatasets = function () {
					return this.properties.datasets
				}, e.prototype.hasVisualization = function (e) {
					return "combined" == (e = e.toLowerCase()) ? null != this.visualizations.sym.fill && "choro" == this.visualizations.sym.fill.type : null != this.visualizations[e]
				}, e.prototype.getVisualization = function (e) {
					if (e = e.toLowerCase(), this.hasVisualization(e)) return "combined" == e ? this.visualizations.sym.fill : this.visualizations[e];
					var t = o.default.localization;
					throw new Error(t.errors.VISUALIZATION_NOT_AVAILABLE)
				}, e.prototype.hasProperties = function () {
					return null !== this.properties
				}, e
			}();
		t.ThematicalMap = i
	},
	763: function (e, t, a) {
		"use strict";
		(function (F) {
			Object.defineProperty(t, "__esModule", {
				value: !0
			});
			var U = a(10),
				W = a(48),
				H = a(84),
				b = a(17),
				G = a(20),
				i = a(148),
				Y = a(30);
			F.fn.exists = function () {
				return 0 < this.length
			};
			var e = function () {
				function e() {
					this.fallBackCenterId = 5, this.barBaseWidth = 1
				}
				return e.prototype.update = function () {
					Y.bajoodooLog("update ThematicalMapVisualization"), this.arealOpacity = H.default.arealOpacityRealValue / 255, this.setupNoDataAndPrivacy()
				}, e.prototype.getNoDataAndPrivacy = function () {
					return Y.bajoodooLog("ThematicalMapVisualization.getNoDataAndPrivacy()"), this.noDataAndPrivacy
				}, e.prototype.setupNoDataAndPrivacy = function () {
					this.noDataAndPrivacy = {
						choro: {
							noData: !1,
							privacy: !1
						},
						quali: {
							noData: !1,
							privacy: !1,
							remainder: !1
						},
						sym: {
							noData: !1,
							privacy: !1,
							suppressed: !1,
							scaledUp: !1,
							scaledDown: !1,
							combined: {
								noData: !1,
								privacy: !1
							}
						},
						sect: {
							noData: !1,
							privacy: !1,
							suppressed: !1,
							scaledUp: !1,
							scaledDown: !1
						},
						bar: {
							noData: !1,
							privacy: !1,
							hasPositiveValue: !1,
							hasNegativeValue: !1,
							minValue: 0,
							maxValue: 0
						}
					}
				}, e.prototype.resetNoDataAndPrivacy = function (e) {
					void 0 === e && (e = !1), Y.bajoodooLog("ThematicalMapVisualization.resetNoDataAndPrivacy()"), !1 === e && (this.noDataAndPrivacy.choro = {
						noData: !1,
						privacy: !1
					}, this.noDataAndPrivacy.quali = {
						noData: !1,
						privacy: !1,
						remainder: !1
					}), this.noDataAndPrivacy.sect = {
						noData: !1,
						privacy: !1,
						suppressed: !1,
						scaledUp: !1,
						scaledDown: !1
					}, this.noDataAndPrivacy.bar = {
						noData: !1,
						privacy: !1,
						hasPositiveValue: !1,
						hasNegativeValue: !1,
						minValue: 0,
						maxValue: 0
					}, this.noDataAndPrivacy.sym = {
						noData: !1,
						privacy: !1,
						suppressed: !1,
						scaledUp: !1,
						scaledDown: !1,
						combined: {
							noData: !1,
							privacy: !1
						}
					}
				}, e.prototype.setNoDataAndPrivacy = function (e, t) {
					Y.bajoodooLog("ThematicalMapVisualization.setNoDataAndPrivacy('" + e + "', " + t + ")"), this.noDataAndPrivacy[e] = t
				}, e.prototype.redrawSymbols = function () {
					Y.bajoodooLog("ThematicalMapVisualization.redrawSymbols()"), this.clearVisualization(!0), this.resetNoDataAndPrivacy(!0);
					var a = this,
						o = G.default.current.visualizations;
					F.each(H.default.getVisOrder(), function (e, t) {
						"symbol" === e && F.each(t, function (e, t) {
							!1 === H.default.isUndefined(o[t]) && a.setNoDataAndPrivacy(t, a["show" + H.default.ucFirst(t)](o[t]))
						})
					}), Y.bajoodooLog("SYMBOLS REDRAW DONE!")
				}, e.prototype.clearVisualization = function (e) {
					if (void 0 === e && (e = !0), F("#symbolContainer").children().each(function () {
							F(this).remove()
						}), !1 === e) {
						var t = G.default.current.geometry,
							a = F("#geometry_" + G.default.current.geometry);
						F.each(a.find("[id^=geo_" + t + "_]"), function () {
							F(this).attr("fill", "#cccccc").attr("fill-opacity", 0)
						})
					}
					return !0
				}, e.prototype.showVisualization = function () {
					Y.bajoodooLog("ThematicalMapVisualization.showVisualization()"), this.clearVisualization(!1), this.resetNoDataAndPrivacy(!1);
					var o = this,
						n = G.default.current.visualizations,
						e = G.default.current,
						r = {
							areal: !1,
							symbol: !1
						};
					for (var t in (0 === e.properties.is_live_map || 1 === e.properties.is_live_map && 1 === e.properties.is_live_active) && F.each(H.default.getVisOrder(), function (a, e) {
							F.each(e, function (e, t) {
								!1 === H.default.isUndefined(n[t]) && (r[a] = !0, o.setNoDataAndPrivacy(t, o["show" + H.default.ucFirst(t)](n[t])))
							})
						}), r) U.default["has" + H.default.ucFirst(t)] = r[t];
					Y.bajoodooLog("VISUALIZATION DONE!")
				}, e.prototype.showBar = function (e) {
					Y.bajoodooLog("ThematicalMapVisualization.showBar()");
					var E = this,
						n = (b.default.localization, !1),
						r = !1,
						S = 1 === e.spec.is_bar,
						L = 1 === e.spec.is_stacked,
						O = parseInt(e.spec.bar_width),
						I = parseInt(e.spec.gap_width),
						A = parseInt(e.spec.ln_thickness),
						w = parseInt(e.spec.fill_transparency),
						C = parseInt(e.spec.ln_transparency),
						P = e.spec.ln_color,
						N = 1 === e.spec.show_ln,
						M = Number(e.spec.ratio),
						o = parseInt(e.spec.center_method),
						i = (parseInt(e.spec.decimal_places), e.vis_data_info, new Array),
						l = new Array,
						s = new Array,
						D = new Array,
						u = 0,
						c = 0;
					F.each(e.bars, function (e, t) {
						i.push(t.dataset);
						var a = W.default.get(t.dataset);
						l.push(a), s.push(a.info), D.push(t.color), a.info.max > c && (c = a.info.max), a.info.min < u && (u = a.info.min)
					});
					var t = 0 < c,
						a = u < 0;
					!1 === N && (A = 0);
					var R, k, d = null,
						p = new Object,
						f = new Object,
						h = new Object,
						j = 0,
						x = 0;
					F.each(l[0].values, function (e, t) {
						var a = e.substr(1);
						t.pseudoId || (d = F("#center_" + o + "_" + a).exists() ? o : E.fallBackCenterId, p[e] = Number(F("#center_" + d + "_" + a).attr("cx")), f[e] = Number(F("#center_" + d + "_" + a).attr("cy")))
					});
					var m = new Array;
					for (var g in p) m.push([g, p[g], f[g]]);
					S ? t ? m.sort(function (e, t) {
						return e[1] - t[1]
					}) : m.sort(function (e, t) {
						return t[1] - e[1]
					}) : t ? m.sort(function (e, t) {
						return t[2] - e[2]
					}) : m.sort(function (e, t) {
						return e[2] - t[2]
					});
					var V = new Object;
					for (var y in m) V[m[y][0]] = {
						posX: m[y][1],
						posY: m[y][2]
					};
					F.each(V, function (o, e) {
						F.each(i, function (e, t) {
							var a = l[e].values[o].value;
							H.default.isUndefined(h[o]) && (h[o] = new Object, h[o].hasValue = !1), !1 === a || H.default.isNull(a) ? (!1 === a && (r = !0), H.default.isNull(a) && (n = !0)) : h[o].hasValue = !0, h[o][t] = a
						})
					});
					var v = !0 === L ? 1 : i.length,
						B = 0;
					return B += v * O, B += (v - 1) * I, B += 2 * v * A, B += 2 * this.barBaseWidth, B = H.default.getRadiusForCurrentScale(H.default.scaleSymbol(B, !0)), F.each(h, function (e, t) {
						if (!0 === t.hasValue) {
							var a = e.substr(1),
								o = "translate(" + V[e].posX + "," + V[e].posY + ")";
							S && (o += " rotate(-90)"), F(document.createElementNS("http://www.w3.org/2000/svg", "g")).attr({
								id: "_" + a + "_group",
								style: "pointer-events: none;",
								transform: o
							}).appendTo(F("#symbolContainer"));
							var n, r, i, l, s, u = B / -2,
								c = B / 2,
								d = u,
								p = 0 - H.default.getRadiusForCurrentScale(5),
								f = u,
								h = 0 + H.default.getRadiusForCurrentScale(5),
								m = c,
								g = p,
								y = c,
								v = h,
								b = 0,
								_ = 0,
								T = 0;
							R = k = 0, F.each(t, function (e, t) {
								if ("hasValue" !== e) {
									if (!1 === H.default.isNull(t) && !1 !== t) {
										if (F(document.createElementNS("http://www.w3.org/2000/svg", "rect")).attr({
												id: "symbol_" + a + "_bar_" + e,
												style: "pointer-events: none;"
											}).appendTo(F("#_" + a + "_group")), F("#symbol_" + a + "_bar_" + e).attr("fill", "#" + D[b]).attr("fill-opacity", w / 100), i = H.default.getRadiusForCurrentScale(H.default.scaleSymbol(O, !0)), l = H.default.getRadiusForCurrentScale(H.default.scaleSymbol(t / M, !0)), s = H.default.getRadiusForCurrentScale(H.default.scaleSymbol(A, !0)), !0 === L)
											if (0 < t ? k += Number(t) : t < 0 && (R += Number(t)), n = B / -2 + H.default.getRadiusForCurrentScale(H.default.scaleSymbol(E.barBaseWidth + A, !0)), 0 < l) r = T, T += l;
											else {
												if (!(l < 0)) return b++, !0;
												r = _, _ += l
											}
										else x < t ? x = Number(t) : t < j && (j = Number(t)), n = B / -2 + H.default.getRadiusForCurrentScale(H.default.scaleSymbol(E.barBaseWidth + O * b + I * b + 2 * A * b + A, !0)), r = 0;
										F("#symbol_" + a + "_bar_" + e).attr("x", n).attr("width", i), F("#symbol_" + a + "_bar_" + e).attr("y", l < 0 ? r + l : r).attr("height", Math.abs(l)), N && !1 === L && F(document.createElementNS("http://www.w3.org/2000/svg", "polyline")).attr({
											id: "symbol_" + a + "_bar_" + e + "_line",
											points: n - s / 2 + "," + r + " " + (n - s / 2) + "," + (r + l + s / (l < 0 ? -2 : 2)) + " " + (n + i + s / 2) + "," + (r + l + s / (l < 0 ? -2 : 2)) + " " + (n + i + s / 2) + "," + r,
											stroke: "#" + P
										}).attr("fill", "none").attr("stroke-width", s).attr("stroke-opacity", C / 100).attr("stroke-linejoin", "miter").appendTo(F("#_" + a + "_group"))
									}
									b++
								}
							}), !0 === L && (x < k && (x = k), R < j && (j = R)), !1 !== L && 0 === T && 0 === _ || (N && !0 === L && (0 !== T && F(document.createElementNS("http://www.w3.org/2000/svg", "polyline")).attr({
								id: "symbol_" + a + "_bar_stacked_line_positive",
								points: n - s / 2 + ",0 " + (n - s / 2) + "," + (T + s / 2) + " " + (n + i + s / 2) + "," + (T + s / 2) + " " + (n + i + s / 2) + ",0",
								stroke: "#" + P
							}).attr("fill", "none").attr("stroke-width", s).attr("stroke-opacity", C / 100).attr("stroke-linejoin", "miter").appendTo(F("#_" + a + "_group")), 0 !== _ && F(document.createElementNS("http://www.w3.org/2000/svg", "polyline")).attr({
								id: "symbol_" + a + "_bar_stacked_line_negative",
								points: n - s / 2 + ",0 " + (n - s / 2) + "," + (_ + s / -2) + " " + (n + i + s / 2) + "," + (_ + s / -2) + " " + (n + i + s / 2) + ",0",
								stroke: "#" + P
							}).attr("fill", "none").attr("stroke-width", s).attr("stroke-opacity", C / 100).attr("stroke-linejoin", "miter").appendTo(F("#_" + a + "_group"))), F(document.createElementNS("http://www.w3.org/2000/svg", "line")).attr({
								id: "symbol_" + a + "_bar_line_1",
								x1: u,
								y1: 0,
								x2: c,
								y2: 0,
								stroke: "#FFFFFF"
							}).appendTo(F("#_" + a + "_group")), F("#symbol_" + a + "_bar_line_1").attr("stroke", "#FFFFFF").attr("vector-effect", "non-scaling-stroke").attr("stroke-width", 3).attr("stroke-opacity", .65).attr("stroke-linejoin", "miter"), F(document.createElementNS("http://www.w3.org/2000/svg", "line")).attr({
								id: "symbol_" + a + "_bar_line_2",
								x1: u,
								y1: 0,
								x2: c,
								y2: 0,
								stroke: "#000000"
							}).appendTo(F("#_" + a + "_group")), F(document.createElementNS("http://www.w3.org/2000/svg", "line")).attr({
								id: "symbol_" + a + "_bar_line_3",
								x1: d,
								y1: p,
								x2: f,
								y2: h,
								stroke: "#000000"
							}).appendTo(F("#_" + a + "_group")), F(document.createElementNS("http://www.w3.org/2000/svg", "line")).attr({
								id: "symbol_" + a + "_bar_line_4",
								x1: m,
								y1: g,
								x2: y,
								y2: v,
								stroke: "#000000"
							}).appendTo(F("#_" + a + "_group")), F("#symbol_" + a + "_bar_line_2").attr("stroke", "#000000").attr("vector-effect", "non-scaling-stroke").attr("stroke-width", 1).attr("stroke-opacity", 1).attr("stroke-linejoin", "miter"), F("#symbol_" + a + "_bar_line_3").attr("stroke", "#000000").attr("vector-effect", "non-scaling-stroke").attr("stroke-width", 1).attr("stroke-opacity", 1).attr("stroke-linejoin", "miter"), F("#symbol_" + a + "_bar_line_4").attr("stroke", "#000000").attr("vector-effect", "non-scaling-stroke").attr("stroke-width", 1).attr("stroke-opacity", 1).attr("stroke-linejoin", "miter"))
						}
					}), {
						noData: n,
						privacy: r,
						hasPositiveValue: t,
						hasNegativeValue: a,
						minValue: j,
						maxValue: x
					}
				}, e.prototype.showSect = function (e) {
					Y.bajoodooLog("ThematicalMapVisualization.showSect()");
					var t, a, m = !1,
						g = !1,
						o = !1,
						n = !1,
						r = !1,
						i = parseInt(e.spec.ln_thickness),
						y = parseInt(e.spec.fill_transparency),
						v = parseInt(e.spec.ln_transparency),
						b = e.spec.ln_color,
						_ = 1 === e.spec.ln_inbetween,
						l = Number(e.spec.ratio),
						T = parseInt(e.spec.center_method),
						s = e.spec.symbol_values.split(","),
						E = 1 === e.spec.bias_min,
						S = 1 === e.spec.bias_max,
						L = e.spec.suppression,
						u = 1 === e.spec.dynamic_suppression;
					if (H.default.createVirtualDataset0(e), 0 < F(e.datasets).length) {
						t = Object.keys(e.datasets)[0];
						var c = W.default.get(t);
						c.info.crosses_0, c.info.hasNegative;
						a = 1 === e.spec.is_related
					} else t = 0, a = !1;
					var O = e.sectors;
					if (!0 === a && 0 != O[F(O).jsonObjCount()].dataset) {
						var d = F(O).jsonObjCount() - -1;
						O[d] = {
							dataset: 0,
							color: e.spec.fill_color_remainder,
							languages: {}
						}, F.each(e.languages, function (e, t) {
							O[d].languages[e] = {}, O[d].languages[e].name = t.remainder
						})
					}
					if (!0 === E) var I = Number(s[0]);
					if (!0 === S) var A = Number(s[s.length - 1]);
					0 === i && (i = 1);
					var w = L;
					!1 !== L && !0 === u && (w /= U.default.actualZoomFactor / 100);
					var C = null,
						p = W.default.get(t);
					if (!1 === a) var P = W.default.get(0);
					var N = [];
					return F.each(O, function (e, t) {
						N[e - 1] = W.default.get(t.dataset)
					}), F.each(p.values, function (s, e) {
						var u = s.substr(1);
						if (!1 === e.pseudoId)
							if (!1 === e.value || H.default.isNull(e.value)) !1 === e.value && (g = !0), H.default.isNull(e.value) && (m = !0);
							else {
								C = F("#center_" + T + "_" + u).exists() ? T : this.fallBackCenterId;
								var c = Number(F("#center_" + C + "_" + u).attr("cx")),
									d = Number(F("#center_" + C + "_" + u).attr("cy")),
									t = e.value;
								if (!0 === a) var p = t;
								else p = P.values[s].value;
								if (0 != L && Math.abs(t) < w) return o = !0;
								!0 === E && Math.abs(t) < I ? (n = !0, t = I * (t < 0 ? -1 : 1)) : !0 === S && Math.abs(t) > A && (r = !0, t = A * (t < 0 ? -1 : 1));
								var f = H.default.getRadius(t, 1, l, !0);
								F("#clip_" + u + "_container").remove(), F(document.createElementNS("http://www.w3.org/2000/svg", "clipPath")).attr({
									id: "clip_" + u + "_container"
								}).appendTo(F("#geometry_" + G.default.current.geometry + ">defs")), F(document.createElementNS("http://www.w3.org/2000/svg", "circle")).attr({
									id: "clip_" + u,
									style: "pointer-events: none;"
								}).appendTo(F("#clip_" + u + "_container")), F("#clip_" + u).attr("cx", c), F("#clip_" + u).attr("cy", d), F("#clip_" + u).attr("r", f), F(document.createElementNS("http://www.w3.org/2000/svg", "g")).attr({
									id: "_" + u + "_group",
									style: "pointer-events: none;"
								}).prependTo(F("#symbolContainer")), F(document.createElementNS("http://www.w3.org/2000/svg", "g")).attr({
									id: "__" + u + "_group",
									style: "pointer-events: none;"
								}).attr("clip-path", "url(#clip_" + u + "_container)").prependTo(F("#_" + u + "_group")), F(document.createElementNS("http://www.w3.org/2000/svg", "circle")).attr({
									id: "symbol_" + u,
									style: "pointer-events: none;"
								}).attr("stroke", "#" + b).attr("stroke-width", H.default.scaleSymbol(i) * (100 / U.default.actualZoomFactor)).attr("stroke-opacity", v / 100).attr("stroke-linejoin", "miter").attr("fill-opacity", 0).attr("cx", c).attr("cy", d).attr("r", f).prependTo(F("#_" + u + "_group"));
								var h = 0;
								F.each(O, function (e, t) {
									var a = N[e - 1].values[s].value;
									if (!1 === a) return g = !0, F('polyline[id^="sector_' + u + '_"]').each(function () {
										F(this).remove()
									}), F(document.createElementNS("http://www.w3.org/2000/svg", "circle")).attr({
										id: "sector_" + u + "_" + e,
										points: n,
										stroke: "#" + b
									}).attr("cx", c).attr("cy", d).attr("r", f).attr("clip-path", "url(#clip_" + u + "_container)").attr("fill", "#" + G.default.current.properties.nodatacolor).attr("fill-opacity", y / 100).prependTo(F("#__" + u + "_group")), !1;
									if (!1 === H.default.isNull(a)) {
										var o = a / p * 360,
											n = c + "," + d + " ";
										n += Math.sin(H.default.deg2rad(h)) * f - -c + "," + (Math.cos(H.default.deg2rad(h)) * f - -d) + " ";
										for (var r = null, i = null, l = 0; l < H.default.sectorHelperDegrees.length; l++)
											if (H.default.sectorHelperDegrees[l] <= h && (r = l), H.default.isNull(i) && H.default.sectorHelperDegrees[l] >= h - -o) {
												i = l;
												break
											}
										H.default.isNull(i) && (i = H.default.sectorHelperDegrees.length - 1);
										for (l = r; l <= i; l++) n += Math.sin(H.default.deg2rad(H.default.sectorHelperDegrees[l])) * f * 2 - -c + "," + (Math.cos(H.default.deg2rad(H.default.sectorHelperDegrees[l])) * f * 2 - -d) + " ";
										h += o, n += Math.sin(H.default.deg2rad(h)) * f - -c + "," + (Math.cos(H.default.deg2rad(h)) * f - -d) + " ", n += c + "," + d, F(document.createElementNS("http://www.w3.org/2000/svg", "polyline")).attr({
											id: "sector_" + u + "_" + e,
											points: n,
											stroke: "#" + b
										}).attr("clip-path", "url(#clip_" + u + "_container)").attr("fill", "#" + t.color).attr("fill-opacity", y / 100).attr("stroke-width", H.default.scaleSymbol(.5) * (100 / U.default.actualZoomFactor)).attr("stroke-opacity", !0 === _ ? v / 100 : 0).attr("stroke-linejoin", "miter").prependTo(F("#_" + u + "_group"))
									} else m = !0
								})
							}
					}), {
						noData: m,
						privacy: g,
						suppressed: o,
						scaledUp: n,
						scaledDown: r
					}
				}, e.prototype.showSym = function (p) {
					Y.bajoodooLog("ThematicalMapVisualization.showSym()");
					var f = this,
						h = !1,
						m = !1,
						g = !1,
						y = !1,
						v = !1,
						b = !1,
						_ = !1,
						e = Number(Object.keys(p.datasets)[0]),
						T = p.spec.syms;
					if (!1 === H.default.isUndefined(p.spec.vectorSymbol)) {
						var t = Number(p.spec.vectorSymbol);
						if (4 === T) var E = i.default.get(t)
					} else t = 1;
					var S = parseInt(p.spec.ln_thickness),
						L = parseInt(p.spec.fill_transparency),
						O = parseInt(p.spec.ln_transparency),
						I = p.spec.fill_color,
						A = p.spec.ln_color,
						w = p.spec.fill_color_negative,
						C = p.spec.ln_color_negative,
						P = Number(p.spec.ratio),
						N = parseInt(p.spec.center_method),
						M = parseInt(p.spec.rotation),
						a = p.spec.symbol_values.split(","),
						D = 1 === p.spec.bias_min,
						R = 1 === p.spec.bias_max,
						k = p.spec.suppression,
						o = 1 === p.spec.dynamic_suppression;
					if (!0 === D) var j = Number(a[0]);
					if (!0 === R) var x = Number(a[a.length - 1]);
					0 === S && (S = 1);
					var V = k;
					!1 !== k && !0 === o && (V /= U.default.actualZoomFactor / 100);
					var B = null,
						n = W.default.get(e);
					if (void 0 !== p.fill) var r = Number(Object.keys(p.fill.datasets)[0]),
						z = W.default.get(r);
					return F.each(n.values, function (e, t) {
						if (e = e.substr(1), !1 === t.pseudoId)
							if (!1 === t.value || H.default.isNull(t.value)) !1 === t.value && (m = !0), H.default.isNull(t.value) && (h = !0);
							else {
								B = F("#center_" + N + "_" + e).exists() ? N : f.fallBackCenterId;
								var a = Number(F("#center_" + B + "_" + e).attr("cx")),
									o = Number(F("#center_" + B + "_" + e).attr("cy")),
									n = Number(t.value);
								if (!1 !== k && Math.abs(n) < V) return v = !0;
								!0 === D && Math.abs(n) < j ? (b = !0, n = j * (n < 0 ? -1 : 1)) : !0 === R && Math.abs(n) > x && (_ = !0, n = x * (n < 0 ? -1 : 1));
								var r = null,
									i = H.default.getRadius(n, T, P, !0);
								1 === T ? r = "circle" : 2 === T ? r = "rect" : 3 === T ? r = "polygon" : 4 === T && (r = "path");
								var l = "symbolContainer";
								if (F(document.createElementNS("http://www.w3.org/2000/svg", "g")).attr({
										id: "_" + e + "_group",
										style: "pointer-events: none;"
									}).prependTo(F("#" + l)), l = "_" + e + "_group", "path" === r) {
									var s = E.size * i / 2;
									F("#" + l).attr({
										transform: "translate(" + (a - s) + "," + (o + s) + ")"
									})
								}
								if (F(document.createElementNS("http://www.w3.org/2000/svg", r)).attr({
										id: "symbol_" + e,
										style: "pointer-events: none;"
									}).prependTo(F("#" + l)), 0 <= n ? F("#symbol_" + e).attr("stroke", "#" + A) : F("#symbol_" + e).attr("stroke", "#" + C), void 0 === p.fill) 0 <= n ? F("#symbol_" + e).attr("fill", "#" + I) : F("#symbol_" + e).attr("fill", "#" + w);
								else {
									var u = p.fill.spec.colours.split(",").reverse(),
										c = p.fill.spec.class_limits.split(",");
									F.each(c, function (e, t) {
										c[e] = Number(t)
									}), F.each(z.values, function (e, a) {
										if (e = e.substr(1), !1 === a.pseudoId)
											if (!1 === a.value || H.default.isNull(a.value)) !1 === a.value && (y = !0), H.default.isNull(a.value) && (g = !0), F("#symbol_" + e).attr("fill", "#" + G.default.current.properties.nodatacolor);
											else {
												var o = !1,
													n = "000000";
												F.each(c, function (e, t) {
													if (Number(a.value) < t) return n = u[e], !(o = !0)
												}), !1 === o && (n = u[u.length - 1]), F("#symbol_" + e).attr("fill", "#" + n)
											}
									})
								}
								F("#symbol_" + e).attr("stroke-width", H.default.scaleSymbol(S) * (100 / U.default.actualZoomFactor)), F("#symbol_" + e).attr("stroke-opacity", O / 100), F("#symbol_" + e).attr("stroke-linejoin", "miter"), F("#symbol_" + e).attr("fill-opacity", L / 100), 1 === T ? (F("#symbol_" + e).attr("cx", a), F("#symbol_" + e).attr("cy", o), F("#symbol_" + e).attr("r", i)) : 2 === T ? (F("#symbol_" + e).attr("x", a - i), F("#symbol_" + e).attr("y", o - i), F("#symbol_" + e).attr("width", 2 * i), F("#symbol_" + e).attr("height", 2 * i)) : 3 === T ? F("#symbol_" + e).attr("points", a + "," + (o + i) + " " + (a + Math.sin(H.default.deg2rad(120)) * i) + "," + (o + Math.cos(H.default.deg2rad(120)) * i) + " " + (a - Math.sin(H.default.deg2rad(120)) * i) + "," + (o + Math.cos(H.default.deg2rad(120)) * i)) : 4 === T && (F("#symbol_" + e).attr("stroke-width", H.default.scaleSymbol(S / i) * (100 / U.default.actualZoomFactor)), F("#symbol_" + e).attr({
									d: E.path,
									transform: "scale(" + i + "," + -i + ")"
								}));
								var d = F("#symbol" + ("path" === r ? "Container" : "") + "_" + e);
								"path" === r ? d.attr("transform", d.attr("transform") + " rotate(" + -M + "," + i * E.size / 2 + "," + i * E.size / -2 + ")") : d.attr("transform", "rotate(" + -M + "," + a + "," + o + ")")
							}
					}), {
						noData: h,
						privacy: m,
						suppressed: v,
						scaledUp: b,
						scaledDown: _,
						combined: {
							noData: g,
							privacy: y
						}
					}
				}, e.prototype.showQuali = function (t) {
					Y.bajoodooLog("ThematicalMapVisualization.showQuali()");
					var r = this,
						i = !1,
						l = !1,
						s = !1,
						e = Number(Object.keys(t.datasets)[0]),
						u = t.values;
					return F.each(W.default.get(e).values, function (e, a) {
						if (e = e.substr(1), !1 === a.pseudoId)
							if (F("#geo_" + G.default.current.geometry + "_" + e).attr("fill-opacity", U.default.isArealOn ? r.arealOpacity : 0), !1 === a.value || H.default.isNull(a.value)) !1 === a.value && (l = !0), H.default.isNull(a.value) && (i = !0), F("#geo_" + G.default.current.geometry + "_" + e).attr("fill", "#" + G.default.current.properties.nodatacolor);
							else {
								var o = !1,
									n = "000000";
								F.each(u, function (e, t) {
									if (a.value === t.value) return 1 === t.show ? (n = t.color, o = !0) : s = !0, !1
								}), !1 === o && (n = t.spec.fill_color_remainder), F("#geo_" + G.default.current.geometry + "_" + e).attr("fill", "#" + n)
							}
					}), {
						noData: i,
						privacy: l,
						remainder: s
					}
				}, e.prototype.showChoro = function (e) {
					Y.bajoodooLog("ThematicalMapVisualization.showChoro()");
					var t = this,
						r = !1,
						i = !1,
						a = Number(Object.keys(e.datasets)[0]),
						l = e.spec.colours.split(",").reverse(),
						s = e.spec.class_limits.split(",");
					return F.each(s, function (e, t) {
						s[e] = Number(t)
					}), F.each(W.default.get(a).values, function (e, a) {
						if (e = e.substr(1), !1 === a.pseudoId)
							if (F("#geo_" + G.default.current.geometry + "_" + e).attr("fill-opacity", U.default.isArealOn ? t.arealOpacity : 0), !1 === a.value || H.default.isNull(a.value)) !1 === a.value && (i = !0), H.default.isNull(a.value) && (r = !0), F("#geo_" + G.default.current.geometry + "_" + e).attr("fill", "#" + G.default.current.properties.nodatacolor);
							else {
								var o = !1,
									n = "000000";
								F.each(s, function (e, t) {
									if (Number(a.value) < t) return n = l[e], !(o = !0)
								}), !1 === o && (n = l[l.length - 1]), F("#geo_" + G.default.current.geometry + "_" + e).attr("fill", "#" + n)
							}
					}), {
						noData: r,
						privacy: i
					}
				}, e
			}();
			t.default = new e
		}).call(this, a(31))
	},
	764: function (e, t, o) {
		"use strict";
		(function (U) {
			Object.defineProperty(t, "__esModule", {
				value: !0
			});
			var r = o(79),
				i = o(40),
				l = o(1),
				s = o(765),
				W = o(10),
				V = o(48),
				H = o(84),
				B = o(17),
				u = o(213),
				v = o(20),
				G = o(148),
				Y = o(30),
				a = o(28),
				z = o(18);
			U.fn.exists = function () {
				return 0 < this.length
			};
			var e = function () {
				function e(e) {
					var t = this;
					this.arealOpacity = 200, this.maxBarSize = 200, this.fallBackCenterId = 5, this.barBaseWidth = 1, this.memberRegionCount = 0, this.currentDatasetIda = 0, this.qualiShownValues = [], Y.bajoodooLog("new ThematicalMapLegend"), this.arealOpacity /= 255, this.mVis = e, W.default.emitter.on(z.LEGEND_RERENDER, function () {
						return t.showLegend()
					})
				}
				return e.prototype.changeVis = function (e) {
					Y.bajoodooLog("ThematicalMapLegend.changeVis()"), this.mVis = e
				}, e.prototype.clearLegend = function () {
					Y.bajoodooLog("ThematicalMapLegend.clearLegend()")
				}, e.prototype.bindCheckboxes = function () {
					var t = this;
					U(".visEye").unbind(), U(".visEye").click(function (e) {
						return t.handleCheckboxChange(e)
					})
				}, e.prototype.showLegend = function () {
					if (W.default.isMapLegendVisible) {
						Y.bajoodooLog("ThematicalMapLegend.showLegend()"), this.clearLegend();
						var o = this;
						this.noDataAndPrivacy = this.mVis.getNoDataAndPrivacy();
						var n = v.default.current.visualizations,
							r = {
								symbol: !1,
								areal: !1
							};
						W.default.visCount = 0, W.default.colorFields = [], U.each(H.default.getVisOrder(), function (a, e) {
							U.each(e, function (e, t) {
								!1 === H.default.isUndefined(n[t]) && (r[a] = !0, W.default.visCount++, U("#invisibleWorker").html(null), U("#" + t + "Legend").html(o["show" + H.default.ucFirst(t)](n[t])))
							}), U("#" + a + "Legend").css("border-top", "0px none"), 1 < W.default.visCount && U("#" + a + "Legend").css("border-top", "1px solid #ccc")
						}), U(".colorField").off(), U(".colorField").on("mouseover", function () {
							o.showMemberRegions(this)
						}), U(".colorField").on("mouseout", function () {
							o.hideMemberRegions(this)
						}), this.showLegendSymbol(), H.default.resizeColorFieldsAndTables(), this.bindCheckboxes(), W.default.emitter.emit(z.LEGEND_DONE), Y.bajoodooLog("LEGEND DONE!")
					}
				}, e.prototype.showMemberRegions = function (e) {
					var t = U(e).attr("id"),
						a = void 0 === W.default.colorFields[t] ? -1 : W.default.colorFields[t].dataset_ida;
					if (0 < a) {
						var o = this.getMemberRegions(e, a);
						for (var n in U(e).attr("title", o.length), U(e).css("border", "2px inset #aaa"), o) u.default.createRegionRollover(o[n], !1, !1)
					} else if (0 === t.indexOf("sector")) {
						U(e).css("border", "2px inset #aaa");
						var r = t.substr(t.lastIndexOf("_") + 1),
							i = U("#symbolContainer polyline").filter(function () {
								return this.id.match(/sector_[0-9]{1,2}_[0-9]+/) && this.id.substr(this.id.lastIndexOf("_") + 1) !== r
							});
						U(i).each(function () {
							U(this).attr("fill-opacity", .1)
						});
						var l = U("#symbolContainer polyline").filter(function () {
							return this.id.match(/sector_[0-9]{1,2}_[0-9]+/) && this.id.substr(this.id.lastIndexOf("_") + 1) === r
						});
						U(l).each(function () {
							U(this).attr("fill-opacity", 1)
						}), U("#symbolContainer circle").each(function () {
							U(this).attr("stroke-opacity", .1)
						})
					}
				}, e.prototype.hideMemberRegions = function (e) {
					if (u.default.handleMouseLeaveRegion(!0), U(e).css("border", "1px solid #444"), 0 === U(e).attr("id").indexOf("sector")) {
						var t = v.default.current.getVisualization("sect"),
							a = t.spec.fill_transparency / 100,
							o = U("#symbolContainer polyline").filter(function () {
								return this.id.match(/sector_[0-9]{1,2}_[0-9]+/)
							});
						U(o).each(function () {
							U(this).attr("fill-opacity", a)
						}), U("#symbolContainer circle").each(function () {
							U(this).attr("stroke-opacity", t.spec.ln_transparency / 100)
						})
					}
				}, e.prototype.getMemberRegions = function (e, t) {
					var a = [];
					if (!(t <= (this.memberRegionCount = 0))) {
						var o = V.default.get(t),
							n = U(e).attr("id"),
							r = null == W.default.colorFields[n] ? {
								dataset_ida: -1
							} : W.default.colorFields[n],
							i = v.default.current.geometry,
							l = o.getValuesObject(!1, !1, !1),
							s = W.default.validIds;
						for (var u in s) {
							var c = this.getDataValueForUnitId(l, s[u]);
							(r.anyOtherValue != H.default.getColorRemainderValue() && (void 0 !== r.anyOtherValue && c == r.anyOtherValue || !1 !== c && null !== c && (null === r.minValue && c < r.maxValue || null === r.maxValue && c >= r.minValue || c >= r.minValue && c < r.maxValue)) || r.anyOtherValue == H.default.getColorRemainderValue() && 0 != c && null != c && -1 == this.qualiShownValues.indexOf(c)) && a.push("geo_" + i + "_" + s[u])
						}
						return a
					}
				}, e.prototype.getDataValueForUnitId = function (e, t) {
					return e["|" + t]
				}, e.prototype.showLegendSymbol = function () {
					Y.bajoodooLog("ThematicalMapLegend.showLegendSymbol()");
					if (!0 !== H.default.isUndefined(this.barSetupData) || !0 !== H.default.isUndefined(this.symbolSetupData)) {
						U("#symbolLegendPlaceHolder").html(null);
						var e = this.getMapLegendInitialRatio(),
							s = this.getLegendRatio(),
							u = 0,
							t = [0, 0, W.default.initialViewBox.width * e, W.default.initialViewBox.height * e],
							c = U(document.createElementNS("http://www.w3.org/2000/svg", "svg")).attr({
								id: "legendSymbols",
								version: "1.2",
								baseProfile: "tiny"
							}).attr("viewBox", t.join(" ")).attr("preserveAspectRatio", "xMidYMid meet").attr("xmlns:xlink", "http://www.w3.org/1999/xlink").attr("xmlns", "http://www.w3.org/2000/svg").attr("style", "cursor: default;"),
							a = U("#symbolLegend").css("width"),
							o = (a = Number(a.substring(0, a.length - 2))) / Number(t[2]) * Number(t[3]) + 5;
						U("#legendSymbols").css("height", o + "px"), U("#legendSymbols").attr("height", o);
						var d = !1,
							n = 1,
							p = "mathematical";
						"MS Edge" !== Z.browser && "Explorer" !== Z.browser || (d = !0, n *= o / 150);
						var f = null;
						if (!1 === H.default.isUndefined(this.barSetupData)) {
							Y.bajoodooLog("ThematicalMapLegend.showLegendSymbol.bar()");
							var r = this.barSetupData;
							!1 === r.showLines && (r.lineWidth = 0);
							var i = 1,
								l = (f = 300, this.noDataAndPrivacy.bar.minValue),
								h = this.noDataAndPrivacy.bar.maxValue,
								m = {
									null: {
										realPos: null,
										finalPos: null,
										realValue: 0,
										showValue: H.default.showValue(0, r.decimalPlaces, !1, !1),
										length: null,
										height: null
									},
									plus: {
										realPos: null,
										finalPos: null,
										realValue: null,
										showValue: null,
										length: null,
										height: null
									},
									minus: {
										realPos: null,
										finalPos: null,
										realValue: null,
										showValue: null,
										length: null,
										height: null
									}
								},
								g = 0;
							g += 1 * r.barWidth, g += 0 * r.gapWidth, g += 2 * r.lineWidth, g += 2 * this.barBaseWidth;
							i = 1;
							(g = H.default.scaleSymbol(g, !0)) > this.maxBarSize * s && (i = g / (this.maxBarSize * s));
							var y, v, b, _, T, E, S, L, O = 1,
								I = H.default.scaleSymbol((h - l) / r.ratio, !0);
							!0 === r.hasPositiveValue && (I += H.default.scaleSymbol(r.lineWidth, !0)), !0 === r.hasNegativeValue && (I += H.default.scaleSymbol(r.lineWidth, !0)), I > this.maxBarSize * s && (O = I / (this.maxBarSize * s)), !0 === r.isBar ? (y = H.default.scaleSymbol(-l / r.ratio, !0) / O, v = 0, m.null.realPos = y, m.null.finalPos = y) : (y = 0, v = H.default.scaleSymbol(h / r.ratio, !0) / O, m.null.realPos = v, m.null.finalPos = v), _ = !0 === r.isBar ? (b = y, g / i) : (b = g / i, v), T = H.default.scaleSymbol(r.barWidth, !0) / i, E = H.default.scaleSymbol(r.lineWidth + this.barBaseWidth, !0) / i, S = H.default.scaleSymbol((h - l) / r.ratio, !0) / O, L = !0 === r.isBar && 0 !== l || !1 === r.isBar && 0 !== h ? H.default.scaleSymbol(r.lineWidth, !0) : 0, !0 === r.isBar ? (0 !== l && (m.minus.realPos = L, m.minus.finalPos = L, m.minus.realValue = l, m.minus.showValue = H.default.showValue(l / O, r.decimalPlaces, !1, !1)), 0 !== h && (m.plus.realPos = L + S, m.plus.finalPos = L + S + 10, m.plus.realValue = h, m.plus.showValue = H.default.showValue(h / O, r.decimalPlaces, !1, !1))) : !1 === r.isBar && (0 !== h && (m.plus.realPos = L, m.plus.finalPos = L, m.plus.realValue = h, m.plus.showValue = H.default.showValue(h / O, r.decimalPlaces, !1, !1)), 0 !== l && (m.minus.realPos = L + S, m.minus.finalPos = L + S, m.minus.realValue = l, m.minus.showValue = H.default.showValue(l / O, r.decimalPlaces, !1, !1))), U(document.createElementNS("http://www.w3.org/2000/svg", "text")).attr("id", "lengthTest").attr("x", -1e3).attr("y", -1e3).attr("font-size", s * n + "em").attr("text-anchor", "end").attr("dominant-baseline", p).attr("text-align", "right").attr("fill", "#444").prop("textContent", m.null.showValue).appendTo(c), c.appendTo("#symbolLegendPlaceHolder"), m.null.length = Number(document.getElementById("lengthTest").getBoundingClientRect().width), m.null.height = Number(document.getElementById("lengthTest").getBoundingClientRect().height), u = m.null.length, !1 === H.default.isNull(m.minus.realValue) && (U("#lengthTest").prop("textContent", m.minus.showValue), m.minus.length = Number(document.getElementById("lengthTest").getBoundingClientRect().width), m.minus.height = Number(document.getElementById("lengthTest").getBoundingClientRect().height), m.minus.length > u && (u = m.minus.length)), !1 === H.default.isNull(m.plus.realValue) && (U("#lengthTest").prop("textContent", m.plus.showValue), m.plus.length = Number(document.getElementById("lengthTest").getBoundingClientRect().width), m.plus.height = Number(document.getElementById("lengthTest").getBoundingClientRect().height), m.plus.length > u && (u = m.plus.length)), !1 === r.isBar && (0 !== h && m.null.finalPos < m.plus.finalPos + m.plus.height * s * 1.5 && (m.null.finalPos = m.plus.finalPos + m.plus.height * s * 1.5), 0 !== l && m.minus.finalPos < m.null.finalPos + m.null.height * s * 1.5 && (m.minus.finalPos = m.null.finalPos + m.null.height * s * 1.5)), !1 === r.isBar && (m.plus.finalPos += m.plus.height * s * .5, m.minus.finalPos -= m.minus.height * s * .5), U("#lengthTest").remove();
							var A = U(document.createElementNS("http://www.w3.org/2000/svg", "g")).attr({
								id: "_legend_group",
								style: "pointer-events: none;",
								transform: "translate(10, 10)"
							}).appendTo(c);
							if (U(document.createElementNS("http://www.w3.org/2000/svg", "rect")).attr({
									x: !0 === r.isBar ? L : E,
									y: !0 === r.isBar ? E : L,
									width: !0 === r.isBar ? S : T,
									height: !0 === r.isBar ? T : S,
									fill: "#CCCCCC"
								}).attr("stroke-width", 0).appendTo(A), !0 === r.showLines) {
								var w = " ";
								!0 === r.isBar ? (0 < h && (w += y + "," + E + " " + (L + S) + "," + E + " " + (L + S) + "," + (E + T) + " " + y + "," + (E + T) + " "), l < 0 && (w += y + "," + (E + T) + " " + L + "," + (E + T) + " " + L + "," + E + " " + y + "," + E + " ")) : (0 < h && (w += E + "," + v + " " + E + "," + L + " " + (E + T) + "," + L + " " + (E + T) + "," + v + " "), l < 0 && (w += E + T + "," + v + " " + (E + T) + "," + (L + S) + " " + E + "," + (L + S) + " " + E + "," + v + " ")), U(document.createElementNS("http://www.w3.org/2000/svg", "polyline")).attr({
									points: w,
									stroke: "#666666"
								}).attr("fill", "none").attr("stroke-width", H.default.scaleSymbol(r.lineWidth, !0)).attr("stroke-opacity", r.lineTrans / 200).attr("stroke-linejoin", "miter").appendTo(A)
							}
							if (U(document.createElementNS("http://www.w3.org/2000/svg", "line")).attr({
									id: "_bar_line_1",
									x1: y,
									y1: v,
									x2: b,
									y2: _
								}).attr("stroke", "#FFFFFF").attr("vector-effect", "non-scaling-stroke").attr("stroke-width", 3).attr("stroke-opacity", .65).attr("stroke-linejoin", "miter").appendTo(A), U(document.createElementNS("http://www.w3.org/2000/svg", "line")).attr({
									id: "_bar_line_2",
									x1: y,
									y1: v,
									x2: b,
									y2: _
								}).attr("stroke", "#000000").attr("vector-effect", "non-scaling-stroke").attr("stroke-width", 1).attr("stroke-opacity", 1).attr("stroke-linejoin", "miter").appendTo(A), !0 === r.isBar ? (U(document.createElementNS("http://www.w3.org/2000/svg", "line")).attr({
									id: "_bar_line_3",
									x1: y + 5,
									y1: v,
									x2: y - 5,
									y2: v
								}).attr("stroke", "#000000").attr("vector-effect", "non-scaling-stroke").attr("stroke-width", 1).attr("stroke-opacity", 1).attr("stroke-linejoin", "miter").appendTo(A), U(document.createElementNS("http://www.w3.org/2000/svg", "line")).attr({
									id: "_bar_line_4",
									x1: y + 5,
									y1: _,
									x2: y - 5,
									y2: _
								}).attr("stroke", "#000000").attr("vector-effect", "non-scaling-stroke").attr("stroke-width", 1).attr("stroke-opacity", 1).attr("stroke-linejoin", "miter").appendTo(A)) : !1 === r.isBar && (U(document.createElementNS("http://www.w3.org/2000/svg", "line")).attr({
									id: "_bar_line_3",
									x1: y,
									y1: v + 5,
									x2: y,
									y2: v - 5
								}).attr("stroke", "#000000").attr("vector-effect", "non-scaling-stroke").attr("stroke-width", 1).attr("stroke-opacity", 1).attr("stroke-linejoin", "miter").appendTo(A), U(document.createElementNS("http://www.w3.org/2000/svg", "line")).attr({
									id: "_bar_line_4",
									x1: b,
									y1: v + 5,
									x2: b,
									y2: v - 5
								}).attr("stroke", "#000000").attr("vector-effect", "non-scaling-stroke").attr("stroke-width", 1).attr("stroke-opacity", 1).attr("stroke-linejoin", "miter").appendTo(A)), !0 === r.isBar) {
								var C = g;
								!1 === H.default.isNull(m.plus.realValue) && (U(document.createElementNS("http://www.w3.org/2000/svg", "text")).attr("id", "textPlus").attr("x", m.plus.realPos + 10 * s).attr("y", C + (m.plus.height * (!0 === d ? 1 : .5) + 10) * s).attr("font-size", s + "em").attr("text-anchor", "start").attr("dominant-baseline", p).attr("text-align", "left").attr("fill", "#444").prop("textContent", m.plus.showValue).appendTo(A), U(document.createElementNS("http://www.w3.org/2000/svg", "polyline")).attr("points", m.plus.realPos + "," + C + " " + m.plus.realPos + "," + (C + (.5 * m.plus.height + 10) * s) + " " + (m.plus.realPos + 5 * s) + "," + (C + (.5 * m.plus.height + 10) * s)).attr("fill", "none").attr("stroke", "#444").attr("stroke-width", 1).attr("stroke-opacity", 1).attr("stroke-linejoin", "miter").attr("vector-effect", "non-scaling-stroke").appendTo(A), f = C + (1 * m.plus.height + 10) * s), U(document.createElementNS("http://www.w3.org/2000/svg", "text")).attr("id", "textNull").attr("x", m.null.realPos + 10 * s).attr("y", C + (m.null.height * (!0 === d ? 2 : 1.5) + 10) * s).attr("font-size", s + "em").attr("text-anchor", "start").attr("dominant-baseline", p).attr("text-align", "left").attr("fill", "#444").prop("textContent", m.null.showValue).appendTo(A), U(document.createElementNS("http://www.w3.org/2000/svg", "polyline")).attr("points", m.null.realPos + "," + C + " " + m.null.realPos + "," + (C + (1.5 * m.null.height + 10) * s) + " " + (m.null.realPos + 5 * s) + "," + (C + (1.5 * m.null.height + 10) * s)).attr("fill", "none").attr("stroke", "#444").attr("stroke-width", 1).attr("stroke-opacity", 1).attr("stroke-linejoin", "miter").attr("vector-effect", "non-scaling-stroke").appendTo(A), f = C + (2 * m.plus.height + 10) * s, !1 === H.default.isNull(m.minus.realValue) && (U(document.createElementNS("http://www.w3.org/2000/svg", "text")).attr("id", "textMinus").attr("x", m.minus.realPos + 10 * s).attr("y", C + (m.minus.height * (!0 === d ? 3 : 2.5) + 10) * s).attr("font-size", s + "em").attr("text-anchor", "start").attr("dominant-baseline", p).attr("text-align", "left").attr("fill", "#444").prop("textContent", m.minus.showValue).appendTo(A), U(document.createElementNS("http://www.w3.org/2000/svg", "polyline")).attr("points", m.minus.realPos + "," + C + " " + m.minus.realPos + "," + (C + (2.5 * m.minus.height + 10) * s) + " " + (m.minus.realPos + 5 * s) + "," + (C + (2.5 * m.minus.height + 10) * s)).attr("fill", "none").attr("stroke", "#444").attr("stroke-width", 1).attr("stroke-opacity", 1).attr("stroke-linejoin", "miter").attr("vector-effect", "non-scaling-stroke").appendTo(A), f = C + (3 * m.plus.height + 10) * s)
							} else if (!1 === r.isBar) {
								var P = g;
								!1 === H.default.isNull(m.plus.realValue) && (U(document.createElementNS("http://www.w3.org/2000/svg", "text")).attr("id", "textPlus").attr("x", P + (u + 25) * s).attr("y", m.plus.finalPos + (!0 === d ? m.plus.height / 2 : 0)).attr("font-size", s + "em").attr("text-anchor", "end").attr("dominant-baseline", p).attr("text-align", "right").attr("fill", "#444").prop("textContent", m.plus.showValue).appendTo(A), f = m.plus.finalPos, U(document.createElementNS("http://www.w3.org/2000/svg", "polyline")).attr("points", P + 5 * s + "," + m.plus.realPos + " " + (P + 10 * s) + "," + m.plus.realPos + " " + (P + 15 * s) + "," + m.plus.finalPos + " " + (P + (u - m.plus.length + 20) * s) + "," + m.plus.finalPos).attr("fill", "none").attr("stroke", "#444").attr("stroke-width", 1).attr("stroke-opacity", 1).attr("stroke-linejoin", "miter").attr("vector-effect", "non-scaling-stroke").appendTo(A)), U(document.createElementNS("http://www.w3.org/2000/svg", "text")).attr("id", "textNull").attr("x", P + (u + 25) * s).attr("y", m.null.finalPos + (!0 === d ? m.null.height / 2 : 0)).attr("font-size", s + "em").attr("text-anchor", "end").attr("dominant-baseline", "mathematical").attr("text-align", "right").attr("fill", "#444").prop("textContent", m.null.showValue).appendTo(A), f = m.null.finalPos, U(document.createElementNS("http://www.w3.org/2000/svg", "polyline")).attr("points", P + 5 * s + "," + m.null.realPos + " " + (P + 10 * s) + "," + m.null.realPos + " " + (P + 15 * s) + "," + m.null.finalPos + " " + (P + (u - m.null.length + 20) * s) + "," + m.null.finalPos).attr("fill", "none").attr("stroke", "#444").attr("stroke-width", 1).attr("stroke-opacity", 1).attr("stroke-linejoin", "miter").attr("vector-effect", "non-scaling-stroke").appendTo(A), !1 === H.default.isNull(m.minus.realValue) && (U(document.createElementNS("http://www.w3.org/2000/svg", "text")).attr("id", "textMinus").attr("x", P + (u + 25) * s).attr("y", m.minus.finalPos + (!0 === d ? m.minus.height / 2 : 0)).attr("font-size", s + "em").attr("text-anchor", "end").attr("dominant-baseline", p).attr("text-align", "right").attr("fill", "#444").prop("textContent", m.minus.showValue).appendTo(A), f = m.minus.realPos, U(document.createElementNS("http://www.w3.org/2000/svg", "polyline")).attr("points", P + 5 * s + "," + m.minus.realPos + " " + (P + 10 * s) + "," + m.minus.realPos + " " + (P + 15 * s) + "," + m.minus.finalPos + " " + (P + (u - m.minus.length + 20) * s) + "," + m.minus.finalPos).attr("fill", "none").attr("stroke", "#444").attr("stroke-width", 1).attr("stroke-opacity", 1).attr("stroke-linejoin", "miter").attr("vector-effect", "non-scaling-stroke").appendTo(A), f = m.minus.finalPos)
							}
							f += 20 * s
						} else if (!1 === H.default.isUndefined(this.symbolSetupData)) {
							Y.bajoodooLog("ThematicalMapLegend.showLegendSymbol.symSect()");
							var N, M, D, R, k = this.symbolSetupData,
								j = [];
							if (4 === k.symbolType) {
								var x = G.default.get(k.vectorSymbol);
								U.each(k.symbolValues, function (e, t) {
									0 === e && j.push(t), e === k.symbolValues.length - 1 && !0 === k.hasBiasMin && j.push(t)
								})
							} else j = k.symbolValues;
							var V = [],
								B = [];
							U.each(j, function (e, t) {
								var a, o = H.default.getRadius(t, k.symbolType, k.ratio, !1);
								0 === e && (4 === k.symbolType ? (N = o * x.size + s * k.lineWidth / 2, M = o * x.size + s * k.lineWidth / 2, D = o * x.size / 2 + s * k.lineWidth / 2) : 0 === k.rotationDegree ? 1 === k.symbolType ? (N = 2 * o + 10 * s + s * k.lineWidth / 2, M = 2 * o + s * k.lineWidth / 2, D = o + s * k.lineWidth / 2) : 2 === k.symbolType ? (N = 2 * o + 10 * s + s * k.lineWidth / 2, M = 2 * o + s * k.lineWidth / 2, D = o + s * k.lineWidth / 2) : 3 === k.symbolType && (N = o + Math.sin(H.default.deg2rad(30)) * o + 10 * s + s * k.lineWidth / 2, M = Math.cos(H.default.deg2rad(30)) * o * 2 + s * k.lineWidth / 2, D = Math.cos(H.default.deg2rad(30)) * o + s * k.lineWidth / 2) : 45 === k.rotationDegree && (N = 2 * o * Math.SQRT2 + 10 * s + s * k.lineWidth / 2, M = 2 * o * Math.SQRT2 + s * k.lineWidth / 2, D = o * Math.SQRT2 + s * k.lineWidth / 2), 1 === k.symbolType || 45 === k.rotationDegree ? R = [D, 2 * D, 2 * D + 5 * s, 2 * D + 10 * s, 2 * D + 15 * s] : 2 === k.symbolType ? R = [M, M + 5 * s, M + 10 * s, M + 15 * s] : 3 === k.symbolType ? R = [D, 2 * D, 2 * D + 5 * s, 2 * D + 10 * s, 2 * D + 15 * s] : 4 === k.symbolType && (R = [D, 2 * D, 2 * D + 5 * s, 2 * D + 10 * s, 2 * D + 15 * s])), a = 1 === j.length && !0 === k.hasBiasMax && !0 === k.hasBiasMin ? "≤≥ " : !0 === k.hasBiasMax && 0 === e ? "≥ " : !0 === k.hasBiasMin && e === j.length - 1 ? "≤ " : "", !0 === k.crosses0 ? a += "±" : !0 === k.hasNegative && (a += "-"), U(document.createElementNS("http://www.w3.org/2000/svg", "text")).attr("id", "lengthTest" + e).attr("x", -1e3).attr("y", -1e3).attr("font-size", s * n + "em").attr("text-anchor", "end").attr("dominant-baseline", p).attr("text-align", "right").attr("fill", "#444").prop("textContent", a + H.default.showValue(t, k.decimalPlaces, !1, !1)).appendTo(c)
							}), c.appendTo("#symbolLegendPlaceHolder"), U.each(j, function (e, t) {
								V[e] = Number(document.getElementById("lengthTest" + e).getBoundingClientRect().width), B[e] = Number(document.getElementById("lengthTest" + e).getBoundingClientRect().height), V[e] > u && (u = V[e]), U("#lengthTest" + e).remove()
							});
							var z = 0;
							U.each(j, function (e, t) {
								var a, o, n = H.default.getRadius(t, k.symbolType, k.ratio, !1);
								1 === k.symbolType ? a = U(document.createElementNS("http://www.w3.org/2000/svg", "circle")).attr({
									r: n,
									cy: N - n,
									cx: D,
									fill: "#" + k.fillColor,
									stroke: "#666666"
								}) : 2 === k.symbolType ? a = U(document.createElementNS("http://www.w3.org/2000/svg", "rect")).attr({
									x: 0 === k.rotationDegree ? M - 2 * n : D - n,
									y: 0 === k.rotationDegree ? N - 2 * n : N - n * Math.SQRT2 - n,
									width: 2 * n,
									height: 2 * n,
									fill: "#" + k.fillColor,
									stroke: "#666666",
									transform: "rotate(" + k.rotationDegree + "," + D + "," + (N - n * Math.SQRT2) + ")"
								}) : 3 === k.symbolType ? a = U(document.createElementNS("http://www.w3.org/2000/svg", "polygon")).attr({
									points: M - n * Math.cos(H.default.deg2rad(30)) + "," + (N - n - n * Math.sin(H.default.deg2rad(30))) + " " + M + "," + N + " " + (M - n * Math.cos(H.default.deg2rad(30)) * 2) + "," + N,
									fill: "#" + k.fillColor,
									stroke: "#666666"
								}) : 4 === k.symbolType && (a = U(document.createElementNS("http://www.w3.org/2000/svg", "path")).attr({
									x: 0,
									y: !1 === H.default.isNull(f) ? 2 * f : 0,
									d: x.path,
									fill: "#" + k.fillColor,
									stroke: "#666666",
									transform: "translate(" + (0 === e ? 0 : z / 2 - n * x.size / 2) + "," + z + ") scale(" + n + "," + n + ") rotate(" + k.rotationDegree + "," + D + "," + (N - n * x.size / 2) + ")"
								}), z += n * x.size), a.attr("fill-opacity", 0 === e ? k.fillTrans / 100 : 0).attr("stroke-width", H.default.scaleSymbol(k.lineWidth / (4 === k.symbolType ? n : 1))).attr("stroke-opacity", 0 === e || 4 === k.symbolType ? 1 : .5).attr("stroke-linejoin", "miter").appendTo(c), !1 === H.default.isNull(f) && 4 === k.symbolType && (N += n * x.size);
								var r, i, l = o = 3 === k.symbolType ? N - n - n * Math.sin(H.default.deg2rad(30)) : 2 === k.symbolType && 45 === k.rotationDegree ? N - n * Math.SQRT2 * 2 : 2 === k.symbolType && 0 === k.rotationDegree ? N - 2 * n : 4 === k.symbolType ? N - n * x.size / 2 : N - 2 * n;
								!1 === H.default.isNull(f) && l < f + B[e] * s && (l = f + B[e] * s), r = 1 === j.length && !0 === k.hasBiasMax && !0 === k.hasBiasMin ? "≤≥ " : !0 === k.hasBiasMax && 0 === e ? "≥ " : !0 === k.hasBiasMin && e === j.length - 1 ? "≤ " : "", !0 === k.crosses0 ? r += "±" : !0 === k.hasNegative && (r += "-"), U(document.createElementNS("http://www.w3.org/2000/svg", "text")).attr("x", R[R.length - 1] + (u + 5) * s).attr("y", l + (!0 === d ? B[e] / 2 : 0)).attr("font-size", s + "em").attr("text-anchor", "end").attr("dominant-baseline", p).attr("text-align", "right").attr("fill", "#444").prop("textContent", r + H.default.showValue(t, k.decimalPlaces, !1, !1)).appendTo(c), 1 === k.symbolType || 45 === k.rotationDegree ? i = U(document.createElementNS("http://www.w3.org/2000/svg", "polyline")).attr("points", R[0] + "," + o + " " + R[1] + "," + o + " " + R[2] + "," + o + " " + R[3] + "," + l + " " + (R[4] + (u - V[e]) * s) + "," + l) : 2 === k.symbolType ? i = U(document.createElementNS("http://www.w3.org/2000/svg", "polyline")).attr("points", R[0] + "," + o + " " + R[1] + "," + o + " " + R[2] + "," + l + " " + (R[3] + (u - V[e]) * s) + "," + l) : 3 === k.symbolType ? i = U(document.createElementNS("http://www.w3.org/2000/svg", "polyline")).attr("points", M - n * Math.cos(H.default.deg2rad(30)) + "," + o + " " + R[1] + "," + o + " " + R[2] + "," + o + " " + R[3] + "," + l + " " + (R[4] + (u - V[e]) * s) + "," + l) : 4 === k.symbolType && (i = U(document.createElementNS("http://www.w3.org/2000/svg", "polyline")).attr("points", D + "," + o + " " + R[1] + "," + o + " " + R[2] + "," + o + " " + R[3] + "," + l + " " + (R[4] + (u - V[e]) * s) + "," + l)), i.attr("fill", "none").attr("stroke", "#444").attr("stroke-width", 1).attr("stroke-opacity", 1).attr("stroke-linejoin", "miter").attr("vector-effect", "non-scaling-stroke").appendTo(c), f = l
							}), f < N && (f = N), f += 10 * s
						}
						c.appendTo("#symbolLegendPlaceHolder");
						var F = this.getViewBoxValues("legendSymbols");
						!1 === H.default.isUndefined(f) && (F[3] = f, U("#legendSymbols").attr("viewBox", F.join(" "))), o = (a = parseInt(U("#symbolLegend").css("width"))) / Number(F[2]) * Number(F[3]) + 5, U("#legendSymbols").css("height", o + "px"), U("#legendSymbols").attr("height", o)
					}
				}, e.prototype.getMapLegendInitialRatio = function () {
					try {
						var e = Number(document.getElementById("bg").getBoundingClientRect().width),
							t = void 0;
						if (!0 === W.default.isBottomLegend) {
							var a = parseInt(U("#tML").css("padding-left")) + parseInt(U("#tML").css("padding-right"));
							t = window.innerWidth, t -= a
						} else t = U("#tML").css("width"), t = Number(t.substring(0, t.length - 2));
						return t / (e / (W.default.actualZoomFactor / 100))
					} catch (e) {
						throw new Error("getMapLegendInitialRatio error (" + e.message + ")")
					}
				}, e.prototype.getLegendRatio = function () {
					try {
						var e = this.getMapLegendInitialRatio(),
							t = void 0;
						if (!0 === W.default.isBottomLegend) {
							var a = parseInt(U("#tML").css("padding-left")) + parseInt(U("#tML").css("padding-right"));
							t = window.innerWidth, t -= a
						} else t = U("#tML").css("width"), t = Number(t.substring(0, t.length - 2));
						return W.default.initialViewBox.width * e / t
					} catch (e) {
						throw new Error("getLegendRatio error (" + e.message + ")")
					}
				}, e.prototype.getViewBoxValues = function (t) {
					try {
						var e = U("#" + t)[0].getAttribute("viewBox").split(" ");
						for (var a in e) e[a] = Number(e[a]);
						return e
					} catch (e) {
						throw new Error("no '" + t + "' viewBox found (" + e.message + ")")
					}
				}, e.prototype.showBar = function (e) {
					Y.bajoodooLog("ThematicalMapLegend.showBar()");
					var t, i = B.default.localization,
						a = 1 === e.spec.is_bar,
						o = 1 === e.spec.is_stacked,
						n = parseInt(e.spec.bar_width),
						r = parseInt(e.spec.gap_width),
						l = parseInt(e.spec.ln_thickness),
						s = parseInt(e.spec.fill_transparency),
						u = parseInt(e.spec.ln_transparency),
						c = e.spec.ln_color,
						d = 1 === e.spec.show_ln,
						p = Number(e.spec.ratio),
						f = parseInt(e.spec.decimal_places),
						h = parseInt(e.vis_data_info),
						m = 1 === e.show_info_value,
						g = 1 === e.info_without_pseudo,
						y = new Array,
						v = new Array,
						b = new Array,
						_ = 0,
						T = 0;
					U.each(e.bars, function (e, t) {
						var a = V.default.get(t.dataset);
						y.push(t.dataset), v.push(a.info), b.push(t.color), a.info.max > T && (T = a.info.max), a.info.min < _ && (_ = a.info.min)
					});
					var E = 0 < T,
						S = _ < 0;
					!1 === d && (l = 0);
					var L = !0 === o ? 1 : y.length,
						O = 0;
					O += L * n, O += (L - 1) * r, O += 2 * L * l, O += 2 * this.barBaseWidth, O = H.default.getRadiusForCurrentScale(H.default.scaleSymbol(O, !0)), t = this.getTitle(e.languages[i.ida].name, z.VISUALIZATION_TYPE_SYMBOL), U("<div/>", {
						id: "symbolLegendPlaceHolder",
						class: "svgPlaceholderDiv"
					}).appendTo("#invisibleWorker"), t += U("#symbolLegendPlaceHolder")[0].outerHTML, U("#invisibleWorker").html(null), this.barSetupData = {
						isBar: a,
						isStacked: o,
						barWidth: n,
						gapWidth: r,
						lineWidth: l,
						ratio: p,
						showLines: d,
						lineCol: c,
						lineTrans: u,
						fillTrans: s,
						dsArray: y,
						dataStatsArray: v,
						dataColourArray: b,
						decimalPlaces: f,
						hasPositiveValue: E,
						hasNegativeValue: S
					}, t += this.getSubTitle(e.languages[i.ida].name_bars), U("<table/>", {
						id: "barsTable",
						style: "margin-bottom: 0.2em;"
					}).appendTo("#invisibleWorker"), U("<colgroup/>", {
						id: "barsTableColGroup",
						class: "colorFieldTableGroup"
					}).appendTo(U("#barsTable")), U("<col/>").attr("width", "*").appendTo(U("#barsTableColGroup")), U("<col/>").attr("width", "*").appendTo(U("#barsTableColGroup"));
					var I = 0;
					return U.each(e.bars, function (e, t) {
						var a = 0;
						U("<tr/>", {
							id: "barsTableRow_" + ++I,
							class: "tableRow"
						}).appendTo(U("#barsTable")), U("<td/>", {
							id: "barsTableCell_" + I + "_" + ++a,
							style: "vertical-align: top; padding-top: 0.2em;"
						}).appendTo(U("#barsTableRow_" + I));
						var o = H.default.getColorFieldOrLine(t.color, s);
						if (o.attr("id", "sector_" + e), W.default.colorFields["sector_" + e] = {
								dataset_ida: t.dataset,
								minValue: -999999999,
								maxValue: null
							}, U("#barsTableCell_" + I + "_" + a).append(o), U("<td/>", {
								id: "barsTableCell_" + I + "_" + ++a,
								class: "paddLeft"
							}).appendTo(U("#barsTableRow_" + I)), U("#barsTableCell_" + I + "_" + a).append(t.languages[i.ida].name), !0 === m) {
							var n = H.default.getInfoLabel(h),
								r = H.default.getInfoValueString(t.dataset, g, f);
							U("#barsTableCell_" + I + "_" + a).append(" (" + n + ": " + r + ")")
						}
					}), t += U("#barsTable")[0].outerHTML, U("#invisibleWorker").html(null), !0 === this.noDataAndPrivacy.bar.privacy && (U("<div/>", {
						id: "barPrivacy",
						class: "infoLabelAndValue"
					}).appendTo("#invisibleWorker"), U("#barPrivacy").html(H.default.getPrivacy(!1, !1, !0)), t += U("#barPrivacy")[0].outerHTML, U("#invisibleWorker").html(null)), !0 === this.noDataAndPrivacy.bar.noData && (U("<div/>", {
						id: "barNoData",
						class: "infoLabelAndValue"
					}).appendTo("#invisibleWorker"), U("#barNoData").html(H.default.getNoData(!1, !1, !0)), t += U("#barNoData")[0].outerHTML, U("#invisibleWorker").html(null)), !1 === H.default.isNull(e.languages[i.ida].comment) && (t += this.getComment(e.languages[i.ida].comment)), t
				}, e.prototype.showSym = function (e) {
					Y.bajoodooLog("ThematicalMapLegend.showSym()");
					var t, a = B.default.localization,
						o = parseInt(e.spec.syms);
					if (H.default.isUndefined(e.spec.vectorSymbol)) n = 1;
					else var n = parseInt(e.spec.vectorSymbol);
					var r = parseInt(e.spec.ln_thickness),
						i = parseInt(e.spec.fill_transparency),
						l = parseInt(e.spec.ln_transparency),
						s = e.spec.fill_color,
						u = e.spec.ln_color,
						c = e.spec.fill_color_negative,
						d = e.spec.ln_color_negative,
						p = Number(e.spec.ratio),
						f = (parseInt(e.spec.center_method), parseInt(e.spec.rotation)),
						h = e.spec.symbol_values.split(",").reverse();
					for (var m in h) h[m] = Number(h[m]);
					var g = 1 === e.spec.bias_min,
						y = 1 === e.spec.bias_max,
						v = e.spec.suppression,
						b = 1 === e.spec.dynamic_suppression,
						_ = parseInt(e.spec.decimal_places),
						T = parseInt(e.vis_data_info),
						E = 1 === e.info_without_pseudo,
						S = 1 === e.show_info_value,
						L = Number(Object.keys(e.datasets)[0]),
						O = V.default.get(L).info.crosses_0,
						I = V.default.get(L).info.hasNegative;
					if (!0 === g) var A = Number(h[h.length - 1]);
					if (!0 === y) var w = Number(h[0]);
					0 === r && (r = 1);
					var C = v;
					C = v;
					if (!1 === H.default.isNull(v) && !0 === b && (C *= 1), t = this.getTitle(e.languages[a.ida].name, z.VISUALIZATION_TYPE_SYMBOL), U("<div/>", {
							id: "symbolLegendPlaceHolder",
							class: "svgPlaceholderDiv"
						}).appendTo("#invisibleWorker"), t += U("#symbolLegendPlaceHolder")[0].outerHTML, U("#invisibleWorker").html(null), this.symbolSetupData = {
							symbolValues: h,
							symbolType: o,
							vectorSymbol: n,
							ratio: p,
							rotationDegree: f,
							lineWidth: r,
							lineColor: !0 === O ? "333333" : !0 === I ? d : u,
							lineTrans: l,
							fillColor: !0 === I ? c : s,
							fillTrans: void 0 !== e.fill || !0 === O ? 0 : i,
							crosses0: O,
							hasNegative: I,
							decimalPlaces: _,
							hasBiasMin: g,
							hasBiasMax: y
						}, !0 === O) {
						var P, N = 0;
						P = !1 === H.default.isUndefined(e.spec.bipolar_labels) ? parseInt(e.spec.bipolar_labels) : 1, U("<table/>", {
							id: "additionalSymTable"
						}).appendTo("#invisibleWorker"), U("<colgroup/>", {
							id: "symTableColGroup",
							class: "colorFieldTableGroup"
						}).appendTo(U("#additionalSymTable")), U("<col/>").attr("width", "*").appendTo(U("#symTableColGroup")), U("<col/>").attr("width", "*").appendTo(U("#symTableColGroup"));
						var M = 0;
						if (U("<tr/>", {
								id: "symTableRow_" + ++N,
								class: "tableRow"
							}).appendTo(U("#additionalSymTable")), U("<td/>", {
								id: "symTableCell_" + N + "_" + ++M,
								style: "vertical-align: top; padding-top: 0.2em;"
							}).appendTo(U("#symTableRow_" + N)), void 0 !== e.fill) {
							Number(Object.keys(e.fill.datasets)[0]);
							var D = H.default.getColorFieldOrLine(u, l, !0)
						} else D = H.default.getColorFieldOrLine(s, i);
						if (D.attr("id", "symColorField_increase"), W.default.colorFields.symColorField_increase = {
								dataset_ida: L,
								minValue: 0,
								maxValue: null
							}, U("#symTableCell_" + N + "_" + M).append(D), U("<td/>", {
								id: "symTableCell_" + N + "_" + ++M,
								class: "paddLeft"
							}).appendTo(U("#symTableRow_" + N)), U("#symTableCell_" + N + "_" + M).append(0 === P ? a.messages.INCREASE : a.properties.BIPOLAR_LABELS[P].POSITIVE), M = 0, U("<tr/>", {
								id: "symTableRow_" + ++N
							}).appendTo(U("#additionalSymTable")), U("<td/>", {
								id: "symTableCell_" + N + "_" + ++M,
								style: "vertical-align: top; padding-top: 0.2em;"
							}).appendTo(U("#symTableRow_" + N)), void 0 !== e.fill) D = H.default.getColorFieldOrLine(d, l, !0);
						else D = H.default.getColorFieldOrLine(c, i);
						D.attr("id", "symColorField_decrease"), W.default.colorFields.symColorField_decrease = {
							dataset_ida: L,
							minValue: null,
							maxValue: 0
						}, U("#symTableCell_" + N + "_" + M).append(D), U("<td/>", {
							id: "symTableCell_" + N + "_" + ++M,
							class: "paddLeft"
						}).appendTo(U("#symTableRow_" + N)), U("#symTableCell_" + N + "_" + M).append(0 === P ? a.messages.DECREASE : a.properties.BIPOLAR_LABELS[P].NEGATIVE), t += U("#additionalSymTable")[0].outerHTML, U("#invisibleWorker").html(null)
					}
					if (!0 === S) {
						var R = H.default.getInfoLabel(T),
							k = H.default.getInfoValueString(L, E, _);
						U("<div/>", {
							id: "symInfo",
							class: "infoLabelAndValue"
						}).appendTo("#invisibleWorker"), U("<span/>", {
							text: R + ": ",
							style: "font-weight: 500"
						}).appendTo(U("#symInfo")), U("#symInfo").append(k), t += U("#symInfo")[0].outerHTML, U("#invisibleWorker").html(null)
					}
					if (!0 === this.noDataAndPrivacy.sym.suppressed && (C = !0 === b ? H.default.showValue(C / (W.default.actualZoomFactor / 100), _, !1) : H.default.showValue(C, _, !1), t += this.getInfo(a.messages.SUPPRESSION_MESSAGE.replace("#minVal#", "±" + C))), !0 === this.noDataAndPrivacy.sym.scaledUp) {
						var j = H.default.showValue(A, _, !1);
						t += this.getInfo(a.messages.BIAS_MIN_INFO.replace("#value#", "±" + j))
					}
					if (!0 === this.noDataAndPrivacy.sym.scaledDown) {
						var x = H.default.showValue(w, _, !1);
						t += this.getInfo(a.messages.BIAS_MAX_INFO.replace("#value#", "±" + x))
					}
					return !0 === this.noDataAndPrivacy.sym.privacy && (U("<div/>", {
						id: "symPrivacy",
						class: "infoLabelAndValue"
					}).appendTo("#invisibleWorker"), U("#symPrivacy").html(H.default.getPrivacy(!1, !1, !0)), t += U("#symPrivacy")[0].outerHTML, U("#invisibleWorker").html(null)), !0 === this.noDataAndPrivacy.sym.noData && (U("<div/>", {
						id: "symNoData",
						class: "infoLabelAndValue"
					}).appendTo("#invisibleWorker"), U("#symNoData").html(H.default.getNoData(!1, !1, !0)), t += U("#symNoData")[0].outerHTML, U("#invisibleWorker").html(null)), void 0 !== e.fill && (t += this.showChoro(e.fill, !0)), !1 === H.default.isNull(e.languages[a.ida].comment) && (t += this.getComment(e.languages[a.ida].comment)), t
				}, e.prototype.showSect = function (e) {
					Y.bajoodooLog("ThematicalMapLegend.showSect()");
					var t, i, l, s = B.default.localization,
						a = parseInt(e.spec.ln_thickness),
						o = e.spec.ln_color,
						n = parseInt(e.spec.ln_transparency),
						u = (e.spec.fill_color_remainder, parseInt(e.spec.fill_transparency)),
						r = Number(e.spec.ratio),
						c = (parseInt(e.spec.center_method), e.spec.symbol_values.split(",").reverse()),
						d = 1 === e.spec.bias_min,
						p = 1 === e.spec.bias_max,
						f = e.spec.suppression,
						h = 1 === e.spec.dynamic_suppression,
						m = parseInt(e.spec.decimal_places),
						g = parseInt(e.spec.decimal_places_sectors),
						y = parseInt(e.spec.decimal_places_percent),
						v = 1 === e.spec.show_percentage,
						b = parseInt(e.vis_data_info),
						_ = 1 === e.info_without_pseudo,
						T = 1 === e.show_info_value,
						E = parseInt(e.vis_data_info_sectors),
						S = 1 === e.info_without_pseudo_sectors,
						L = 1 === e.show_info_value_sectors;
					l = 0 < U(e.datasets).length ? (i = Object.keys(e.datasets)[0], 1 === e.spec.is_related) : (i = 0, !1);
					var O = e.sectors;
					if (!0 === d) var I = Number(c[c.length - 1]);
					if (!0 === p) var A = Number(c[0]);
					0 === a && (a = 1);
					var w = f;
					if (!1 === H.default.isNull(f) && !0 === h && (w *= 1), t = this.getTitle(e.languages[s.ida].name, z.VISUALIZATION_TYPE_SYMBOL), U("<div/>", {
							id: "symbolLegendPlaceHolder",
							class: "svgPlaceholderDiv"
						}).appendTo("#invisibleWorker"), t += U("#symbolLegendPlaceHolder")[0].outerHTML, U("#invisibleWorker").html(null), this.symbolSetupData = {
							symbolValues: c,
							symbolType: 1,
							vectorSymbol: 0,
							ratio: r,
							rotationDegree: 0,
							lineWidth: a,
							lineColor: o,
							lineTrans: n,
							fillColor: "000000",
							fillTrans: 0,
							crosses0: !1,
							hasNegative: !1,
							decimalPlaces: m,
							hasBiasMin: d,
							hasBiasMax: p
						}, !0 === T) {
						var C = H.default.getInfoLabel(b),
							P = H.default.getInfoValueString(i, _, m);
						U("<div/>", {
							id: "sectInfo",
							class: "infoLabelAndValue"
						}).appendTo("#invisibleWorker"), U("<span/>", {
							text: C + ": ",
							style: "font-weight: 500"
						}).appendTo(U("#sectInfo")), U("#sectInfo").append(P), t += U("#sectInfo")[0].outerHTML, U("#invisibleWorker").html(null)
					}
					if (!0 === this.noDataAndPrivacy.sect.suppressed && (w = !0 === h ? H.default.showValue(w / (W.default.actualZoomFactor / 100), m, !1) : H.default.showValue(w, m, !1), t += this.getInfo(s.messages.SUPPRESSION_MESSAGE.replace("#minVal#", "±" + w))), !0 === this.noDataAndPrivacy.sect.scaledUp) {
						var N = H.default.showValue(I, m, !1);
						t += this.getInfo(s.messages.BIAS_MIN_INFO.replace("#value#", "±" + N))
					}
					if (!0 === this.noDataAndPrivacy.sect.scaledDown) {
						var M = H.default.showValue(A, m, !1);
						t += this.getInfo(s.messages.BIAS_MAX_INFO.replace("#value#", "±" + M))
					}
					if (t += this.getSubTitle(e.languages[s.ida].name_sectors), U("<table/>", {
							id: "sectorsTable",
							style: "margin-bottom: 0.2em;"
						}).appendTo("#invisibleWorker"), U("<colgroup/>", {
							id: "sectorsTableColGroup",
							class: "colorFieldTableGroup"
						}).appendTo("#sectorsTable"), U("<col/>").attr("width", "*").appendTo("#sectorsTableColGroup"), U("<col/>").attr("width", "*").appendTo("#sectorsTableColGroup"), !1 === l) {
						var D = 0;
						U.each(O, function (e, t) {
							D += H.default.getInfoValue(t.dataset, S)
						})
					}
					var R = 0,
						k = 0;
					return U.each(O, function (e, t) {
						var a = 0;
						U("<tr/>", {
							id: "sectorsTableRow_" + ++R,
							class: "tableRow"
						}).appendTo("#sectorsTable"), U("<td/>", {
							id: "sectorsTableCell_" + R + "_" + ++a,
							style: "vertical-align: top; padding-top: 0.2em;",
							class: "tableRow"
						}).appendTo("#sectorsTableRow_" + R);
						var o = H.default.getColorFieldOrLine(t.color, u);
						if (o.attr("id", "sector_" + e), U("#sectorsTableCell_" + R + "_" + a).append(o), U("<td/>", {
								id: "sectorsTableCell_" + R + "_" + ++a,
								class: "paddLeft"
							}).appendTo("#sectorsTableRow_" + R), U("#sectorsTableCell_" + R + "_" + a).append(t.languages[s.ida].name), !0 === L) {
							0 !== t.dataset && (k += H.default.getInfoValue(t.dataset, S));
							var n = H.default.getInfoLabel(E);
							if (0 !== t.dataset) var r = H.default.getInfoValueString(t.dataset, S, g);
							else r = H.default.getInfoValueStringByValue(H.default.getInfoValue(i, _) - k, S, g);
							U("#sectorsTableCell_" + R + "_" + a).append("<br />(" + n + ": " + r), !0 === v && U("#sectorsTableCell_" + R + "_" + a).append(" / " + H.default.betterRounding((0 !== t.dataset ? H.default.getInfoValue(t.dataset, S) : H.default.getInfoValue(i, _) - k) / (!0 === l ? H.default.getInfoValue(i, _) : D) * 100, y) + "%"), U("#sectorsTableCell_" + R + "_" + a).append(")")
						}
					}), t += U("#sectorsTable")[0].outerHTML, U("#invisibleWorker").html(null), !0 === this.noDataAndPrivacy.sect.privacy && (U("<div/>", {
						id: "sectPrivacy",
						class: "infoLabelAndValue"
					}).appendTo("#invisibleWorker"), U("#sectPrivacy").html(H.default.getPrivacy(!1, !1, !0)), t += U("#sectPrivacy")[0].outerHTML, U("#invisibleWorker").html(null)), !0 === this.noDataAndPrivacy.sect.noData && (U("<div/>", {
						id: "sectNoData",
						class: "infoLabelAndValue"
					}).appendTo("#invisibleWorker"), U("#sectNoData").html(H.default.getNoData(!1, !1, !0)), t += U("#sectNoData")[0].outerHTML, U("#invisibleWorker").html(null)), !1 === H.default.isNull(e.languages[s.ida].comment) && (t += this.getComment(e.languages[s.ida].comment)), t
				}, e.prototype.showQuali = function (n) {
					Y.bajoodooLog("ThematicalMapLegend.showQuali()");
					var e, r = this,
						t = B.default.localization,
						i = Object.keys(n.datasets)[0];
					e = this.getTitle(n.languages[t.ida].name, z.VISUALIZATION_TYPE_AREAL), U("<table/>", {
						id: "qualiTable",
						class: "qualiTable"
					}).appendTo("#invisibleWorker"), U("<colgroup/>", {
						id: "qualiTableColGroup",
						class: "colorFieldTableGroup"
					}).appendTo("#qualiTable"), U("<col/>").attr("width", "*").appendTo("#qualiTableColGroup"), U("<col/>").attr("width", "*").appendTo("#qualiTableColGroup");
					var l = 0;
					U.each(n.datavalues.languages[t.ida], function (e, t) {
						if (e = e.substr(1), 1 === n.values[e].show) {
							var a = 0;
							U("<tr/>", {
								id: "qualiTableRow_" + ++l,
								class: "tableRow"
							}).appendTo("#qualiTable"), U("<td/>", {
								id: "qualiTableCell_" + l + "_" + ++a,
								style: "vertical-align: top; padding-top: 0.2em;",
								class: "paddRight"
							}).appendTo("#qualiTableRow_" + l);
							var o = H.default.getColorFieldOrLine(n.values[e].color, void 0, !1, parseInt(i));
							o.attr("id", "qualiColorField_" + e), W.default.colorFields["qualiColorField_" + e] = {
								dataset_ida: i,
								anyOtherValue: n.values[e].value
							}, o.attr("data-dataset-ida", parseInt(i)), o.attr("data-any-other-value", n.values[e].value.toString()), r.qualiShownValues.push(n.values[e].value), U("#qualiTableCell_" + l + "_" + a).append(o), U("<td/>", {
								id: "qualiTableCell_" + l + "_" + ++a,
								style: "vertical-align: top; padding-top: 0.2em;"
							}).appendTo("#qualiTableRow_" + l), U("#qualiTableCell_" + l + "_" + a).append(t)
						}
					});
					var a = 0;
					if (!0 !== this.noDataAndPrivacy.quali.noData && !0 !== this.noDataAndPrivacy.quali.privacy && !0 !== this.noDataAndPrivacy.quali.remainder || (U("<tr/>", {
							id: "qualiTableRow_" + ++l,
							class: "tableRow"
						}).appendTo(U("#qualiTable")), U("<td/>", {
							id: "qualiTableCell_" + l + "_" + ++a,
							colspan: 4,
							class: "center",
							height: H.default.colorFieldHeight / 4 + "em"
						}).appendTo(U("#qualiTableRow_" + l)), U("#qualiTableCell_" + l + "_" + a).append("")), !0 === this.noDataAndPrivacy.quali.remainder) {
						a = 0;
						U("<tr/>", {
							id: "qualiTableRow_" + ++l,
							class: "tableRow"
						}).appendTo(U("#qualiTable")), U("<td/>", {
							id: "qualiTableCell_" + l + "_" + ++a,
							style: "vertical-align: top; padding-top: 0.2em;"
						}).appendTo(U("#qualiTableRow_" + l)), (o = H.default.getColorFieldOrLine(n.spec.fill_color_remainder)).attr("id", "qualiColorField_remainder"), W.default.colorFields.qualiColorField_remainder = {
							dataset_ida: i,
							anyOtherValue: H.default.getColorRemainderValue(),
							shownValues: this.qualiShownValues
						}, o.attr("data-dataset-ida", parseInt(i)), o.attr("data-any-other-value", H.default.getColorRemainderValue()), o.attr("data-shown-values", this.qualiShownValues), U("#qualiTableCell_" + l + "_" + a).append(o), U("<td/>", {
							id: "qualiTableCell_" + l + "_" + ++a,
							style: "vertical-align: top; padding-top: 0.2em;"
						}).appendTo(U("#qualiTableRow_" + l)), U("#qualiTableCell_" + l + "_" + a).append(n.languages[t.ida].remainder)
					}
					if (!0 === this.noDataAndPrivacy.quali.noData) {
						a = 0;
						U("<tr/>", {
							id: "qualiTableRow_" + ++l,
							class: "tableRow"
						}).appendTo(U("#qualiTable")), U("<td/>", {
							id: "qualiTableCell_" + l + "_" + ++a,
							style: "vertical-align: top; padding-top: 0.2em;"
						}).appendTo(U("#qualiTableRow_" + l));
						var o = H.default.getColorFieldOrLine(v.default.current.properties.nodatacolor);
						W.default.colorFields.qualiColorField_noData = {
							dataset_ida: i,
							anyOtherValue: null
						}, o.attr("id", "qualiColorField_noData"), o.attr("data-dataset-ida", parseInt(i)), o.attr("data-any-other-value", null), U("#qualiTableCell_" + l + "_" + a).append(o), U("<td/>", {
							id: "qualiTableCell_" + l + "_" + ++a
						}).appendTo(U("#qualiTableRow_" + l)), U("#qualiTableCell_" + l + "_" + a).append(H.default.getNoData(!1, !0))
					}
					if (!0 === this.noDataAndPrivacy.quali.privacy) {
						a = 0;
						U("<tr/>", {
							id: "qualiTableRow_" + ++l,
							class: "tableRow"
						}).appendTo(U("#qualiTable")), U("<td/>", {
							id: "qualiTableCell_" + l + "_" + ++a,
							style: "vertical-align: top; padding-top: 0.2em;"
						}).appendTo(U("#qualiTableRow_" + l)), (o = H.default.getColorFieldOrLine(v.default.current.properties.nodatacolor)).attr("id", "qualiColorField_privacy"), W.default.colorFields.qualiColorField_privacy = {
							dataset_ida: i,
							anyOtherValue: !1
						}, o.attr("data-dataset-ida", parseInt(i)), o.attr("data-any-other-value", !1), U("#qualiTableCell_" + l + "_" + a).append(o), U("<td/>", {
							id: "qualiTableCell_" + l + "_" + ++a
						}).appendTo(U("#qualiTableRow_" + l)), U("#qualiTableCell_" + l + "_" + a).append(H.default.getPrivacy(!1, !0))
					}
					return e += U("#qualiTable")[0].outerHTML, U("#invisibleWorker").html(null), !1 === H.default.isNull(n.languages[t.ida].comment) && (e += this.getComment(n.languages[t.ida].comment)), e
				}, e.prototype.showChoro = function (e, t) {
					Y.bajoodooLog("ThematicalMapLegend.showChoro()");
					var a, o = B.default.localization;
					!0 !== t && (t = !1);
					var u, c = Number(Object.keys(e.datasets)[0]),
						d = 1 === e.spec.direction,
						p = parseInt(e.spec.decimal_places),
						f = e.spec.colours.split(","),
						h = e.spec.class_limits.split(",");
					u = !1 === H.default.isUndefined(e.classlabels.languages) ? e.classlabels.languages[o.ida] : new Array;
					var n = parseInt(e.vis_data_info),
						r = 1 === e.info_without_pseudo,
						i = 1 === e.show_info_value;
					U.each(h, function (e, t) {
						h[e] = Number(t)
					}), !1 === d ? h = h.reverse() : f = f.reverse(), a = !0 === t ? this.getSubTitle(e.languages[o.ida].name) : this.getTitle(e.languages[o.ida].name, z.VISUALIZATION_TYPE_AREAL), U("<table/>", {
						id: "choroTable",
						class: "choroTable"
					}).appendTo("#invisibleWorker"), U("<colgroup/>", {
						id: "choroTableColGroup",
						class: "colorFieldTableGroup"
					}).appendTo(U("#choroTable")), U("<col/>").attr("width", "*").appendTo(U("#choroTableColGroup")), U("<col/>").attr("width", "*").appendTo(U("#choroTableColGroup")), U("<col/>").attr("width", "*").appendTo(U("#choroTableColGroup")), U("<col/>").attr("width", "*").appendTo(U("#choroTableColGroup")), U("<col/>").attr("width", "*").appendTo(U("#choroTableColGroup"));
					var m = 0;
					U.each(f, function (e, t) {
						var a = 0,
							o = null,
							n = null,
							r = null,
							i = null,
							l = null;
						0 !== m && m !== f.length - 1 ? !1 === d ? (o = h[e], n = H.default.showValue(h[e], p, !1)) : !0 === d && (o = h[e - 1], n = H.default.showValue(h[e - 1], p, !1)) : (o = null, n = ""), 0 === m ? (r = h[e], i = H.default.showValue(h[e], p, !1)) : m === f.length - 1 ? (r = h[e - 1], i = H.default.showValue(h[e - 1], p, !1)) : !1 === d ? (r = H.default.decreaseValue(h[e - 1], p), i = H.default.showValue(H.default.decreaseValue(h[e - 1], p), p, !1)) : !0 === d && (r = H.default.decreaseValue(h[e], p), i = H.default.showValue(H.default.decreaseValue(h[e], p), p, !1)), l = 0 === m && !1 === d || m === f.length - 1 && !0 === d ? (o = r, r = null, "≥") : m === f.length - 1 && !1 === d || 0 === m && !0 === d ? "<" : "–", U("<tr/>", {
							id: "choroTableRow_" + ++m,
							class: "tableRow"
						}).appendTo(U("#choroTable")), U("<td/>", {
							id: "choroTableCell_" + m + "_" + ++a,
							style: "vertical-align: top; padding-top: 0.2em;"
						}).appendTo(U("#choroTableRow_" + m));
						var s = H.default.getColorFieldOrLine(t);
						s.attr("id", "choroColorField_" + e), W.default.colorFields["choroColorField_" + e] = {
							dataset_ida: c,
							minValue: o,
							maxValue: r,
							anyOtherValue: void 0
						}, s.attr("data-dataset-ida", c), s.attr("data-min-value", o), s.attr("data-max-value", r), s.removeAttr("data-any-other-value"), U("#choroTableCell_" + m + "_" + a).append(s), !1 === d && !1 === H.default.isUndefined(u[m]) || !0 === d && !1 === H.default.isUndefined(u[f.length - (m - 1)]) ? (U("<td/>", {
							id: "choroTableCell_" + m + "_" + ++a,
							class: "paddLeft",
							colspan: 4
						}).appendTo(U("#choroTableRow_" + m)), U("#choroTableCell_" + m + "_" + a).append(!1 === d ? u[m].name : u[f.length - (m - 1)].name)) : (U("<td/>", {
							id: "choroTableCell_" + m + "_" + ++a,
							class: "right paddLeft"
						}).appendTo(U("#choroTableRow_" + m)), U("#choroTableCell_" + m + "_" + a).append(n), U("<td/>", {
							id: "choroTableCell_" + m + "_" + ++a,
							class: "center paddCenter"
						}).appendTo(U("#choroTableRow_" + m)), U("#choroTableCell_" + m + "_" + a).append(l), U("<td/>", {
							id: "choroTableCell_" + m + "_" + ++a,
							class: "right paddRight"
						}).appendTo(U("#choroTableRow_" + m)), U("#choroTableCell_" + m + "_" + a).append(i), U("<td/>", {
							id: "choroTableCell_" + m + "_" + ++a
						}).appendTo(U("#choroTableRow_" + m)))
					}), a += U("#choroTable")[0].outerHTML, U("#invisibleWorker").html(null);
					var l = 0;
					if (!1 === t && (!0 === this.noDataAndPrivacy.choro.noData || !0 === this.noDataAndPrivacy.choro.privacy) || !0 === t && (!0 === this.noDataAndPrivacy.sym.combined.noData || !0 === this.noDataAndPrivacy.sym.combined.privacy)) {
						if (U("<table/>", {
								id: "choroAdditionalTable",
								class: "choroTable"
							}).appendTo("#invisibleWorker"), U("<colgroup/>", {
								id: "choroAdditionalTableColGroup",
								class: "colorFieldTableGroup"
							}).appendTo(U("#choroAdditionalTable")), U("<col/>").attr("width", "*").appendTo(U("#choroAdditionalTableColGroup")), U("<col/>").attr("width", "*").appendTo(U("#choroAdditionalTableColGroup")), U("<col/>").attr("width", "*").appendTo(U("#choroAdditionalTableColGroup")), U("<col/>").attr("width", "*").appendTo(U("#choroAdditionalTableColGroup")), U("<col/>").attr("width", "*").appendTo(U("#choroAdditionalTableColGroup")), U("<tr/>", {
								id: "choroTableRow_" + ++m
							}).appendTo(U("#choroAdditionalTable")), U("<td/>", {
								id: "choroTableCell_" + m + "_" + ++l,
								colspan: 5,
								class: "center",
								height: H.default.colorFieldHeight / 4 + "em"
							}).appendTo(U("#choroTableRow_" + m)), U("#choroTableCell_" + m + "_" + l).append(""), !1 === t && !0 === this.noDataAndPrivacy.choro.noData || !0 === t && !0 === this.noDataAndPrivacy.sym.combined.noData) {
							l = 0;
							U("<tr/>", {
								id: "choroTableRow_" + ++m
							}).appendTo(U("#choroAdditionalTable")), U("<td/>", {
								id: "choroTableCell_" + m + "_" + ++l,
								style: "vertical-align: top; padding-top: 0.2em;"
							}).appendTo(U("#choroTableRow_" + m)), (s = H.default.getColorFieldOrLine(v.default.current.properties.nodatacolor)).attr("data-any-other-value", !1), s.attr("data-dataset-ida", c), s.attr("id", "choroColorField_noData"), W.default.colorFields.choroColorField_noData = {
								dataset_ida: c,
								anyOtherValue: null
							}, U("#choroTableCell_" + m + "_" + l).append(s), U("<td/>", {
								id: "choroTableCell_" + m + "_" + ++l,
								class: "paddLeft",
								colspan: 4
							}).appendTo(U("#choroTableRow_" + m)), U("#choroTableCell_" + m + "_" + l).append(H.default.getNoData(!1, !0))
						}
						if (!1 === t && !0 === this.noDataAndPrivacy.choro.privacy || !0 === t && !0 === this.noDataAndPrivacy.sym.combined.privacy) {
							var s;
							l = 0;
							U("<tr/>", {
								id: "choroTableRow_" + ++m
							}).appendTo(U("#choroAdditionalTable")), U("<td/>", {
								id: "choroTableCell_" + m + "_" + ++l,
								style: "vertical-align: top; padding-top: 0.2em;"
							}).appendTo(U("#choroTableRow_" + m)), (s = H.default.getColorFieldOrLine(v.default.current.properties.nodatacolor)).attr("data-any-other-value", null), s.attr("data-dataset-ida", c), s.attr("id", "choroColorField_privacy"), W.default.colorFields.choroColorField_privacy = {
								dataset_ida: c,
								anyOtherValue: !1
							}, U("#choroTableCell_" + m + "_" + l).append(s), U("<td/>", {
								id: "choroTableCell_" + m + "_" + ++l,
								class: "paddLeft",
								colspan: 4
							}).appendTo(U("#choroTableRow_" + m)), U("#choroTableCell_" + m + "_" + l).append(H.default.getPrivacy(!1, !0))
						}
						a += U("#choroAdditionalTable")[0].outerHTML, U("#invisibleWorker").html(null)
					}
					if (!0 === i) {
						var g = H.default.getInfoLabel(n),
							y = H.default.getInfoValueString(c, r, p);
						U("<div/>", {
							id: "choroInfo",
							class: "infoLabelAndValue"
						}).appendTo("#invisibleWorker"), U("<span/>", {
							text: g + ": ",
							style: "font-weight: 500"
						}).appendTo(U("#choroInfo")), U("#choroInfo").append(y), a += U("#choroInfo")[0].outerHTML, U("#invisibleWorker").html(null)
					}
					return !1 === H.default.isNull(e.languages[o.ida].comment) && (a += this.getComment(e.languages[o.ida].comment)), a
				}, e.prototype.getLegendTitle = function (e) {
					return '\n            <h2 \n                class="legendTitle"\n            >' + e + "</h2>"
				}, e.prototype.handleCheckboxChange = function (e) {
					var t = s.renderToString(l.createElement(i.EyeOffOutline, {
						className: "visEye",
						"data-value": "sym"
					}));
					U(e.currentTarget).html(U(t).html());
					var a = U(e.currentTarget).attr("data-value");
					if (W.default["set" + r.upperFirst(a) + "State"](!W.default["is" + r.upperFirst(a) + "On"]), a == z.VISUALIZATION_TYPE_SYMBOL && U("#symbolContainer").css("visibility", W.default.isSymbolOn ? "visible" : "hidden"), a == z.VISUALIZATION_TYPE_AREAL) {
						var o = v.default.current.geometry,
							n = W.default.isArealOn;
						U("#geo_" + o).find("path").each(function () {
							U(this).attr("fill-opacity", n ? H.default.arealOpacity : 0)
						})
					}
					U("#" + a + "Legend").fadeTo(500, W.default["is" + r.upperFirst(a) + "On"] ? 1 : .4), W.default.emitter.emit(z.WINDOW_RESIZED)
				}, e.prototype.getTitle = function (e, t) {
					return s.renderToString(l.createElement(a.Typography, {
						variant: "h6",
						color: "inherit",
						className: "legendTitle"
					}, W.default["is" + r.upperFirst(t) + "On"] ? l.createElement(i.EyeOutline, {
						className: "visEye",
						"data-value": t,
						id: "toggle_" + t
					}) : l.createElement(i.EyeOffOutline, {
						className: "visEye",
						"data-value": t,
						id: "toggle_" + t
					}), e))
				}, e.prototype.getSubTitle = function (e) {
					return s.renderToString(l.createElement(a.Typography, {
						variant: "subtitle1",
						className: "legendSubTitle",
						component: "h3"
					}, e))
				}, e.prototype.getComment = function (e) {
					return s.renderToString(l.createElement(a.Typography, {
						className: "legendComment"
					}, e))
				}, e.prototype.getInfo = function (e) {
					return s.renderToString(l.createElement(a.Typography, {
						className: "legendInfo"
					}, e))
				}, e.prototype.getDividerLine = function () {
					return s.renderToString(l.createElement(a.Divider, null))
				}, e
			}();
			t.ThematicalMapLegend = e;
			var Z = {
				browser: null,
				version: null,
				ua: null,
				isMobile: null,
				isAndroid: null,
				isIOS: null,
				init: function () {
					this.ua = navigator.userAgent.toLowerCase(), this.isMobile = this.isMobileTest.any(), this.isAndroid = this.isMobileTest.Android(), this.isIOS = this.isMobileTest.iOS(), this.browser = this.searchString(this.dataBrowser) || "Other", this.version = this.searchVersion(navigator.userAgent) || this.searchVersion(navigator.appVersion) || "Unknown"
				},
				searchString: function (e) {
					for (var t = 0; t < e.length; t++) {
						var a = e[t].string;
						if (this.versionSearchString = e[t].subString, -1 !== a.indexOf(e[t].subString)) return e[t].identity
					}
				},
				searchVersion: function (e) {
					var t = e.indexOf(this.versionSearchString);
					if (-1 !== t) {
						var a = e.indexOf("rv:");
						return "Trident" === this.versionSearchString && -1 !== a ? parseFloat(e.substring(a + 3)) : parseFloat(e.substring(t + this.versionSearchString.length + 1))
					}
				},
				dataBrowser: [{
					string: navigator.userAgent,
					subString: "Edge",
					identity: "MS Edge"
				}, {
					string: navigator.userAgent,
					subString: "Chrome",
					identity: "Chrome"
				}, {
					string: navigator.userAgent,
					subString: "MSIE",
					identity: "Explorer"
				}, {
					string: navigator.userAgent,
					subString: "Trident",
					identity: "Explorer"
				}, {
					string: navigator.userAgent,
					subString: "Firefox",
					identity: "Firefox"
				}, {
					string: navigator.userAgent,
					subString: "Safari",
					identity: "Safari"
				}, {
					string: navigator.userAgent,
					subString: "Opera",
					identity: "Opera"
				}],
				isMobileTest: {
					Android: function () {
						return !H.default.isNull(Z.ua.match(/android/))
					},
					BlackBerry: function () {
						return !H.default.isNull(Z.ua.match(/blackberry/))
					},
					iOS: function () {
						return !H.default.isNull(Z.ua.match(/iphone|ipad|ipod/))
					},
					Opera: function () {
						return !H.default.isNull(Z.ua.match(/opera mini/))
					},
					Windows: function () {
						return !H.default.isNull(Z.ua.match(/iemobile/)) || !H.default.isNull(Z.ua.match(/wpdesktop/))
					},
					any: function () {
						return this.Android() || this.BlackBerry() || this.iOS() || this.Opera() || this.Windows()
					}
				}
			};
			Z.init()
		}).call(this, o(31))
	},
	767: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
			return (o = Object.setPrototypeOf || {
					__proto__: []
				}
				instanceof Array && function (e, t) {
					e.__proto__ = t
				} || function (e, t) {
					for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
				})(e, t)
		}, function (e, t) {
			function a() {
				this.constructor = e
			}
			o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
		});
		this && this.__assign;
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var r = a(1),
			i = a(28),
			l = a(151),
			s = a(17),
			u = a(19),
			c = a(20),
			d = a(29),
			p = a(83),
			f = a(10);
		var h = i.withStyles(function (e) {
			var t = e.typography,
				a = (e.mixins, e.spacing, e.palette);
			e.shadows;
			return {
				root: {
					width: "100%"
				},
				heading: {
					fontSize: t.pxToRem(15),
					flexBasis: "100%",
					flexShrink: 0
				},
				secondaryHeading: {
					fontSize: t.pxToRem(15),
					color: a.text.secondary
				}
			}
		})(d.observer(function (e) {
			function t() {
				var o = null !== e && e.apply(this, arguments) || this;
				return o.state = {
					expanded: ""
				}, o.handleChange = function (a) {
					return function (e, t) {
						o.setState({
							expanded: !!t && a
						}), setTimeout(function () {
							f.default.modalTime = Date.now()
						}, 1e3)
					}
				}, o
			}
			return n(t, e), t.prototype.componentWillMount = function () {
				this.p = u.default.getProject(), this.m = c.default.current, this.l = s.default.localization, this.modes = p.default.current.data.properties.modes.glossary.elements["@modes"].split(",")
			}, t.prototype.render = function () {
				var t = this,
					a = this.props.classes,
					o = this.state.expanded;
				return r.createElement("div", null, r.createElement(i.Typography, {
					variant: "h6",
					id: "share-title"
				}, this.l.messages.TOOLBAR_GLOSSARY), r.createElement("hr", null), this.modes.map(function (e) {
					return r.createElement(i.ExpansionPanel, {
						expanded: o === e,
						onChange: t.handleChange(e),
						key: e
					}, r.createElement(i.ExpansionPanelSummary, {
						expandIcon: r.createElement(l.default, null)
					}, r.createElement(i.Typography, {
						className: a.heading
					}, t.l.messages["GLOSSARY_" + e.toUpperCase()])), r.createElement(i.ExpansionPanelDetails, null, r.createElement(i.Typography, {
						className: a.heading,
						key: "formatted" + e,
						dangerouslySetInnerHTML: {
							__html: t.p.currentLanguage()[e]
						}
					})))
				}))
			}, t
		}(r.Component)));
		t.default = h
	},
	768: function (e, f, h) {
		"use strict";
		(function (t) {
			var o, n = this && this.__extends || (o = function (e, t) {
					return (o = Object.setPrototypeOf || {
							__proto__: []
						}
						instanceof Array && function (e, t) {
							e.__proto__ = t
						} || function (e, t) {
							for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
						})(e, t)
				}, function (e, t) {
					function a() {
						this.constructor = e
					}
					o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
				}),
				r = this && this.__decorate || function (e, t, a, o) {
					var n, r = arguments.length,
						i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
					if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
					else
						for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
					return 3 < r && i && Object.defineProperty(t, a, i), i
				};
			Object.defineProperty(f, "__esModule", {
				value: !0
			});
			var i = h(5),
				l = h(1),
				s = h(10),
				u = h(381),
				c = h(382),
				d = h(29),
				e = h(28),
				p = h(18),
				a = function (a) {
					function e(e) {
						var t = a.call(this, e) || this;
						return t.state = {}, t
					}
					return n(e, a), e.prototype.componentDidMount = function () {
						var e = this;
						s.default.emitter.on(p.WINDOW_RESIZED, function () {
							e.updateBottomBarHeight()
						}), this.updateBottomBarHeight()
					}, e.prototype.updateBottomBarHeight = function () {
						s.default.bottomBarHeight = s.default.vars.isEmbedded ? 0 : void 0 !== t("#bottomBar").outerHeight() ? t("#bottomBar").outerHeight() : 0
					}, e.prototype.render = function () {
						var e = this.props.classes;
						return s.default.isBottomLegend ? null : l.createElement("div", {
							className: "d-block container-fluid"
						}, l.createElement("div", {
							className: i(e.bottombar, "row"),
							id: "bottomBar"
						}, l.createElement("div", {
							className: "col-12 col-md-6"
						}, l.createElement(u.default, {
							location: "bottom"
						})), l.createElement("div", {
							className: i(e.sources, "col-12 col-md-6")
						}, l.createElement(c.default, {
							location: "bottom"
						}))))
					}, e = r([d.observer], e)
				}(l.Component);
			f.default = e.withStyles(function (e) {
				return {
					bottombar: {
						position: "fixed",
						backgroundColor: "#cccccc",
						padding: "4px 10px",
						bottom: 0,
						width: "100%",
						zIndex: 1202
					},
					copyright: {},
					sources: {
						textAlign: "right"
					}
				}
			})(a)
		}).call(this, h(31))
	},
	769: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
				return (o = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			}),
			r = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var i = a(1),
			l = a(28),
			s = a(29),
			u = a(20),
			c = a(17),
			d = a(19),
			p = a(770),
			f = a(10),
			h = a(18),
			m = a(58),
			g = a(48),
			y = a(378),
			v = a(40),
			b = a(30),
			_ = function (a) {
				function e(e) {
					var t = a.call(this, e) || this;
					return t.timerRunning = !1, t.state = {
						timeLeft: f.default.vars.liveMapRefreshTime,
						show: !1
					}, t
				}
				return n(e, a), e.prototype.componentWillMount = function () {
					var e = this;
					this.checkLiveMapState(), f.default.emitter.on(h.THEMATICALMAP_LOADED, function () {
						e.checkLiveMapState()
					}), f.default.emitter.on(h.LIVE_MAP_STOP_CHECKING, function () {
						e.stopChecking()
					})
				}, e.prototype.checkLiveMapState = function () {
					var e = u.default.current,
						t = void 0 !== e && e.hasProperties() && 1 === e.properties.is_live_map;
					this.setState({
						show: t
					}), t ? this.startTimer() : this.stopTimer()
				}, e.prototype.startTimer = function () {
					var e = this;
					this.timerRunning || f.default.animationStarted || (this.setState({
						timeLeft: f.default.vars.liveMapRefreshTime
					}), this.timerRunning = !0, this.interval = setInterval(function () {
						e.updateInfo()
					}, 1e3))
				}, e.prototype.stopTimer = function () {
					clearInterval(this.interval), this.timerRunning = !1
				}, e.prototype.stopChecking = function () {
					this.setState({
						show: !1
					}), this.stopTimer()
				}, e.prototype.onTimer = function () {}, e.prototype.onTimerComplete = function () {}, e.prototype.check = function () {
					var t = this,
						e = u.default.current,
						a = d.default.getProject();
					new Date;
					if (void 0 !== e) {
						var o = e.checkTimeStamp;
						this.timerRunning && 0 === this.state.timeLeft && (f.default.emitter.once(h.LIVE_MAP_DATA_AVAILABLE, function (e) {
							e.success && t.updateMap()
						}), p.default.load(e.ida, a.project, o))
					}
				}, e.prototype.updateMap = function () {
					b.bajoodooLog("livemap", "update");
					var e = u.default.current;
					m.default.getCurrentNode().parents.join("_");
					if (void 0 !== e) {
						var t = e.ida,
							a = e.getDatasetIds();
						y.default.removeMap(t), u.default.unsetCurrent(), a.map(function (e) {
							g.default.removeDataset(e)
						}), u.default.loadThematicalMap(t)
					}
				}, e.prototype.updateInfo = function () {
					this.check(), this.setState({
						timeLeft: this.state.timeLeft <= 0 ? f.default.vars.liveMapRefreshTime : this.state.timeLeft - 1
					})
				}, e.prototype.showInfo = function () {
					var e = u.default.current,
						t = [];
					if (void 0 !== e && e.hasProperties() && 1 === e.properties.is_live_map) {
						e.properties.is_live_active || t.push(c.default.localization.messages.LIVE_MAP_NO_DATA_YET);
						var a = c.default.localization.messages.LIVE_MAP_ACTUALIZATION_INFO.split("#timeLeft#").join(this.state.timeLeft);
						return t.push(a), i.createElement("div", null, t.map(function (e, t) {
							return i.createElement(l.Typography, {
								key: "lm" + t,
								variant: "caption"
							}, e)
						}))
					}
					return null
				}, e.prototype.calculateBar = function () {
					var e = f.default.vars.liveMapRefreshTime / 100;
					return 100 - this.state.timeLeft / e
				}, e.prototype.render = function () {
					var e = this.props.classes;
					return i.createElement(l.Snackbar, {
						open: this.state.show
					}, i.createElement(l.Paper, null, i.createElement(l.LinearProgress, {
						variant: "determinate",
						value: this.calculateBar(),
						color: "secondary"
					}), i.createElement("div", {
						className: e.paper
					}, i.createElement(l.Typography, null, i.createElement(v.RadioTower, null), c.default.localization.messages.LIVE_MAP_WINDOW_TITLE), this.showInfo())))
				}, e = r([s.observer], e)
			}(i.Component);
		t.default = l.withStyles(function (e) {
			return {
				paper: {
					padding: 20
				}
			}
		})(_)
	},
	770: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var r = a(18),
			i = a(39),
			l = a(10),
			o = function () {
				function e() {
					this.pending = !1, this.queue = []
				}
				return e.prototype.load = function (e, t, a) {
					var o = this;
					if (!this.pending) {
						this.pending = !0;
						i.default.create({
							baseURL: _WWW,
							responseType: "json"
						});
						var n = {
							map_ida: 1 * e,
							project_ida: t + "",
							project_type: "json",
							timestamp: a
						};
						i.default.post(l.default.paths._WWW + "core/checkUpdate.php?" + Date.now(), n).then(function (e) {
							!0 === e.data.success && (l.default.guitimestamp = e.data.timestamp), o.pending = !1, l.default.emitter.emit(r.LIVE_MAP_DATA_AVAILABLE, e.data)
						}).catch(function (e) {
							l.default.emitter.emit(r.LIVE_MAP_DATA_AVAILABLE, {
								success: !1
							}), o.pending = !1
						})
					}
				}, e
			}();
		t.default = new o
	},
	771: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
				return (o = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			}),
			r = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var i = a(1),
			l = a(10),
			s = a(185),
			u = a(186),
			c = a(187),
			d = a(145),
			p = a(29);
		a(85);
		var f = function (e) {
			function t() {
				return null !== e && e.apply(this, arguments) || this
			}
			return n(t, e), t.prototype.render = function () {
				return l.default.loadingState && l.default.isLoadingDisplayed && l.default.loadingEvent && 0 < l.default.loadingItems.length ? i.createElement("div", null, i.createElement(s.default, {
					fullScreen: !1,
					disableBackdropClick: !0,
					disableEscapeKeyDown: !0,
					open: !0,
					"aria-labelledby": "responsive-dialog-title",
					id: "loader" + l.default.loadingEvent,
					transitionDuration: {
						enter: 1500,
						exit: 1e3
					}
				}, i.createElement(u.default, null, l.default.loadingItems.map(function (e, t) {
					return i.createElement("div", {
						key: e.type + t
					}, -1 < [null, void 0].indexOf(e.percent) ? null : i.createElement(d.default, {
						color: "secondary",
						variant: "determinate",
						value: void 0 === e.percent ? 0 : e.percent
					}), i.createElement(c.default, {
						variant: "caption",
						align: "center"
					}, e.message))
				})))) : null
			}, t = r([p.observer], t)
		}(i.Component);
		t.default = f
	},
	772: function (e, y, v) {
		"use strict";
		(function (o) {
			var n, t = this && this.__extends || (n = function (e, t) {
				return (n = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				n(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			});
			Object.defineProperty(y, "__esModule", {
				value: !0
			});
			var r = v(5),
				i = v(40),
				l = v(1),
				s = v(10),
				u = v(17),
				c = v(773),
				d = v(20),
				p = v(30),
				f = v(28),
				e = v(29),
				h = v(18),
				a = v(41),
				m = v(85),
				g = a.withStyles(function (e) {
					e.typography, e.mixins, e.spacing, e.palette, e.shadows;
					return {
						root: {
							flexGrow: 1
						},
						tabRoot: {
							minWidth: "auto"
						},
						mapLegendDrawer: {
							maxHeight: "100%",
							overflow: "hidden"
						},
						drawerPaper: {
							width: 350
						},
						chevronButton: {
							display: "inline-block",
							top: s.default.appHeaderHeight,
							position: "absolute",
							left: 0,
							padding: 0,
							minWidth: "auto"
						},
						mapLegendDrawerBottom: {
							position: "relative",
							top: window.innerHeight,
							width: "100%",
							backgroundColor: "#FFFFFF",
							paddingTop: 10
						},
						gotoMapButtonContainer: {
							backgroundColor: m.superlightgrey,
							textAlign: "right",
							padding: 4
						},
						mapContent: {
							overflow: "hidden"
						},
						buttonContainer: {
							position: "absolute",
							top: s.default.appHeaderHeight + 10,
							right: 10
						},
						legendButton: {
							margin: 0,
							minWidth: "auto",
							display: "block",
							lineHeight: "1em"
						},
						infoButton: {
							margin: 0,
							marginBottom: 2,
							minWidth: "auto",
							display: "block",
							lineHeight: "1em"
						},
						mapButton: {
							margin: 0,
							minWidth: "auto",
							padding: 8,
							lineHeight: "1em"
						},
						displayBlock: {
							display: "block"
						},
						displayTable: {
							display: "table"
						},
						displayNone: {
							display: "none"
						}
					}
				}, {
					withTheme: !0
				})(e.observer(function (a) {
					function e(e) {
						var t = a.call(this, e) || this;
						return t.handleTabChange = function (e, t) {
							0 === t ? (s.default.isMapContentVisible = !0, s.default.isMapLegendVisible = !1) : 1 === t && (s.default.isMapLegendVisible = !0, s.default.isMapContentVisible = !1)
						}, t.updateLegendLocation(), t.state = {
							scrollLegendUp: !1
						}, s.default.emitter.on(h.LEGEND_DONE, function () {
							t.updateLegendWidth(), t.updateLegendLocation()
						}), s.default.emitter.on(h.WINDOW_RESIZED, function () {
							return t.updateLegendLocation()
						}), t
					}
					return t(e, a), e.prototype.toggleDrawer = function (e, t) {
						s.default.isBottomLegend && o("html, body").animate({
							scrollTop: e ? o("#bottomContainer").offset().top : 0
						}, 500), s.default.mapLegendDrawer = e, s.default.isMapContentVisible = t, s.default.isMapLegendVisible = !t, e && o("#mapLegendDrawer").css("transform", "none")
					}, e.prototype.componentDidMount = function () {}, e.prototype.componentDidUpdate = function () {
						s.default.isMapLegendVisible && this.updateLegendLocation()
					}, e.prototype.updateLegendWidth = function () {
						void 0 !== this.tmlRight && null !== this.tmlRight && (s.default.legendWidth = this.tmlRight.clientWidth)
					}, e.prototype.updateLegendLocation = function () {
						this.props.theme;
						s.default.vars.isExtraSmall || s.default.vars.isSmall || s.default.vars.isMedium ? (o("#bottomContainer").append(o("#contentAndLegend")), s.default.isBottomLegend = !0, o("#bottomContainer").css("top", window.innerHeight)) : (o("#rightContainer").append(o("#contentAndLegend")), s.default.isBottomLegend = !1, s.default.mapLegendDrawer || o("#mapLegendDrawer").css("transform", "translateX(100vw) translateX(-" + window.innerWidth + "px)")), o("#rightContainer").css("padding-top", s.default.appHeaderHeight), o("#rightChevron").css("top", s.default.appHeaderHeight), s.default.legendContainerHeight = window.innerHeight - s.default.appHeaderHeight - 60;
						var e = d.default.current;
						void 0 !== e && e.hasProperties() && 1 === e.properties.is_live_map && o("#bottomContainer").css("padding-bottom", 100)
					}, e.prototype.renderChevron = function () {
						return s.default.isBottomLegend ? l.createElement("div", null, l.createElement(i.ChevronDown, null)) : s.default.mapLegendDrawer ? l.createElement("div", null, l.createElement(i.ChevronRight, null)) : l.createElement("div", null, l.createElement(i.ChevronLeft, null))
					}, e.prototype.render = function () {
						var t = this;
						p.bajoodooLog("MapLegendDrawer.render()");
						var e = this.props,
							a = e.classes,
							o = (e.theme, d.default.current, s.default.isLiveMap && !s.default.isLiveActive);
						return l.createElement("div", null, l.createElement("div", {
							className: a.buttonContainer,
							style: {
								top: s.default.appHeaderHeight + 10
							}
						}, s.default.isBottomLegend || !s.default.mapLegendDrawer ? l.createElement(f.Fab, {
							size: "small",
							color: "primary",
							"aria-label": "info",
							className: a.infoButton,
							onClick: function () {
								return t.toggleDrawer(s.default.isBottomLegend || !s.default.mapLegendDrawer, !0)
							}
						}, l.createElement(i.InformationOutline, null)) : null, s.default.isBottomLegend || !s.default.mapLegendDrawer ? l.createElement(f.Fab, {
							size: "small",
							disabled: o,
							color: "primary",
							"aria-label": "legend",
							className: a.legendButton,
							onClick: function () {
								return t.toggleDrawer(s.default.isBottomLegend || !s.default.mapLegendDrawer, !1)
							}
						}, l.createElement(i.MapLegend, null)) : null), l.createElement(f.Hidden, {
							smDown: !0,
							implementation: "css"
						}, l.createElement(f.Drawer, {
							variant: "persistent",
							anchor: "right",
							open: s.default.mapLegendDrawer,
							className: a.mapLegendDrawer,
							id: "mapLegendDrawer",
							classes: {
								paper: a.drawerPaper
							}
						}, l.createElement("div", {
							id: "rightContainer",
							ref: function (e) {
								return t.rightContainer = e
							},
							key: "rightContainer"
						}, l.createElement("div", {
							id: "contentAndLegend"
						}, l.createElement("div", null, !s.default.isBottomLegend && l.createElement("div", {
							className: a.chevronButton,
							id: "rightChevron"
						}, l.createElement(f.Button, {
							style: {
								minWidth: "auto",
								marginTop: 3
							},
							onClick: function () {
								return t.toggleDrawer(!s.default.mapLegendDrawer, !0)
							}
						}, l.createElement(i.ChevronRight, null))), l.createElement("div", null, l.createElement(f.Tabs, {
							style: {
								paddingLeft: s.default.isBottomLegend ? 0 : 40,
								paddingRight: s.default.isBottomLegend ? 50 : 0
							},
							value: s.default.isMapContentVisible ? 0 : 1,
							onChange: this.handleTabChange
						}, l.createElement(f.Tab, {
							classes: {
								root: a.tabRoot
							},
							label: u.default.localization.messages.MAP_INFORMATION
						}), l.createElement(f.Tab, {
							disabled: o,
							classes: {
								root: a.tabRoot
							},
							label: u.default.localization.messages.BUTTON_LEGEND
						})))), s.default.isMapContentVisible && l.createElement("div", null, l.createElement(c.default, null)), l.createElement("div", {
							id: "tMLContainer",
							key: "tMLContainer",
							style: {
								height: s.default.isBottomLegend ? "auto" : s.default.legendContainerHeight,
								overflowY: "auto"
							},
							className: r(a.mapContent, s.default.isMapLegendVisible ? a.displayBlock : a.displayNone)
						}, l.createElement("div", {
							id: "tML",
							ref: function (e) {
								return t.tmlRight = e
							}
						})))))), l.createElement(f.Hidden, {
							mdUp: !0,
							implementation: "css"
						}, l.createElement("div", {
							id: "bottomContainer",
							ref: function (e) {
								return t.bottomContainer = e
							},
							key: "bottomContainer",
							className: a.mapLegendDrawerBottom
						}, l.createElement("div", {
							className: a.gotoMapButtonContainer
						}, l.createElement(f.Fab, {
							size: "small",
							color: "primary",
							"aria-label": "gotoMap",
							className: a.mapButton,
							onClick: function () {
								return t.toggleDrawer(!1, !1)
							}
						}, l.createElement(i.ChevronUp, null))))))
					}, e
				}(l.Component)));
			y.default = g
		}).call(this, v(31))
	},
	773: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
				return (o = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			}),
			r = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var i = a(1),
			l = a(41),
			s = a(29),
			u = a(10),
			c = a(774),
			d = a(775),
			p = a(784),
			f = a(382),
			h = a(381),
			m = function (t) {
				function e(e) {
					return t.call(this, e) || this
				}
				return n(e, t), e.prototype.render = function () {
					var e = this.props.classes;
					return i.createElement("div", null, i.createElement("div", {
						className: e.mapContent,
						id: "mapContent"
					}, i.createElement("div", {
						id: "upperMapContent",
						className: e.upperMapContent,
						style: {
							height: u.default.isBottomLegend ? "auto" : u.default.upperMapContentHeight
						}
					}, i.createElement(d.default, null), i.createElement(p.default, null), u.default.isMapContentVisible ? i.createElement(c.default, null) : null, u.default.isBottomLegend ? i.createElement(f.default, null) : null, u.default.isBottomLegend ? i.createElement(h.default, null) : null)))
				}, e = r([s.observer], e)
			}(i.Component);
		t.default = l.withStyles(function (e) {
			return {
				mapContent: {
					maxWidth: window.innerWidth,
					height: "100%",
					overflow: "hidden"
				},
				upperMapContent: {
					overflowY: "auto"
				}
			}
		})(m)
	},
	774: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
				return (o = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			}),
			r = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var l = a(1),
			s = a(20),
			u = a(17),
			i = a(58),
			c = a(29),
			d = a(28),
			p = a(10),
			f = function (t) {
				function e(e) {
					return t.call(this, e) || this
				}
				return n(e, t), e.prototype.componentWillMount = function () {
					this.m = s.default.current, this.l = u.default.localization
				}, e.prototype.componentWillUpdate = function () {
					this.m = s.default.current, this.l = u.default.localization
				}, e.prototype.renderMapId = function () {
					var e = null;
					if (p.default.vars.isPreview) {
						var t = i.default.getCurrentNode();
						e = l.createElement("div", null, t.parents.join("_"))
					}
					return l.createElement(d.Typography, {
						variant: "caption"
					}, l.createElement("div", null, l.createElement("span", {
						className: "bold"
					}, u.default.localization.messages.MAP_ID, ": "), s.default.current.ida), e)
				}, e.prototype.renderOnlineSince = function () {
					var e, t = u.default.localization.ida,
						a = s.default.current.languages[t].firstact;
					return "" === a ? null : (e = a.split(" ")[0], p.default.vars.isPreview || p.default.vars.isOffline ? null : l.createElement(d.Typography, {
						variant: "caption"
					}, l.createElement("span", {
						className: "bold"
					}, u.default.localization.messages.FIRST_ACTIVATION, ": "), e))
				}, e.prototype.renderLastModification = function () {
					var e = u.default.localization.ida,
						t = s.default.current.languages[e].lastmod;
					if ("" !== t) {
						var a = t,
							o = void 0;
						if (0 !== s.default.current.properties.is_live_map) a = (o = a.split(" "))[0];
						else {
							(o = a.split(":"))[o.length - 1] = o[o.length - 1].substr(2, o[o.length - 1].length), ":" == (a = o.join(":")).substr(a.length - 1, a.length) && (a = a.substr(0, a.length - 1)), o = a.split(" ");
							for (var n = 0, r = "", i = 0; i < o.length; i++) 1 < ++n && (r += " ", 2 == n && (r += " ")), r += o[i];
							a = r
						}
						t = a
					} else t = "-";
					return l.createElement(d.Typography, {
						variant: "caption"
					}, l.createElement("span", {
						className: "bold"
					}, u.default.localization.messages.LAST_MODIFICATION, ": "), t)
				}, e.prototype.render = function () {
					var e = this.props.classes;
					return void 0 !== s.default.current ? l.createElement("div", {
						className: e.statBox
					}, this.renderMapId(), this.renderOnlineSince(), this.renderLastModification()) : null
				}, e = r([c.observer], e)
			}(l.Component);
		t.default = d.withStyles(function (e) {
			return {
				statBox: {
					marginTop: 0,
					padding: "20px 24px 10px"
				}
			}
		})(f)
	},
	775: function (e, y, v) {
		"use strict";
		(function (a) {
			var o, n = this && this.__extends || (o = function (e, t) {
					return (o = Object.setPrototypeOf || {
							__proto__: []
						}
						instanceof Array && function (e, t) {
							e.__proto__ = t
						} || function (e, t) {
							for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
						})(e, t)
				}, function (e, t) {
					function a() {
						this.constructor = e
					}
					o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
				}),
				r = this && this.__decorate || function (e, t, a, o) {
					var n, r = arguments.length,
						i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
					if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
					else
						for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
					return 3 < r && i && Object.defineProperty(t, a, i), i
				};
			Object.defineProperty(y, "__esModule", {
				value: !0
			});
			var i = v(1),
				l = v(29),
				s = v(28),
				u = v(83),
				c = v(20),
				d = v(40),
				p = v(17),
				f = v(19),
				h = v(776),
				m = v(10),
				g = v(783),
				t = v(85),
				e = function (e) {
					function t() {
						return null !== e && e.apply(this, arguments) || this
					}
					return n(t, e), t.prototype.renderData = function () {
						var t = this;
						return i.createElement(s.ListItem, {
							id: "dataView",
							key: "dataView",
							disabled: m.default.isLiveMap && !m.default.isLiveActive,
							onClick: function (e) {
								return t.handleDataView(e)
							},
							button: !0
						}, i.createElement(s.ListItemIcon, null, i.createElement(d.Table, null)), i.createElement(s.ListItemText, {
							primary: p.default.localization.messages.BUTTON_DATA
						}))
					}, t.prototype.renderShare = function () {
						var t = this;
						return i.createElement(s.ListItem, {
							id: "share",
							key: "share",
							onClick: function (e) {
								return t.handleShare(e)
							},
							button: !0
						}, i.createElement(s.ListItemIcon, null, i.createElement(d.ShareVariant, null)), i.createElement(s.ListItemText, {
							primary: f.default.getProject().isMapsProject() ? p.default.localization.messages.BUTTON_SHARE_MAP : p.default.localization.messages.BUTTON_SHARE_CHART
						}))
					}, t.prototype.renderGetXLSX = function () {
						var t = this;
						return c.default.current ? i.createElement(s.ListItem, {
							component: "a",
							download: !0,
							href: "#",
							id: "getXLSX",
							key: "getXLSX",
							onClick: function (e) {
								return t.handleGetXLSX(e)
							},
							disabled: !c.default.current.hasXLSX()
						}, i.createElement(s.ListItemIcon, null, i.createElement(d.FileExcel, null)), i.createElement(s.ListItemText, {
							primary: p.default.localization.messages.BUTTON_XLSX_DOWNLOAD_SHORT
						})) : null
					}, t.prototype.renderGetPDF = function () {
						var t = this;
						return c.default.current ? i.createElement(s.ListItem, {
							component: "a",
							download: !0,
							href: "#",
							id: "getPDF",
							key: "getPDF",
							onClick: function (e) {
								return t.handleGetPDF(e)
							},
							disabled: !c.default.current.hasPDF()
						}, i.createElement(s.ListItemIcon, null, i.createElement(d.FilePdf, null)), i.createElement(s.ListItemText, {
							primary: p.default.localization.messages.BUTTON_PDF_DOWNLOAD_SHORT
						})) : null
					}, t.prototype.handleDataView = function (e) {
						m.default.modalContent = i.createElement(h.default, null), m.default.modalOpen = !0
					}, t.prototype.handleGetXLSX = function (e) {
						"disabled" !== a(e.currentTarget).attr("disabled") && (e.currentTarget.href = c.default.current.getXLSX())
					}, t.prototype.handleGetPDF = function (e) {
						"disabled" !== a(e.currentTarget).attr("disabled") && (e.currentTarget.href = c.default.current.getPDF())
					}, t.prototype.handleShare = function (e) {
						m.default.modalContent = i.createElement(g.default, null), m.default.modalWidth = 600, m.default.modalOpen = !0
					}, t.prototype.render = function () {
						var t = this,
							e = this.props.classes;
						return u.default.current && c.default.current ? i.createElement("div", {
							className: "mapContentHeader"
						}, i.createElement("table", {
							className: e.linkBox
						}, i.createElement("colgroup", null, i.createElement("col", {
							style: {
								width: "25%"
							}
						}), i.createElement("col", {
							style: {
								width: "25%"
							}
						}), i.createElement("col", {
							style: {
								width: "25%"
							}
						}), i.createElement("col", {
							style: {
								width: "25%"
							}
						})), i.createElement("tbody", null, i.createElement("tr", null, i.createElement("td", null, i.createElement(s.Button, {
							id: "dataView",
							key: "dataView",
							className: e.linkBoxButton,
							disabled: 1 == c.default.current.properties.is_live_map && 1 != c.default.current.properties.is_live_active,
							onClick: function (e) {
								return t.handleDataView(e)
							}
						}, i.createElement("div", null, i.createElement(d.Table, null)), i.createElement(s.Typography, {
							variant: "caption"
						}, p.default.localization.messages.BUTTON_DATA))), i.createElement("td", null, i.createElement(s.Tooltip, {
							title: p.default.localization.messages.BUTTON_XLSX_DOWNLOAD,
							placement: "bottom"
						}, i.createElement(s.Button, {
							className: e.linkBoxButton,
							download: !0,
							href: "#",
							id: "getXLSX",
							key: "getXLSX",
							onClick: function (e) {
								return t.handleGetXLSX(e)
							},
							disabled: !c.default.current.hasXLSX()
						}, i.createElement("div", null, i.createElement(d.FileExcel, null)), i.createElement(s.Typography, {
							variant: "caption"
						}, p.default.localization.messages.BUTTON_XLSX_DOWNLOAD_SHORT)))), i.createElement("td", null, i.createElement(s.Tooltip, {
							title: p.default.localization.messages.BUTTON_PDF_DOWNLOAD,
							placement: "bottom"
						}, i.createElement(s.Button, {
							className: e.linkBoxButton,
							download: !0,
							href: "#",
							id: "getPDF",
							key: "getPDF",
							onClick: function (e) {
								return t.handleGetPDF(e)
							},
							disabled: !c.default.current.hasPDF()
						}, i.createElement("div", null, i.createElement(d.FilePdf, null)), i.createElement(s.Typography, {
							variant: "caption"
						}, p.default.localization.messages.BUTTON_PDF_DOWNLOAD_SHORT)))), i.createElement("td", null, i.createElement(s.Tooltip, {
							title: f.default.getProject().isMapsProject() ? p.default.localization.messages.BUTTON_SHARE_MAP : p.default.localization.messages.BUTTON_SHARE_CHART,
							placement: "bottom"
						}, i.createElement(s.Button, {
							className: e.linkBoxButton,
							id: "share",
							key: "share",
							onClick: function (e) {
								return t.handleShare(e)
							}
						}, i.createElement("div", null, i.createElement(d.ShareVariant, null)), i.createElement(s.Typography, {
							variant: "caption"
						}, (f.default.getProject().isMapsProject(), p.default.localization.messages.BUTTON_SHARE_MAP_SHORT))))))))) : null
					}, t = r([l.observer], t)
				}(i.Component);
			y.default = s.withStyles(function (e) {
				return {
					statBox: {
						marginTop: 0,
						padding: 24
					},
					linkBoxButton: {
						display: "block",
						padding: 10,
						color: t.darkgrey,
						textTransform: "none"
					},
					linkBox: {
						"& td": {
							verticalAlign: "top",
							textAlign: "center"
						}
					}
				}
			})(e)
		}).call(this, v(31))
	},
	776: function (e, _, T) {
		"use strict";
		(function (t) {
			var o, n = this && this.__extends || (o = function (e, t) {
					return (o = Object.setPrototypeOf || {
							__proto__: []
						}
						instanceof Array && function (e, t) {
							e.__proto__ = t
						} || function (e, t) {
							for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
						})(e, t)
				}, function (e, t) {
					function a() {
						this.constructor = e
					}
					o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
				}),
				r = this && this.__decorate || function (e, t, a, o) {
					var n, r = arguments.length,
						i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
					if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
					else
						for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
					return 3 < r && i && Object.defineProperty(t, a, i), i
				};
			Object.defineProperty(_, "__esModule", {
				value: !0
			});
			var i = T(5),
				d = T(1),
				B = T(10),
				z = T(48),
				l = T(777),
				s = T(778),
				u = T(84),
				F = T(17),
				U = T(19),
				p = T(779),
				W = T(20),
				H = T(30),
				f = T(28),
				a = T(64),
				c = T(25),
				e = T(29),
				G = T(18),
				h = T(41),
				m = T(383),
				Y = [],
				Z = "",
				q = function (e) {
					Z += e
				},
				g = h.withStyles(function (e) {
					return {
						root: {
							width: "fit-content",
							marginTop: 3 * e.spacing.unit
						},
						table: {
							width: "auto"
						},
						tableWrapper: {
							overflow: "auto",
							width: "100%"
						},
						td_string: {
							width: 100,
							textAlign: "left"
						},
						td_string_id: {
							width: 30,
							textAlign: "left"
						},
						td_numeric: {
							width: 100,
							textAlign: "right"
						}
					}
				}),
				y = g(function (e) {
					function t() {
						var a = null !== e && e.apply(this, arguments) || this;
						return a.createSortHandler = function (t) {
							return function (e) {
								a.props.onRequestSort(e, t)
							}
						}, a
					}
					return n(t, e), t.prototype.render = function () {
						var t = this,
							e = this.props,
							a = e.order,
							o = e.orderBy,
							n = e.summary,
							r = e.classes;
						return d.createElement(f.TableHead, null, d.createElement(f.TableRow, null, Y.map(function (e) {
							return d.createElement(f.TableCell, {
								key: e.id,
								align: e.numeric ? "right" : "left",
								padding: e.disablePadding ? "dense" : "default",
								sortDirection: o === e.id && a
							}, d.createElement(f.Tooltip, {
								title: "Sort",
								placement: e.numeric ? "bottom-end" : "bottom-start",
								enterDelay: 300
							}, d.createElement(f.TableSortLabel, {
								active: o === e.id,
								direction: a,
								onClick: t.createSortHandler(e.id),
								component: "div",
								className: e.numeric ? r.td_numeric : "id" === e.id ? r.td_string_id : r.td_string
							}, e.label)))
						}, this)), n.map(function (a, e) {
							return d.createElement(f.TableRow, {
								key: "summary" + e,
								className: "tableRowHighlight"
							}, W.default.current.properties.showids && d.createElement(f.TableCell, {
								component: "th",
								scope: "row",
								padding: a.disablePadding ? "dense" : "default"
							}, d.createElement("div", {
								className: r.td_string_id
							}, a.id)), d.createElement(f.TableCell, {
								component: "th",
								scope: "row",
								padding: a.disablePadding ? "dense" : "default"
							}, d.createElement("div", {
								className: r.td_string
							}, a.name)), a.values.map(function (e, t) {
								return d.createElement(f.TableCell, {
									align: "right",
									component: "th",
									padding: a.disablePadding ? "dense" : "default",
									scope: "row",
									key: "summaryvalue" + t
								}, d.createElement("div", {
									className: r.td_numeric
								}, e))
							}))
						}))
					}, t
				}(d.Component)),
				v = (h.withStyles(function (e) {
					e.spacing;
					var t = e.palette;
					return {
						root: {
							paddingRight: 0
						},
						highlight: "light" === t.type ? {
							color: t.secondary.main,
							backgroundColor: a.lighten(t.secondary.light, .85)
						} : {
							color: t.text.primary,
							backgroundColor: t.secondary.dark
						},
						spacer: {
							flex: "1 1 100%"
						},
						actions: {
							color: t.text.secondary
						},
						title: {
							flex: "0 0 auto"
						}
					}
				})(function (e) {
					function t() {
						return null !== e && e.apply(this, arguments) || this
					}
					return n(t, e), t.prototype.render = function () {
						var e, t = this.props,
							a = t.numSelected,
							o = t.classes;
						return d.createElement(f.Toolbar, {
							className: i(o.root, (e = {}, e[o.highlight] = 0 < a, e))
						}, d.createElement("div", {
							className: o.title
						}, 0 < a ? d.createElement(f.Typography, {
							color: "inherit",
							variant: "subtitle1"
						}, a, " selected") : d.createElement(f.Typography, {
							variant: "h6",
							id: "tableTitle"
						}, F.default.localization.messages.BUTTON_DATA)), d.createElement("div", {
							className: o.spacer
						}), d.createElement("div", {
							className: o.actions
						}, 0 < a ? d.createElement(f.Tooltip, {
							title: "Delete"
						}, d.createElement(f.IconButton, {
							"aria-label": "Delete"
						}, d.createElement(l.default, null))) : d.createElement(f.Tooltip, {
							title: "Filter list"
						}, d.createElement(f.IconButton, {
							"aria-label": "Filter list"
						}, d.createElement(s.default, null)))))
					}, t
				}(d.Component)), g(e.observer(function (t) {
					function e(e) {
						var i = t.call(this, e) || this;
						return i.handleRequestSort = function (e, t) {
							var a = t,
								o = "desc";
							i.state.orderBy === t && "desc" === i.state.order && (o = "asc");
							var n = void 0 !== i.state.data[0] && null == i.state.data[0].sort[a],
								r = i.state.data;
							r = n ? "desc" === o ? i.state.data.sort(function (e, t) {
								return t.sort.values[a] - e.sort.values[a]
							}) : i.state.data.sort(function (e, t) {
								return e.sort.values[a] - t.sort.values[a]
							}) : "desc" === o ? i.state.data.sort(function (e, t) {
								return t.sort[a] < e.sort[a] ? -1 : 1
							}) : i.state.data.sort(function (e, t) {
								return e.sort[a] < t.sort[a] ? -1 : 1
							}), i.setState({
								data: r,
								order: o,
								orderBy: a
							})
						}, i.handleSelectAllClick = function (e, t) {
							t ? i.setState({
								selected: i.state.data.map(function (e) {
									return e.id
								})
							}) : i.setState({
								selected: []
							})
						}, i.handleClick = function (e, t) {
							var a = i.state.selected,
								o = a.indexOf(t),
								n = []; - 1 === o ? n = n.concat(a, t) : 0 === o ? n = n.concat(a.slice(1)) : o === a.length - 1 ? n = n.concat(a.slice(0, -1)) : 0 < o && (n = n.concat(a.slice(0, o), a.slice(o + 1))), i.setState({
								selected: n
							})
						}, i.handleChangePage = function (e, t) {
							i.setState({
								page: t
							})
						}, i.handleChangeRowsPerPage = function (e) {
							i.setState({
								rowsPerPage: e.target.value
							})
						}, i.isSelected = function (e) {
							return -1 !== i.state.selected.indexOf(e)
						}, i.state = {
							order: "asc",
							orderBy: "calories",
							selected: [],
							data: [],
							summary: [],
							page: 0,
							rowsPerPage: 100
						}, i
					}
					return n(e, t), e.prototype.componentWillReceiveProps = function (e) {
						this.setState({
							data: e.data,
							summary: e.summary,
							height: e.height,
							width: e.width
						}), this.forceUpdate()
					}, e.prototype.handleRowClick = function (e, t) {
						B.default.emitter.emit(G.UNIT_WAS_CLICKED_IN_UNITLIST, {
							unit_ida: t
						}), B.default.modalOpen = !1
					}, e.prototype.render = function () {
						var o = this,
							n = this.props.classes,
							e = this.state,
							t = e.data,
							a = e.summary,
							r = e.order,
							i = e.orderBy,
							l = e.selected,
							s = e.rowsPerPage,
							u = e.page,
							c = s - Math.min(s, t.length - u * s);
						return d.createElement(f.Paper, {
							key: "table-paper",
							className: n.root
						}, d.createElement("div", {
							className: n.tableWrapper
						}, d.createElement(f.Table, {
							className: n.table,
							"aria-labelledby": "tableTitle",
							id: "headTable"
						}, d.createElement(y, {
							numSelected: l.length,
							order: r,
							orderBy: i,
							onSelectAllClick: this.handleSelectAllClick,
							onRequestSort: this.handleRequestSort,
							rowCount: t.length,
							summary: a
						})), d.createElement(p.default, {
							style: {
								height: this.state.height,
								width: this.state.width
							},
							noScrollX: !0
						}, d.createElement(f.Table, null, d.createElement(f.TableBody, null, t.slice(u * s, u * s + s).map(function (a, e) {
							var t = o.isSelected(a.row.id);
							return d.createElement(f.TableRow, {
								hover: !0,
								onClick: function (e) {
									return o.handleRowClick(e, a.row.id)
								},
								className: "href",
								role: "href",
								tabIndex: -1,
								key: a.row.key,
								selected: t
							}, 1 === W.default.current.properties.showids ? d.createElement(f.TableCell, {
								component: "td",
								scope: "row",
								padding: a.row.disablePadding ? "dense" : "default"
							}, d.createElement("div", {
								className: n.td_string_id
							}, a.row.id)) : null, d.createElement(f.TableCell, {
								component: "td",
								scope: "row",
								padding: a.row.disablePadding ? "dense" : "default"
							}, d.createElement("div", {
								className: n.td_string
							}, a.row.name)), Object.keys(a.row.values).map(function (e, t) {
								return d.createElement(f.TableCell, {
									align: "right",
									component: "td",
									scope: "row",
									padding: a.row.disablePadding ? "dense" : "default",
									key: "value" + e
								}, d.createElement("div", {
									className: n.td_numeric
								}, a.row.values[e]))
							}))
						}), 0 < c && d.createElement(f.TableRow, {
							style: {
								height: 49 * c
							}
						}, d.createElement(f.TableCell, {
							colSpan: 6
						})))))), d.createElement(f.TablePagination, {
							component: "div",
							count: t.length,
							rowsPerPage: s,
							rowsPerPageOptions: [10, 50, 100, 200, 500],
							page: u,
							labelDisplayedRows: function (e) {
								var t = e.from,
									a = e.to,
									o = e.count;
								return t + "-" + a + " " + F.default.localization.messages.OF + " " + o
							},
							labelRowsPerPage: F.default.localization.messages.ROWS_PER_PAGE + ":",
							backIconButtonProps: {
								"aria-label": "Previous Page"
							},
							nextIconButtonProps: {
								"aria-label": "Next Page"
							},
							onChangePage: this.handleChangePage,
							onChangeRowsPerPage: this.handleChangeRowsPerPage
						}))
					}, e
				}(d.Component)))),
				b = function (a) {
					function e(e) {
						var t = a.call(this, e) || this;
						return t.state = {}, t.data = [], t.dataviewIsMounted = !1, t.state = {
							data: [],
							height: 0
						}, B.default.emitter.on(G.WINDOW_RESIZED, function () {
							t.adaptTableHeight(), t.adaptTableWidth()
						}), t
					}
					return n(e, a), e.prototype.adaptTableHeight = function () {
						this.dataviewIsMounted && this.setState({
							height: window.innerHeight - t("#dataViewHead").height() - t("#headTable").height() - 200
						})
					}, e.prototype.adaptTableWidth = function () {
						this.dataviewIsMounted && this.setState({
							width: t("#headTable").width()
						})
					}, e.prototype.componentDidMount = function () {
						var e = this;
						this.dataInfo = z.default.getDataInfo(), this.p = U.default.getProject(), this.m = W.default.current, this.l = F.default.localization, this.generateDataView(), this.dataviewIsMounted = !0, setTimeout(function () {
							e.adaptTableHeight(), e.adaptTableWidth(), B.default.emitter.emit(G.WINDOW_RESIZED)
						}, 200)
					}, e.prototype.componentWillUnmount = function () {
						this.dataviewIsMounted = !1
					}, e.prototype.generateDataView = function () {
						var e, a, o = this,
							n = !(Y = []);
						if (Z = "", q(U.default.getProject().currentLanguage().name + "\n"), this.p.isMapsProject()) {
							n = this.m.properties.showids, e = this.m.getDatasetIds();
							var t = B.default.mapPath.length ? B.default.mapPath.map(function (e, t) {
								return e[F.default.localization.ida] + (t < B.default.mapPath.length - 1 ? " > " : "")
							}).join(" ") : "";
							q(t + "\n"), q(W.default.current.getCurrentMapTitle() + "\n");
							var r = z.default.get(e[0]);
							q(r.getCurrentVG())
						} else this.p.isIndicatorProject();
						if (q("\n\n"), n && (Y.push({
								id: "id",
								key: "id",
								numeric: !1,
								disablePadding: !0,
								label: this.l.messages.DATAVIEW_ID_COLUMN
							}), q(this.l.messages.DATAVIEW_ID_COLUMN + "\t")), Y.push({
								id: "name",
								key: "name",
								numeric: !1,
								disablePadding: !0,
								label: this.l.messages.DATAVIEW_NAME_COLUMN
							}), q(this.l.messages.DATAVIEW_NAME_COLUMN + "\t"), this.p.isIndicatorProject());
						else if (e = this.m.getDatasetIds(), a = z.default.get(e[0]), Object.keys(a.values).length) {
							var i = Object.keys(a.values).filter(function (e, t) {
								return !1 === a.values[e].pseudoId
							});
							if (i.length) {
								var l = this.dataInfo.getRegionInfo(i[0]);
								if ("object" == typeof l && void 0 !== l.data) l.data.map(function (e, t) {
									Y.push({
										id: "c" + t,
										key: "c" + t,
										numeric: !0,
										disablePadding: !0,
										label: e.label
									}), q(e.label + "\t")
								});
								if (l.datasets.length) {
									var s = [],
										u = [],
										c = a.getValuesObject(!1, !1, !1),
										d = a.getValuesObject(!0, !0, !1);
									if (Object.keys(c).map(function (e, t) {
											s.push(parseInt(e.substr(1)).toString() == e.substr(1) ? parseInt(e.substr(1)) : e.substr(1))
										}), s.sort(this.sortNumeric), Object.keys(d).map(function (e, t) {
											u.push(parseInt(e.substr(1)).toString() == e.substr(1) ? parseInt(e.substr(1)) : e.substr(1))
										}), u.sort(this.sortNumeric), this.p.isMapsProject()) {
										var p = this.generateSummaryRows();
										this.setState({
											summary: p
										})
									}
									var f = [];
									if (s.map(function (e) {
											if (o.p.isMapsProject()) {
												var t = o.generateRow(e, n);
												f.push(t)
											}
										}), f = f.sort(function (e, t) {
											return e.row.name > t.row.name ? 1 : -1
										}), this.p.isMapsProject())
										for (var h = 0; h < u.length; h++) {
											var m = this.dataInfo.getRegionInfo(u[h]),
												g = !1;
											if (m.data.length && m.data.map(function (e) {
													null !== e.value && (g = !0)
												}), g) {
												var y = this.generateRow(u[h], n);
												f.push(y)
											}
										}
									q("\n\n");
									var v = z.default.getDataInfo().getInfoValues(),
										b = v.datasetsOrdered,
										_ = v.infoLabels,
										T = [];
									if (b.length)
										for (var E = 0; E < _.length; E++)
											if (!(-1 < T.indexOf(_[E]))) {
												n && q("\t"), q(_[E]);
												for (var S = function (t) {
														if (t.visType == G.VISUALIZATION_QUALI) return q("\t"), "continue";
														var e = v.visualizations[t.visType].filter(function (e) {
																return e.dataset_ida === t.dataset_ida
															}),
															a = null;
														e.length && (a = e[0]).infoLabel === _[E] ? q(a.infoValueString) : q("\t")
													}, L = 0, O = b; L < O.length; L++) {
													S(A = O[L])
												}
												q("\n"), T.push(_[E])
											}
									q("\n\n"), n && q("\t"), q(this.l.messages.INFO_NOTE + ":");
									for (var I = 0; I < b.length; I++) {
										var A = b[I],
											w = this.m.getVisualization(A.visType).languages[this.l.ida].comment;
										w = null === w ? "" : w.replace(/(\r\n|\n|\r)/gm, " "), q("\t" + w)
									}
									q("\n"), n && q("\t"), q(this.l.messages.INFO_SURVEY_DATES + ":");
									for (var C = 0; C < b.length; C++) {
										A = b[C];
										var P = z.default.get(A.dataset_ida),
											N = P.getCurrent("survey_date"),
											M = P.getCurrent("survey_date_end"),
											D = N + (M.length ? " - " + M : "");
										q("\t" + D)
									}
									q("\n"), n && q("\t"), q(this.l.messages.SOURCE + ":");
									for (var R = 0; R < b.length; R++) {
										0 == (A = b[R]).dataset_ida || isNaN(A.dataset_ida) ? q("\t") : (a = z.default.get(A.dataset_ida), q("\t" + a.getCurrent("source")))
									}
									q("\n"), n && q("\t"), q(this.l.messages.DATASET + ":");
									for (var k = 0; k < b.length; k++) {
										0 == (A = b[k]).dataset_ida || isNaN(A.dataset_ida) ? q("\t") : q("\t( #" + A.dataset_ida + " )")
									}
									q("\n\n"), n && q("\t");
									var j = this.m.properties.missing_data_label,
										x = this.l.properties.MISSING_DATA_LABELS[j].NAME,
										V = this.l.properties.MISSING_DATA_LABELS[j].SHORT;
									q(V + " = " + x), q("\n\n"), n && q("\t"), q(this.p.currentLanguage().copyright_short), H.bajoodooLog("EXPORTSTRING", Z), this.setState({
										data: f
									})
								}
							}
						}
						B.default.modalWidth = 250 * Y.length, B.default.emitter.emit(G.WINDOW_RESIZED)
					}, e.prototype.handleClipboardCopyClick = function (e) {
						m(Z), B.default.setSnackbarText(F.default.localization.messages.COPY_DATA_VIEW_SUCCESS_MESSAGE), B.default.snackbarOpen = !0
					}, e.prototype.generateSummaryRows = function () {
						var s = this,
							t = this.dataInfo.getInfoValues(),
							a = [];
						return t.infoLabels.map(function (r, e) {
							var i = {
								disablePadding: !0,
								id: null
							};
							i.name = r;
							var l = !1;
							i.values = [], 0 == a.filter(function (e) {
								return e.name === r
							}).length && (t.datasetsOrdered.map(function (a, e) {
								var o = a.visType,
									n = !1;
								t.visualizations[o].map(function (e, t) {
									e.infoLabel === r && e.dataset_ida === a.dataset_ida && ("sect" == o && !0 === e.sector && 1 === s.m.visualizations.sect.show_info_value_sectors || !e.sector && 1 === s.m.getVisualization(o).show_info_value) && (i.values.push(e.infoValueString), n = l = !0)
								}), n || i.values.push(null)
							}), l && a.push(i))
						}), H.bajoodooLog("ROWS", a, t), a
					}, e.prototype.generateRow = function (e, t) {
						var a = this.dataInfo.getRegionInfo(e),
							o = {},
							n = {};
						return q("\n"), o.disablePadding = !0, o.key = e, n.key = e, o.id = e, n.id = e, o.name = a.label, n.name = a.label, o.values = [], n.values = [], t && q(e + "\t"), q(a.label), a.data.map(function (e, t) {
							o.values["c" + t] = e.fvalue, n.values["c" + t] = e.value, q("\t" + u.default.tryToReformatToNumber(e.fvalue))
						}), {
							row: o,
							sort: n
						}
					}, e.prototype.generateIndicatorAttendantRow = function (e) {
						return {}
					}, e.prototype.sortNumeric = function (e, t) {
						return t < e ? 1 : e < t ? -1 : 0
					}, e.prototype.handleGetXLSX = function (e) {
						"disabled" !== t(e.currentTarget).attr("disabled") && (e.currentTarget.href = W.default.current.getXLSX())
					}, e.prototype.renderDownload = function () {
						var t = this;
						return d.createElement("div", null, d.createElement(f.Typography, {
							variant: "subheading",
							id: "download-title",
							key: "download-title"
						}, F.default.localization.messages.COPY_DATA_VIEW_TITLE), d.createElement(f.Typography, {
							className: "href",
							variant: "body1",
							id: "sort-message",
							key: "sort-message",
							onClick: function (e) {
								return t.handleClipboardCopyClick(e)
							}
						}, F.default.localization.messages.COPY_DATA_VIEW), void 0 !== W.default.current && W.default.current.hasXLSX() ? d.createElement("div", null, d.createElement(f.Typography, {
							variant: "body1",
							id: "download-link",
							key: "download-link"
						}, d.createElement("a", {
							download: !0,
							href: "#",
							onClick: function (e) {
								return t.handleGetXLSX(e)
							}
						}, F.default.localization.messages.DOWNLOAD_XLSX))) : null)
					}, e.prototype.render = function () {
						return d.createElement("div", null, d.createElement("div", {
							id: "dataViewHead"
						}, d.createElement(f.Typography, {
							variant: "h6",
							id: "tableTitle"
						}, F.default.localization.messages.BUTTON_DATA), d.createElement(f.Typography, {
							variant: "subheading",
							id: "click-title",
							key: "click-title"
						}, F.default.localization.messages.DATA_VIEW_CLICK_TITLE), d.createElement(f.Typography, {
							variant: "body1",
							id: "click-message",
							key: "click-message"
						}, F.default.localization.messages.DATA_VIEW_CLICK_MESSAGE), d.createElement(f.Typography, {
							variant: "subheading",
							id: "sort-title",
							key: "sort-title"
						}, F.default.localization.messages.DATA_VIEW_SORT_TITLE), d.createElement(f.Typography, {
							variant: "body1",
							id: "sort-message",
							key: "sort-message"
						}, F.default.localization.messages.DATA_VIEW_INFO_MESSAGE), this.renderDownload()), d.createElement(v, {
							height: this.state.height,
							width: this.state.width,
							data: this.state.data,
							summary: this.state.summary,
							closeHandler: this.props.closeHandler
						}))
					}, r([c.observable], e.prototype, "data", void 0), e
				}(d.Component);
			_.default = b
		}).call(this, T(31))
	},
	783: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
				return (o = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			}),
			r = (this && this.__assign, this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			});
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var i = a(1),
			l = a(28),
			s = a(17),
			u = a(19),
			c = a(20),
			d = a(383),
			p = a(10),
			f = a(40),
			h = a(29),
			m = a(379);
		var g = function (e) {
			function t() {
				return null !== e && e.apply(this, arguments) || this
			}
			return n(t, e), t.prototype.componentWillMount = function () {
				this.linksAndTitles = new m.LinksAndTitles
			}, t.prototype.handleFacebookClick = function (e) {
				var t = this.linksAndTitles.prepareShareUrl(),
					a = this.linksAndTitles.prepareShareTitle();
				this.linksAndTitles.openWindow("https://www.facebook.com/sharer/sharer.php?u=" + t + "&t=" + a)
			}, t.prototype.handleGooglePlusClick = function (e) {
				var t = this.linksAndTitles.prepareShareUrl();
				this.linksAndTitles.openWindow("https://plus.google.com/share?url=" + t)
			}, t.prototype.handleTwitterClick = function (e) {
				var t = this.linksAndTitles.prepareShareUrl(),
					a = this.linksAndTitles.prepareShareTitle();
				this.linksAndTitles.openWindow("https://twitter.com/intent/tweet?url=" + t + "&text=" + a)
			}, t.prototype.handleShortUrlCopyClick = function (e) {
				d(this.linksAndTitles.renderShortUrl()), p.default.setSnackbarText(s.default.localization.messages.COPY_MAP_URL_SUCCESS_MESSAGE), p.default.snackbarOpen = !0
			}, t.prototype.handleCopyLinkClick = function (e) {
				var t = this.linksAndTitles.getEmbedCode(this.linksAndTitles.getShortElements(), u.default.getProject().isMapsProject() ? u.default.getProject().currentLanguage().name + " - " + c.default.current.getCurrentMapTitle() : this.linksAndTitles.getIndicatorPageTitle());
				d(t), p.default.setSnackbarText(s.default.localization.messages.COPY_EMBED_HTML_SUCCESS_MESSAGE), p.default.snackbarOpen = !0
			}, t.prototype.handleEmbedClick = function (e) {
				d(this.linksAndTitles.getIFrameCode(this.linksAndTitles.getShortElements())), p.default.setSnackbarText(s.default.localization.messages.COPY_EMBED_IFRAME_SUCCESS_MESSAGE), p.default.snackbarOpen = !0
			}, t.prototype.render = function () {
				var t = this,
					e = this.props.classes;
				return i.createElement("div", null, i.createElement("table", {
					className: e.table
				}, i.createElement("colgroup", null, i.createElement("col", {
					style: {
						width: "auto"
					}
				}), i.createElement("col", {
					style: {
						width: "auto"
					}
				})), i.createElement("thead", null), i.createElement("tbody", null, i.createElement("tr", null, i.createElement("td", {
					colSpan: 2
				}, i.createElement(l.Typography, {
					variant: "h6",
					id: "share-title"
				}, u.default.getProject().isMapsProject() ? s.default.localization.messages.BUTTON_SHARE_MAP : s.default.localization.messages.BUTTON_SHARE_CHART), i.createElement(l.Button, {
					className: e.clipboardButton,
					title: s.default.localization.messages.BUTTON_FACEBOOK,
					onClick: function (e) {
						return t.handleFacebookClick(e)
					}
				}, i.createElement(f.FacebookBox, {
					className: e.socialIcon,
					style: {
						color: "#3b5998"
					}
				})), i.createElement(l.Button, {
					className: e.clipboardButton,
					title: s.default.localization.messages.BUTTON_GOOGLE_PLUS,
					onClick: function (e) {
						return t.handleGooglePlusClick(e)
					}
				}, i.createElement(f.GooglePlusBox, {
					className: e.socialIcon,
					style: {
						color: "#dd4b39"
					}
				})), i.createElement(l.Button, {
					className: e.clipboardButton,
					title: s.default.localization.messages.BUTTON_TWITTER,
					onClick: function (e) {
						return t.handleTwitterClick(e)
					}
				}, i.createElement(f.TwitterBox, {
					className: e.socialIcon,
					style: {
						color: "#55acee"
					}
				})))), i.createElement("tr", null, i.createElement("td", {
					colSpan: 2
				}, i.createElement(l.Typography, {
					variant: "h6"
				}, u.default.getProject().isMapsProject() ? s.default.localization.messages.BUTTON_SHORT_URL : s.default.localization.indicators.BUTTON_SHORT_URL))), i.createElement("tr", null, i.createElement("td", null, i.createElement(l.Typography, {
					variant: "body2",
					className: "urlWrap"
				}, this.linksAndTitles.renderShortUrl())), i.createElement("td", null, i.createElement(l.Button, {
					className: e.clipboardButton,
					onClick: function (e) {
						return t.handleShortUrlCopyClick(e)
					}
				}, i.createElement(f.ContentCopy, null)))), i.createElement("tr", null, i.createElement("td", {
					colSpan: 2
				}, i.createElement(l.Typography, {
					variant: "h6",
					id: "link-title"
				}, u.default.getProject().isMapsProject() ? s.default.localization.messages.COPY_LINK_MAP_TITLE : s.default.localization.messages.COPY_LINK_CHART_TITLE))), i.createElement("tr", null, i.createElement("td", null, i.createElement(l.Typography, {
					variant: "body2",
					id: "link-description"
				}, u.default.getProject().isMapsProject() ? s.default.localization.messages.COPY_LINK_MAP_INFO : s.default.localization.indicators.COPY_LINK_CHART_INFO)), i.createElement("td", null, i.createElement(l.Button, {
					className: e.clipboardButton,
					onClick: function (e) {
						return t.handleCopyLinkClick(e)
					}
				}, i.createElement(f.ContentCopy, null)))), i.createElement("tr", null, i.createElement("td", {
					colSpan: 2
				}, i.createElement(l.Typography, {
					variant: "h6",
					id: "link-title"
				}, u.default.getProject().isMapsProject() ? s.default.localization.messages.COPY_EMBED_HTML_TITLE : s.default.localization.indicators.COPY_EMBED_HTML_TITLE))), i.createElement("tr", null, i.createElement("td", null, i.createElement(l.Typography, {
					variant: "body2",
					id: "embed-description"
				}, u.default.getProject().isMapsProject() ? s.default.localization.messages.COPY_EMBED_HTML_INFO : s.default.localization.indicators.COPY_EMBED_HTML_INFO)), i.createElement("td", null, i.createElement(l.Button, {
					className: e.clipboardButton,
					onClick: function (e) {
						return t.handleEmbedClick(e)
					}
				}, i.createElement(f.ContentCopy, null)))))))
			}, t = r([h.observer], t)
		}(i.Component);
		t.default = l.withStyles(function (e) {
			return {
				table: {
					width: "100%",
					marginTop: 20
				},
				clipboardButton: {
					padding: 4,
					minWidth: "auto"
				},
				socialIcon: {
					width: "2em",
					height: "2em"
				}
			}
		})(g)
	},
	784: function (e, t, d) {
		"use strict";
		(function (a) {
			var o, n = this && this.__extends || (o = function (e, t) {
					return (o = Object.setPrototypeOf || {
							__proto__: []
						}
						instanceof Array && function (e, t) {
							e.__proto__ = t
						} || function (e, t) {
							for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
						})(e, t)
				}, function (e, t) {
					function a() {
						this.constructor = e
					}
					o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
				}),
				r = this && this.__decorate || function (e, t, a, o) {
					var n, r = arguments.length,
						i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
					if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
					else
						for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
					return 3 < r && i && Object.defineProperty(t, a, i), i
				};
			Object.defineProperty(t, "__esModule", {
				value: !0
			});
			var v = d(1),
				b = d(79),
				i = d(29),
				_ = d(28),
				l = d(83),
				y = d(20),
				s = d(151),
				T = d(17),
				E = d(19),
				u = d(10),
				S = d(18),
				L = d(48),
				c = d(785),
				e = function (t) {
					function e() {
						var e = null !== t && t.apply(this, arguments) || this;
						return e.modes = [], e.formattedText = ["mapComment"], e
					}
					return n(e, t), e.prototype.componentDidMount = function () {
						var e = this;
						u.default.emitter.on(S.WINDOW_RESIZED, function () {
							return e.onResize()
						})
					}, e.prototype.componentDidUpdate = function () {
						u.default.contentWidth = a("#mapContentDrawer").width(), this.onResize()
					}, e.prototype.onResize = function () {
						u.default.upperMapContentHeight = window.innerHeight - u.default.appHeaderHeight - 60
					}, e.prototype.testContentForFormatting = function (e) {
						return -1 < this.formattedText.indexOf(b.camelCase(e)) ? v.createElement(_.Typography, {
							key: "formatted" + e,
							dangerouslySetInnerHTML: this[b.camelCase(e)]()
						}) : v.createElement("div", {
							key: "unformatted" + e
						}, this[b.camelCase(e)]())
					}, e.prototype.mapComment = function () {
						var e = T.default.localization.ida,
							t = y.default.current ? y.default.current.languages[e].comment : "";
						return {
							__html: 0 < t.length ? t : T.default.localization.messages.NO_MAP_COMMENT_INFO
						}
					}, e.prototype.glossaryDef = function () {
						var a = this.props.classes,
							o = 0,
							n = T.default.localization.ida,
							e = T.default.localization,
							r = y.default.current ? y.default.current.metadata.glossaries : {},
							i = (T.default.localization.messages.INFO_DEFINITION_TEXT, []),
							l = T.default.localization.messages.LINK_TO_MEDIASTAT_GLOSSARY;
						Object.keys(r).map(function (e, t) {
							var a = b.replace(l, "#GLOSSARY_ID#", r[e].id);
							a = b.replace(a, "#LNG#", T.default.localization.codeShort), i.push({
								id: parseInt(e),
								glossaryId: r[e].id,
								name: r[e].languages[n].name,
								link: a
							})
						});
						var t = [];
						return 1 === i.length ? t.push(v.createElement(_.Typography, {
							key: ++o
						}, e.messages.INFO_DEFINITION_TEXT)) : 1 < i.length ? t.push(v.createElement(_.Typography, {
							key: ++o
						}, e.messages.INFO_DEFINITIONS_TEXT)) : t.push(v.createElement(_.Typography, {
							key: ++o
						}, e.messages.INFO_NO_DEFINITIONS)), t.push(v.createElement(_.List, {
							key: "glossaryList"
						}, i.map(function (e, t) {
							return v.createElement(_.ListItem, {
								button: !0,
								key: "glossary" + t,
								component: "a",
								href: e.link,
								className: a.definitionsListItem
							}, v.createElement(_.ListItemText, {
								primary: e.name,
								key: "listitem" + ++o,
								disableTypography: !0,
								className: a.definitionsButton
							}))
						}))), i.length && t.push(v.createElement(_.Typography, {
							variant: "caption",
							key: ++o
						}, e.messages.INFO_DEFINITIONS_WARNING)), t
					}, e.prototype.calculations = function () {
						for (var o = 0, e = L.default.getDataInfo().getInfoValues(), n = (T.default.localization.ida, T.default.localization), t = e.datasetsOrdered, r = [], i = 0, a = function (e) {
								if (0 === e.dataset_ida) return "continue";
								var t = L.default.get(e.dataset_ida);
								if ("" !== b.trim(t.getInfoByLabel(S.DATASET_FORMULA))) {
									r.push(v.createElement(_.Typography, {
										variant: "body1",
										key: ++o
									}, n.messages.INFO_DATASET_USED + ": " + t.getCurrent(S.DATASET_NAME))), r.push(v.createElement(_.Typography, {
										variant: "body2",
										key: ++o
									}, n.messages.INFO_FORMULA + ": ")), r.push(v.createElement(_.Typography, {
										variant: "body2",
										key: ++o
									}, b.replace(t.getInfoByLabel(S.DATASET_FORMULA), "#", "")));
									var a = t.getCurrentFormulaElements();
									Object.keys(a).length && Object.keys(a).map(function (e, t) {
										return r.push(v.createElement(_.Typography, {
											variant: "caption",
											key: "fe" + t
										}, a[e].name + " = " + e))
									}), i++
								}
							}, l = 0, s = t; l < s.length; l++) {
							a(p = s[l])
						}
						0 === i && r.push(v.createElement(_.Typography, {
							key: ++o
						}, n.messages.INFO_NO_CALCULATED_DATASETS));
						for (var u = [], c = 0, d = t; c < d.length; c++) {
							var p;
							if (0 !== (p = d[c]).dataset_ida) {
								var f = L.default.get(p.dataset_ida);
								b.isUndefined(f.getCurrent(S.DATASET_METADATA)) || b.isUndefined(f.getCurrent(S.DATASET_METADATA)[S.METADATA_CLASS_CALCULATIONS]) || null == f.getCurrent(S.DATASET_METADATA)[S.METADATA_CLASS_CALCULATIONS].value || (u.push(v.createElement(_.Typography, {
									variant: "body1",
									key: ++o
								}, n.messages.INFO_DATASET_USED + ": " + f.getCurrent(S.DATASET_NAME))), u.push(v.createElement(_.Typography, {
									variant: "body2",
									key: ++o,
									dangerouslySetInnerHTML: {
										__html: b.replace(f.getCurrent(S.DATASET_METADATA)[S.METADATA_CLASS_CALCULATIONS].value, /(\r\n|\n\r|\r|\n)/g, "<br />")
									}
								})))
							}
						}
						return u.length && (r.push(v.createElement("hr", {
							key: "listDivider",
							className: "listDivider"
						})), r.push(v.createElement(_.Typography, {
							variant: "body1",
							key: "calculation_characteristics"
						}, n.messages.INFO_CALCULATION_CHARACTERISTICS)), u.map(function (e) {
							r.push(e)
						})), r
					}, e.prototype.sources = function () {
						for (var e = 0, t = (T.default.localization.ida, T.default.localization), a = [], o = 0, n = 0, r = L.default.getDataInfo().getInfoValues().datasetsOrdered; n < r.length; n++) {
							var i = r[n];
							if (0 !== i.dataset_ida) {
								var l = L.default.get(i.dataset_ida);
								a.push(v.createElement(_.Typography, {
									variant: "body1",
									key: ++e
								}, t.messages.INFO_DATASET_USED + ": " + l.getCurrent(S.DATASET_NAME))), a.push(v.createElement(_.Typography, {
									variant: "body2",
									key: ++e
								}, t.messages.SOURCE + ": ")), a.push(v.createElement(_.Typography, {
									variant: "body2",
									key: ++e
								}, l.getCurrent(S.DATASET_SOURCE))), o++
							}
						}
						var s, u = t.messages.LINK_TO_PORTAL + t.codeShort + t.messages.PATH_TO_PORTAL_SOURCES_WITHOUT_LOC;
						return 0 < o ? (s = 1 === o ? t.messages.INFO_SOURCE_PORTRAIT : t.messages.INFO_SOURCES_PORTRAIT, a.push(v.createElement("hr", {
							key: "listdDivider" + ++e,
							className: "listDivider"
						})), a.push(v.createElement(_.Typography, {
							key: ++e,
							variant: "body1"
						}, s)), a.push(v.createElement(_.Typography, {
							key: ++e
						}, v.createElement("a", {
							href: u
						}, t.messages.INFO_CHARACTERISTICS_IN_PORTAL))), a.push(v.createElement("hr", {
							key: "listDivider" + ++e,
							className: "listDivider"
						})), a.push(v.createElement(_.Typography, {
							key: ++e,
							variant: "caption"
						}, t.messages.INFO_SOURCE_WARNING))) : a.push(v.createElement(_.Typography, {
							key: ++e
						}, t.messages.INFO_NO_SOURCES)), a
					}, e.prototype.surveyDates = function () {
						for (var e = 0, t = (T.default.localization.ida, T.default.localization), a = [], o = 0, n = L.default.getDataInfo().getInfoValues().datasetsOrdered; o < n.length; o++) {
							var r = n[o];
							if (0 !== r.dataset_ida) {
								var i = L.default.get(r.dataset_ida);
								a.push(v.createElement(_.Typography, {
									variant: "body1",
									key: ++e
								}, t.messages.INFO_DATASET_USED + ": " + i.getCurrent(S.DATASET_NAME)));
								var l = void 0;
								l = i.getCurrent(S.DATASET_SURVEY_DATE), !1 == !i.getCurrent(S.DATASET_SURVEY_DATE_END) && (l += " - " + i.getCurrent(S.DATASET_SURVEY_DATE_END)), a.push(v.createElement(_.Typography, {
									key: ++e
								}, l))
							}
						}
						return a
					}, e.prototype.spatialInf = function () {
						for (var e = 0, t = (T.default.localization.ida, T.default.localization, []), a = 0, o = L.default.getDataInfo().getInfoValues().datasetsOrdered; a < o.length; a++) {
							var n = o[a];
							if (0 !== n.dataset_ida) {
								0;
								var r = L.default.get(n.dataset_ida);
								t.push(v.createElement(_.Typography, {
									variant: "body1",
									key: ++e
								}, r.getCurrent(S.DATASET_VALID_GEOMETRY)));
								break
							}
						}
						return t
					}, e.prototype.notes = function () {
						for (var e = 0, t = (T.default.localization.ida, T.default.localization), a = [], o = [], n = !1, r = 0, i = L.default.getDataInfo().getInfoValues().datasetsOrdered; r < i.length; r++) {
							var l = i[r];
							if (0 !== l.dataset_ida) {
								var s = L.default.get(l.dataset_ida),
									u = s.getCurrent(S.DATASET_METADATA);
								o = [];
								for (var c = 0, d = Object.keys(u); c < d.length; c++) {
									(h = d[c]) !== S.METADATA_CLASS_CALCULATIONS && (h != S.METADATA_CLASS_EUROSTAT && h != S.METADATA_CLASS_BFS && h != S.METADATA_CLASS_CITIES && h != S.METADATA_CLASS_FOOTNOTE && null !== u[h].value && (o.push(v.createElement(_.Typography, {
										variant: "body2",
										key: ++e
									}, u[h].name)), o.push(v.createElement(_.Typography, {
										variant: "caption",
										key: ++e,
										dangerouslySetInnerHTML: {
											__html: b.replace(u[h].value, /(\r\n|\n\r|\r|\n)/g, "<br />")
										}
									})), n = !0))
								}
								for (var p = 0, f = Object.keys(s.meta_ext); p < f.length; p++) {
									var h = f[p];
									null !== s.meta_ext[h] && ("code_ua" != h && "survey_year_ua" != h && (o.push(v.createElement(_.Typography, {
										variant: "body2",
										key: ++e
									}, t.messages["INFO_" + h.toUpperCase()])), o.push(v.createElement(_.Typography, {
										variant: "caption",
										key: ++e
									}, s.meta_ext[h])), n = !0))
								}
								if (o.length) {
									a.push(v.createElement(_.Typography, {
										variant: "body1",
										key: ++e
									}, t.messages.INFO_DATASET_USED + ": " + s.getCurrent(S.DATASET_NAME)));
									for (var m = 0, g = o; m < g.length; m++) {
										var y = g[m];
										a.push(y)
									}
								}
							}
						}
						return n || a.push(v.createElement(_.Typography, {
							key: ++e
						}, t.messages.NO_DATASET_META_INFO)), a
					}, e.prototype.further = function () {
						var n, t = 0,
							r = (T.default.localization.ida, T.default.localization),
							e = L.default.getDataInfo().getInfoValues(),
							i = E.default.getProject(),
							a = e.datasetsOrdered,
							o = [],
							l = y.default.current;
						if ("" == l.getCurrentMapSpecial()) {
							o.push(v.createElement(_.Typography, {
								variant: "body1",
								key: ++t
							}, r.messages.INFO_GENERAL_INFORMATION));
							for (var s = !1, u = [], c = [], d = 0, p = a; d < p.length; d++) {
								var f = p[d];
								if (0 !== f.dataset_ida) {
									var h = L.default.get(f.dataset_ida).getCurrent(S.DATASET_CLASSIFICATION);
									if ((s = !0) || b.isUndefined(h.value) || null === h.value || !b.isNumber(parseInt(h.value.substr(0, 2)))) s && Object.keys(h).length && Object.keys(h).map(function (e, t) {
										var a = e.substr(0, 2),
											o = i.getCurrentProdimaChapterByChapterId(a.substr(0, 2));
										n = r.messages.LINK_TO_PORTAL + r.codeShort + r.messages.PORTAL_LINK_CLASSIFICATION_WITHOUT_LOC + a + ".html", -1 == c.indexOf(a) && (u.push({
											id: a,
											text: r.messages.INFO_PORTAL_THEME_TEXT + " " + a.substr(0, 2) + " - " + o,
											link: n
										}), c.push(a))
									});
									else {
										var m = h.value.substr(0, 2),
											g = i.getCurrentProdimaChapterByChapterId(m);
										n = r.messages.LINK_TO_PORTAL + r.codeShort + r.messages.PORTAL_LINK_CLASSIFICATION_WITHOUT_LOC + m + ".html", -1 == c.indexOf(m) && (u.push({
											id: m,
											text: r.messages.INFO_PORTAL_THEME_TEXT + " " + m + " - " + g,
											link: n
										}), c.push(m))
									}
								}
							}
							u.length && (this.sortClassificationChapters(u), u.map(function (e) {
								o.push(v.createElement(_.Typography, {
									key: ++t
								}, e.text))
							})), o.push(v.createElement(_.Typography, {
								key: ++t
							}, v.createElement("a", {
								href: this.handleStatatlasLink()
							}, r.messages.INFO_SPATIAL_INF_OVERVIEW_STATATLAS))), o.push(v.createElement(_.Typography, {
								key: ++t
							}, v.createElement("a", {
								href: this.handlePortalLink()
							}, r.messages.INFO_SPATIAL_INF_OVERVIEW_PORTALTEXT))), o.push(v.createElement("hr", {
								key: ++t,
								className: "listDivider"
							})), o.push(v.createElement(_.Typography, {
								variant: "caption",
								key: ++t
							}, r.messages.CLASSIFICATION_WARNING))
						} else o.push(v.createElement(_.Typography, {
							variant: "body1",
							key: ++t
						}, r.messages.INFO_GENERAL_INFORMATION)), o.push(v.createElement(_.Typography, {
							variant: "body2",
							key: ++t,
							dangerouslySetInnerHTML: {
								__html: b.replace(l.getCurrentMapSpecial(), /(\r\n|\n\r|\r|\n)/g, "<br />")
							}
						}));
						return o
					}, e.prototype.sortClassificationChapters = function (e) {
						e.sort(function (e, t) {
							return Number(e.id) < Number(t.id) ? -1 : Number(e.id) > Number(t.id) ? 1 : 0
						})
					}, e.prototype.handleStatatlasLink = function () {
						var e = T.default.localization;
						return u.default.vars.isPreview ? "/frontend/preview/project/13/map/134/loc/" + e.codeShort + "/" : "/maps/13/map/mapIdOnly/134_" + e.codeShort + ".html"
					}, e.prototype.handlePortalLink = function () {
						return T.default.localization.messages.INFO_SPATIAL_INF_OVERVIEW_PORTALLINK
					}, e.prototype.render = function () {
						var a = this,
							o = this.props.classes;
						if (void 0 === l.default.current || void 0 === y.default.current) return null;
						var e = l.default.current.data.properties.modes.info.elements.filter(function (e) {
								return "standard" == e["@id"]
							}),
							n = [];
						if (e.length) {
							var t = e[0];
							return this.modes = t["@modes"].split(","), this.modes.map(function (e, t) {
								n.push(v.createElement(_.ExpansionPanel, {
									key: "mapContentPanel" + t,
									onClick: function () {
										return a.onResize()
									}
								}, v.createElement(_.ExpansionPanelSummary, {
									expandIcon: v.createElement(s.default, null)
								}, v.createElement(_.Typography, {
									className: o.heading
								}, T.default.localization.messages["INFO_" + e.toUpperCase()])), v.createElement(_.ExpansionPanelDetails, null, null !== L.default.getDataInfo() ? a.testContentForFormatting(e) : v.createElement("div", null))))
							}), n.push(v.createElement(c.default, {
								key: "DownloadDocs",
								onResize: this.onResize
							})), n
						}
						return null
					}, e = r([i.observer], e)
				}(v.Component);
			t.default = _.withStyles(function (e) {
				return {
					statBox: {
						marginTop: 0,
						padding: 24
					},
					definitionsListItem: {
						paddingLeft: 0
					},
					definitionsButton: {
						color: "#069",
						"&:visited": {
							color: "#848"
						}
					}
				}
			})(e)
		}).call(this, d(31))
	},
	785: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
				return (o = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			}),
			r = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var i = a(1),
			l = a(20),
			s = a(17),
			u = a(19),
			c = a(29),
			d = a(40),
			p = a(18),
			f = a(28),
			h = a(151),
			m = a(10),
			g = function (a) {
				function e(e) {
					var t = a.call(this, e) || this;
					return t.state = {}, t.state = {
						sources: []
					}, m.default.emitter.on(p.THEMATICALMAP_LOADED, function () {}), m.default.emitter.on(p.LANGUAGE_LOADED, function () {}), t
				}
				return n(e, a), e.prototype.renderAvatarIcon = function (e) {
					switch (e.substr(e.lastIndexOf("."))) {
						case ".xlsx":
						case ".xls":
						case ".excel":
							return i.createElement(d.FileExcel, null);
						case ".pdf":
							return i.createElement(d.FilePdf, null);
						case ".zip":
							return i.createElement(d.FileZip, null);
						case ".doc":
						case ".docx":
						case ".word":
							return i.createElement(d.FileWord, null);
						default:
							return i.createElement(d.FileOutline, null)
					}
				}, e.prototype.renderDownloadLink = function (e) {
					var t = u.default.getProject(),
						a = m.default.paths;
					return a._MEDIA + t.project + a._DS + "xshared" + a._DS + "downloads" + a._DS + e
				}, e.prototype.renderDownloadDocs = function () {
					var a = this;
					return void 0 !== l.default.current && l.default.current.hasDownloadDocs() ? i.createElement("div", null, i.createElement(f.Typography, {
						variant: "body2",
						key: "downloadsHeadline"
					}, s.default.localization.messages.DOWNLOADS_INFO), i.createElement(f.List, {
						dense: !0
					}, l.default.current.getDownloadDocs().map(function (e, t) {
						return i.createElement(f.ListItem, {
							key: "downloadDoc" + t,
							component: "a",
							download: !0,
							href: a.renderDownloadLink(e),
							style: {
								padding: 0
							}
						}, i.createElement(f.ListItemAvatar, null, i.createElement(f.Avatar, null, a.renderAvatarIcon(e))), i.createElement(f.ListItemText, {
							primary: e
						}))
					}))) : i.createElement("div", null, i.createElement(f.Typography, {
						variant: "body2",
						key: "noDownloadsHeadline"
					}, s.default.localization.messages.NO_DOWNLOAD_INFO))
				}, e.prototype.render = function () {
					var e = this,
						t = this.props.classes;
					return i.createElement(f.ExpansionPanel, {
						key: "mapContentPanelDownloadDocs",
						onClick: function () {
							return e.props.onResize()
						}
					}, i.createElement(f.ExpansionPanelSummary, {
						expandIcon: i.createElement(h.default, null)
					}, i.createElement(f.Typography, {
						className: t.heading
					}, s.default.localization.messages.TOOLBAR_DOWNLOAD)), i.createElement(f.ExpansionPanelDetails, null, this.renderDownloadDocs()))
				}, e = r([c.observer], e)
			}(i.Component);
		t.default = f.withStyles(function (e) {
			return {
				sourcesBox: {
					marginTop: 0,
					padding: "10px 24px"
				}
			}
		})(g)
	},
	786: function (e, _, T) {
		"use strict";
		(function (o) {
			var n, t = this && this.__extends || (n = function (e, t) {
				return (n = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				n(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			});
			Object.defineProperty(_, "__esModule", {
				value: !0
			});
			var r = T(1),
				e = T(29),
				i = T(28),
				l = T(40),
				s = T(18),
				u = T(787),
				c = T(788),
				d = T(5),
				p = T(210),
				f = T(58),
				h = T(10),
				m = T(211),
				g = T(19),
				y = T(17),
				v = T(919),
				b = T(20),
				a = i.withStyles(function (e) {
					e.typography, e.mixins, e.spacing, e.palette, e.shadows;
					return {
						root: {},
						tabRoot: {
							minWidth: "auto",
							width: "50%"
						},
						mapLegendDrawer: {
							maxHeight: "100%",
							overflow: "hidden"
						},
						drawerPaper: {
							maxWidth: 400,
							width: 400
						},
						leftContainer: {
							paddingTop: h.default.appHeaderHeight
						},
						chevronButton: {
							display: "inline-block",
							top: h.default.appHeaderHeight - 20,
							position: "absolute",
							right: 0,
							padding: 0,
							minWidth: "auto"
						},
						displayBlock: {
							display: "block"
						},
						displayTable: {
							display: "table"
						},
						displayNone: {
							display: "none"
						},
						toolBar: {
							margin: 0,
							padding: "0 0 0 10px",
							minHeight: "auto"
						},
						paper: {
							marginBottom: "10px"
						},
						textField: {},
						iconButton: {
							minWidth: "auto"
						},
						clearButton: {
							minWidth: "auto",
							padding: 0
						},
						menuButtonContainer: {
							position: "absolute",
							top: h.default.appHeaderHeight,
							left: 10,
							zIndex: 1199
						},
						menuButton: {
							marginBottom: 2
						},
						clickShader: {
							backgroundColor: "green",
							top: 0,
							left: 0,
							right: 0,
							bottom: 0,
							height: "100%",
							width: "100%",
							zIndex: 999,
							position: "absolute",
							opacity: 0,
							filter: "alpha(opacity=0)"
						},
						leftContent: {
							margin: "20px 10px",
							maxHeight: window.innerHeight - h.default.appHeaderHeight - 60,
							overflowY: "auto"
						},
						searchInfo: {
							margin: "20px 0"
						},
						resultContainer: {
							margin: 10,
							paddingTop: 20,
							borderTop: "1px solid #aaa"
						},
						mapList: {
							overflowY: "auto"
						},
						contentTree: {},
						buttonProgress: {}
					}
				})(e.observer(function (a) {
					function e(e) {
						var t = a.call(this, e) || this;
						return t.intervalCount = 0, t.handleTabChange = function (e, t) {
							0 === t ? (h.default.isContentTreeVisible = !0, h.default.isSearchMenuVisible = !1) : 1 === t && (h.default.isSearchMenuVisible = !0, h.default.isContentTreeVisible = !1)
						}, t.handleSnackbarClose = function () {
							h.default.searchResult = []
						}, t.state = {
							isProjectLoaded: !1,
							areProjectsLoaded: !1,
							loading: !1,
							isInputOpen: !1,
							treeData: f.default.rootStructure,
							hasNewMap: !1,
							geometry: 0,
							startSearch: !1,
							lastKeyInput: 0
						}, h.default.emitter.on(s.THEMATICALMAP_LOADED, function () {
							t.setState({
								hasNewMap: !0
							})
						}), t
					}
					return t(e, a), e.prototype.componentWillMount = function () {
						var e = this;
						h.default.emitter.on(s.WINDOW_RESIZED, function () {
							e.defineContainers()
						}), this.defineContainers(), void 0 !== b.default.current && this.setState({
							geometry: b.default.current.geometry
						})
					}, e.prototype.componentDidUpdate = function () {
						this.defineContainers(), this.state.hasNewMap && void 0 !== this.searchInput && null !== this.searchInput && (this.setState({
							hasNewMap: !1
						}), void 0 !== b.default.current && (this.state.geometry !== b.default.current.geometry && (h.default.unitListSearchResult = []), this.setState({
							geometry: b.default.current.geometry
						})))
					}, e.prototype.defineContainers = function () {
						o("#unitList").css("max-height", "18em"), o("#projectList").css("max-height", "18em");
						var e = window.innerHeight - h.default.appHeaderHeight - 100,
							t = e - o("#searchBox").outerHeight() - o("#projectList").outerHeight() - o("#unitList").outerHeight() - 60;
						o("#mapList").css("max-height", t), o("#leftContent").css("max-height", e), o("#leftContainer").css("padding-top", h.default.appHeaderHeight), o("#leftChevron").css("top", h.default.appHeaderHeight)
					}, e.prototype.handleMapContentDrawerAction = function (e, t) {
						t && (h.default.contentWidth = o("#mapContentDrawer").width()), h.default.mapContentDrawer = t
					}, e.prototype.handleInput = function () {
						var e = this,
							t = new Date;
						this.setState({
							lastKeyInput: t.getTime(),
							startSearch: !0
						}), 0 !== this.timer && void 0 !== this.timer || (this.intervalCount = 0, this.timer = setInterval(function () {
							return e.triggerSearches()
						}, 1e3))
					}, e.prototype.triggerSearches = function () {
						var t = void 0 !== this.searchInput && null !== this.searchInput ? this.searchInput.value : "";
						if ((new Date).getTime() - this.state.lastKeyInput < 500 || t.length < 1) return this.intervalCount++, void(4 < this.intervalCount && (clearInterval(this.timer), this.timer = 0, this.setState({
							startSearch: !1
						})));
						if (clearInterval(this.timer), this.timer = 0, this.setState({
								startSearch: !1
							}), 0 < t.length) {
							h.default.searchResult = [];
							var e = y.default.localization;
							h.default.emitter.once(s.SEARCHINDEX_AVAILABLE, function () {
								h.default.foundMapsSearchResult = m.default.compareWithInput(t), h.default.foundMapsSearchResult.length || h.default.searchResult.push({
									el: "maps",
									count: 0 < h.default.foundMapsSearchResult.length
								})
							}), m.default.getIndex(e.codeShort);
							var a = g.default.getProject().geometries[b.default.current.geometry].vg;
							h.default.emitter.once(s.GEOMETRYLABELS_AVAILABLE, function (e) {
								p.default.provideSearchResult(a, t)
							}), p.default.has(a)
						} else f.default.clearFlags(), p.default.clearSearchResult()
					}, e.prototype.clearInput = function () {
						h.default.searchResult = [], void 0 !== this.searchInput && (this.searchInput.value = "", h.default.unitListSearchResult = h.default.projectListSearchResult = h.default.foundMapsSearchResult = [])
					}, e.prototype.renderClickShader = function () {
						this.props.classes;
						return h.default.treeDrawerOpen, null
					}, e.prototype.renderClearButton = function () {
						var e = this,
							t = this.props.classes;
						return r.createElement(i.Button, {
							className: t.clearButton,
							onClick: function () {
								return e.clearInput()
							}
						}, r.createElement(l.Close, null))
					}, e.prototype.renderLoadingAnimation = function () {
						this.props.classes;
						return !0 === this.state.startSearch ? r.createElement(i.CircularProgress, {
							size: 24,
							color: "secondary"
						}) : null
					}, e.prototype.renderContentTree = function () {
						var e = this.props.classes;
						return h.default.isContentTreeVisible ? r.createElement("div", null, r.createElement(c.default, {
							className: e.contentTree,
							id: "contentTree"
						})) : null
					}, e.prototype.renderSearchMenu = function () {
						var t = this,
							e = this.props.classes;
						return h.default.isSearchMenuVisible ? r.createElement("div", null, r.createElement("div", {
							id: "searchBox"
						}, r.createElement(i.Input, {
							id: "with-placeholder",
							style: {
								width: "100%"
							},
							placeholder: y.default.localization.messages.SEARCHITEM,
							onKeyUp: function (e) {
								return t.handleInput()
							},
							inputRef: function (e) {
								null !== (t.searchInput = e) && e.focus()
							},
							endAdornment: r.createElement(i.InputAdornment, {
								position: "end"
							}, this.renderLoadingAnimation(), this.renderClearButton())
						}), r.createElement(i.Typography, {
							variant: "caption",
							component: "p",
							className: e.searchInfo
						}, y.default.localization.messages.SEARCH_MAP_INFO)), h.default.unitListSearchResult.length ? r.createElement("div", {
							id: "unitList",
							className: e.resultContainer
						}, r.createElement(u.default, null)) : null, h.default.foundMapsSearchResult.length ? r.createElement("div", {
							id: "mapList",
							className: d(e.mapList, e.resultContainer)
						}, r.createElement(v.default, null)) : null, h.default.unitListSearchResult.length ? null : r.createElement("div", {
							id: "unitList",
							className: e.resultContainer
						}, r.createElement(u.default, null)), h.default.foundMapsSearchResult.length ? null : r.createElement("div", {
							id: "mapList",
							className: d(e.mapList, e.resultContainer)
						}, r.createElement(v.default, null))) : null
					}, e.prototype.render = function () {
						var t = this,
							e = this.state,
							a = (e.loading, e.isInputOpen),
							o = this.props.classes;
						return r.createElement("div", null, r.createElement("div", {
							className: o.menuButtonContainer,
							style: {
								top: h.default.appHeaderHeight + 10
							}
						}, r.createElement(i.Fab, {
							size: "small",
							className: d(o.menuButton, a ? o.displayNone : o.displayBlock),
							color: "primary",
							"aria-label": "Menu",
							onClick: function (e) {
								t.handleTabChange(e, 0), h.default.treeDrawerOpen = !0, h.default.emitter.emit(s.AUTOMATION_STOPPED)
							}
						}, r.createElement(l.FileTree, null)), r.createElement(i.Fab, {
							size: "small",
							className: d(o.menuButton, a ? o.displayNone : o.displayBlock),
							color: "primary",
							"aria-label": "Menu",
							onClick: function (e) {
								t.handleTabChange(e, 1), h.default.treeDrawerOpen = !0, h.default.emitter.emit(s.AUTOMATION_STOPPED)
							}
						}, r.createElement(l.Magnify, null)), h.default.animationAvailable && h.default.automationControlsMinimized ? r.createElement(i.Fab, {
							size: "small",
							disabled: h.default.animationStarted,
							title: y.default.localization.messages.MAP_AUTOMATION_HEADLINE,
							className: d(o.menuButton, a ? o.displayNone : o.displayBlock),
							color: "primary",
							"aria-label": "Video",
							onClick: function (e) {
								h.default.automationControlsMinimized = !1, h.default.emitter.emit(s.AUTOMATION_STARTED)
							}
						}, r.createElement(l.ControlsPlay, null)) : null), r.createElement(i.Drawer, {
							variant: "persistent",
							anchor: "left",
							open: h.default.treeDrawerOpen,
							className: o.treeDrawer,
							id: "treeDrawer",
							classes: {
								paper: o.drawerPaper
							}
						}, r.createElement("div", {
							id: "leftContainer",
							className: o.leftContainer
						}, r.createElement("div", {
							id: "leftTabs"
						}, r.createElement(i.Tabs, {
							style: {
								paddingRight: 40
							},
							value: h.default.isContentTreeVisible ? 0 : 1,
							onChange: this.handleTabChange
						}, r.createElement(i.Tab, {
							classes: {
								root: o.tabRoot
							},
							label: y.default.localization.messages.BUTTON_THEME
						}), r.createElement(i.Tab, {
							classes: {
								root: o.tabRoot
							},
							label: y.default.localization.messages.BUTTON_SEARCH
						})), r.createElement("div", {
							className: o.chevronButton,
							id: "leftChevron"
						}, r.createElement(i.Button, {
							style: {
								minWidth: "auto",
								marginTop: 3
							},
							onClick: function () {
								return h.default.treeDrawerOpen = !1
							}
						}, r.createElement(l.ChevronLeft, null)))), r.createElement("div", {
							id: "leftContent",
							className: o.leftContent
						}, this.renderContentTree(), this.renderSearchMenu()))), this.renderClickShader(), r.createElement(i.Snackbar, {
							open: 0 < h.default.searchResult.length,
							onClose: this.handleSnackbarClose,
							message: r.createElement("div", null, h.default.searchResult.map(function (e) {
								return "maps" !== e.el || e.count ? "units" !== e.el || e.count ? void 0 : r.createElement("div", {
									key: "units"
								}, y.default.localization.messages.NO_SPATIAL_UNITS_FOUND_YET) : r.createElement("div", {
									key: "maps"
								}, y.default.localization.messages.NO_MAPS_FOUND_YET)
							}))
						}))
					}, e
				}(r.Component)));
			_.default = a
		}).call(this, T(31))
	},
	787: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
				return (o = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			}),
			r = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var i = a(1),
			l = a(28),
			s = a(18),
			u = a(29),
			c = a(17),
			d = a(10),
			p = a(40),
			f = function (a) {
				function e(e) {
					var t = a.call(this, e) || this;
					return t.state = {
						anchorEl: null
					}, t.handleInfoClick = function (e) {
						t.setState({
							anchorEl: e.currentTarget
						})
					}, t.handleInfoClose = function () {
						t.setState({
							anchorEl: null
						})
					}, t
				}
				return n(e, a), e.prototype.handleUnitClick = function (e, t) {
					d.default.emitter.emit(s.UNIT_WAS_CLICKED_IN_UNITLIST, {
						unit_ida: t
					}), d.default.treeDrawerOpen = !1
				}, e.prototype.renderList = function () {
					var a = this,
						e = this.props.classes;
					return i.createElement(l.List, {
						component: "ul",
						className: e.units
					}, d.default.unitListSearchResult.map(function (t, e) {
						return i.createElement(l.ListItem, {
							key: "label" + t.ida
						}, i.createElement(l.ListItemText, {
							primary: t.name
						}), i.createElement(l.ListItemSecondaryAction, null, i.createElement(l.IconButton, {
							"aria-label": "UnitClick",
							onClick: function (e) {
								return a.handleUnitClick(e, t.ida)
							}
						}, i.createElement(p.MapMarker, null))))
					}))
				}, e.prototype.renderHint = function () {
					var e = this.state.anchorEl,
						t = this.props.classes;
					return i.createElement("span", null, " ", i.createElement(p.InformationOutline, {
						onClick: this.handleInfoClick
					}), i.createElement(l.Popover, {
						open: Boolean(e),
						anchorEl: e,
						onClose: this.handleInfoClose,
						anchorOrigin: {
							vertical: "bottom",
							horizontal: "center"
						},
						transformOrigin: {
							vertical: "top",
							horizontal: "center"
						}
					}, i.createElement(l.Typography, {
						variant: "body2",
						className: t.typography
					}, c.default.localization.messages.SPATIAL_UNITS_SEARCH_INFO)))
				}, e.prototype.render = function () {
					var e = this.props.classes,
						t = 0 < d.default.unitListSearchResult.length;
					return i.createElement("div", null, i.createElement(l.Typography, {
						variant: "h6",
						className: t ? e.active : e.inactive
					}, c.default.localization.messages.SPATIAL_UNITS, t ? null : this.renderHint()), t ? this.renderList() : null)
				}, e = r([u.observer], e)
			}(i.Component);
		t.default = l.withStyles(function (e) {
			return {
				typography: {
					margin: 2 * e.spacing.unit
				},
				active: {
					color: "#000000"
				},
				inactive: {
					color: "#CCCCCC"
				},
				units: {
					overflowY: "auto",
					maxHeight: 200
				}
			}
		})(f)
	},
	788: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
			return (o = Object.setPrototypeOf || {
					__proto__: []
				}
				instanceof Array && function (e, t) {
					e.__proto__ = t
				} || function (e, t) {
					for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
				})(e, t)
		}, function (e, t) {
			function a() {
				this.constructor = e
			}
			o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
		});
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var r = a(1),
			i = a(28),
			l = a(212),
			s = a(17),
			u = a(789),
			c = a(58),
			d = a(19),
			p = a(40),
			f = function (e) {
				function t() {
					return null !== e && e.apply(this, arguments) || this
				}
				return n(t, e), t.prototype.handleProjectClick = function (e, t) {
					location.href = _WWW + "maps/" + t.ida + "/map/mapIdOnly/0_de.html"
				}, t.prototype.renderHeader = function () {
					return r.createElement("div", null, r.createElement(i.Typography, {
						variant: "h6",
						color: "inherit"
					}, s.default.localization.messages.FURTHER_PROJECTS), r.createElement(i.Fade, { in: l.default.pending,
						unmountOnExit: !0
					}, r.createElement(i.CircularProgress, null)))
				}, t.prototype.renderNoProjectAvailable = function () {
					return r.createElement(i.List, null, r.createElement(i.ListItem, null, r.createElement(i.ListItemText, {
						primary: "Leider wurden keine weiteren Projekte gefunden, die verlinkt werden können."
					})))
				}, t.prototype.renderProjects = function () {
					var a = this;
					return r.createElement(i.List, {
						component: "nav"
					}, l.default.getProjects().map(function (t) {
						return r.createElement(i.ListItem, {
							key: t.key,
							button: !0,
							component: "a",
							onClick: function (e) {
								return a.handleProjectClick(e, {
									ida: t.key
								})
							}
						}, r.createElement(i.ListItemText, {
							primary: t.languages[s.default.localization.ida].name
						}), r.createElement(i.ListItemSecondaryAction, null, r.createElement(i.IconButton, {
							"aria-label": "ProjectClick",
							onClick: function (e) {
								return a.handleProjectClick(e, {
									ida: t.key
								})
							}
						}, r.createElement(p.OpenInNew, null))))
					}))
				}, t.prototype.render = function () {
					return r.createElement("div", null, r.createElement(i.Typography, {
						variant: "h6",
						color: "inherit"
					}, void 0 === d.default.getProject().currentLanguage() ? null : d.default.getProject().currentLanguage().name), r.createElement(i.Fade, { in: !c.default.rootStructure.length,
						unmountOnExit: !0
					}, r.createElement(i.CircularProgress, null)), r.createElement(u.StructureTree, {
						data: c.default.rootStructure
					}))
				}, t
			}(r.Component);
		t.default = f
	},
	789: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
				return (o = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			}),
			r = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var i = a(1),
			l = a(790),
			s = a(918),
			u = a(29),
			c = a(58),
			d = a(122),
			p = a(17),
			f = a(40),
			h = a(10),
			m = a(28);
		s.default.Header = u.observer(function (e) {
			e.style;
			var t = e.node,
				a = "";
			return a = !t.flag || 0 === t.flag || t.map_ida ? "" : " (" + t.flag + ")", t.map_title ? h.default.isContentTreeVisible || h.default.isSearchMenuVisible ? i.createElement("span", {
				"data-for": "map_title",
				style: {
					fontWeight: t.colorized ? "bold" : "normal"
				}
			}, i.createElement(m.Typography, {
				component: "span",
				variant: "body1"
			}, t.colorized ? i.createElement(f.Map, null) : i.createElement(f.MapOutline, null), i.createElement("span", null, t.translation[p.default.localization.ida].name, a))) : null : h.default.isContentTreeVisible || h.default.isSearchMenuVisible ? i.createElement("span", null, t.toggled ? i.createElement(f.ChevronDown, null) : i.createElement(f.ChevronRight, null), i.createElement(m.Typography, {
				component: "span",
				variant: 1 === t.parents.length ? "subtitle1" : "body2",
				style: {
					fontWeight: t.colorized ? "bold" : "normal"
				}
			}, t.translation[p.default.localization.ida], h.default.isSearchMenuVisible ? a : null)) : null
		});
		var g = function (a) {
			function e(e) {
				var t = a.call(this, e) || this;
				return t._isMounted = !1, t.state = {
					treeData: c.default.rootStructure
				}, t.onToggle = t.onToggle.bind(t), t.handleMouseOver = t.handleMouseOver.bind(t), t
			}
			return n(e, a), e.prototype.componentDidMount = function () {
				var e = this;
				h.default.emitter.on("STRUCTURE_UPDATED", function () {
					e.update()
				}), this._isMounted = !0
			}, e.prototype.componentWillUnmount = function () {
				this._isMounted = !1
			}, e.prototype.update = function () {
				this._isMounted && this.setState({
					treeData: c.default.rootStructure
				})
			}, e.prototype.onToggle = function (e, t) {
				var a = this.state.cursor;
				a && (a.active = !1), c.default.autoToggleTree(e, t, !0), this.setState({
					cursor: e
				})
			}, e.prototype.handleMouseOver = function (e) {
				e.map_ida && c.default.getMapTitleForNode(e)
			}, e.prototype.render = function () {
				return i.createElement("div", {
					className: "tree"
				}, i.createElement(l.default, {
					data: this.state.treeData,
					decorators: s.default,
					onToggle: this.onToggle,
					handleMouseOver: this.handleMouseOver
				}), i.createElement(d, {
					id: "map_title"
				}))
			}, e = r([u.observer], e)
		}(i.Component);
		t.StructureTree = g
	},
	790: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
			return (o = Object.setPrototypeOf || {
					__proto__: []
				}
				instanceof Array && function (e, t) {
					e.__proto__ = t
				} || function (e, t) {
					for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
				})(e, t)
		}, function (e, t) {
			function a() {
				this.constructor = e
			}
			o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
		});
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var u = a(1),
			r = a(2),
			c = a(791),
			i = a(915),
			l = a(916),
			s = a(917),
			d = function (t) {
				function e(e) {
					return t.call(this, e) || this
				}
				return n(e, t), e.prototype.render = function () {
					var t = this,
						e = this.props,
						a = e.animations,
						o = e.decorators,
						n = e.data,
						r = e.onToggle,
						i = e.handleMouseOver,
						l = e.style,
						s = n;
					return Array.isArray(s), u.createElement("ul", {
						style: l.tree.base,
						ref: function (e) {
							return t.treeBaseRef = e
						}
					}, s.map(function (e, t) {
						return u.createElement(c.default, {
							animations: a,
							decorators: o,
							key: e.id || t,
							node: e,
							onToggle: r,
							handleMouseOver: i,
							style: l.tree.node
						})
					}))
				}, e
			}(u.Component);
		d.propTypes = {
			style: r.object,
			data: r.oneOfType([r.object, r.array]).isRequired,
			animations: r.oneOfType([r.object, r.bool]),
			onToggle: r.func,
			decorators: r.object
		}, d.defaultProps = {
			style: l.default,
			animations: s.default,
			decorators: i.default
		}, t.default = d
	},
	791: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
				return (o = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			}),
			u = this && this.__assign || function () {
				return (u = Object.assign || function (e) {
					for (var t, a = 1, o = arguments.length; a < o; a++)
						for (var n in t = arguments[a]) Object.prototype.hasOwnProperty.call(t, n) && (e[n] = t[n]);
					return e
				}).apply(this, arguments)
			},
			i = this && this.__rest || function (e, t) {
				var a = {};
				for (var o in e) Object.prototype.hasOwnProperty.call(e, o) && t.indexOf(o) < 0 && (a[o] = e[o]);
				if (null != e && "function" == typeof Object.getOwnPropertySymbols) {
					var n = 0;
					for (o = Object.getOwnPropertySymbols(e); n < o.length; n++) t.indexOf(o[n]) < 0 && (a[o[n]] = e[o[n]])
				}
				return a
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var c = a(1),
			r = a(2),
			l = a(152),
			d = a(910),
			s = function (a) {
				function s(e) {
					var t = a.call(this, e) || this;
					return t.onClick = t.onClick.bind(t), t.onMouseOver = t.onMouseOver.bind(t), t
				}
				return n(s, a), s.prototype.onClick = function () {
					var e = this.props,
						t = e.node,
						a = e.onToggle,
						o = t.toggled;
					a && a(t, !o)
				}, s.prototype.onMouseOver = function () {
					var e = this.props,
						t = e.node,
						a = e.handleMouseOver;
					a && a(t)
				}, s.prototype.animations = function () {
					var e = this.props,
						t = e.animations,
						a = e.node;
					if (!1 === t) return !1;
					var o = u({}, t, a.animations);
					return {
						toggle: o.toggle(this.props),
						drawer: o.drawer(this.props)
					}
				}, s.prototype.decorators = function () {
					var e = this.props,
						t = e.decorators,
						a = e.node.decorators || {};
					return u({}, t, a)
				}, s.prototype.render = function () {
					var t = this,
						e = this.props.style,
						a = this.decorators(),
						o = this.animations();
					return c.createElement("li", {
						ref: function (e) {
							return t.topLevelRef = e
						},
						style: e.base
					}, this.renderHeader(a, o), this.renderDrawer(a, o))
				}, s.prototype.renderDrawer = function (e, t) {
					var a = this,
						o = this.props.node.toggled;
					if (!t && !o) return null;
					if (!t && o) return this.renderChildren(e);
					var n = t.drawer,
						r = (n.animation, n.duration, i(n, ["animation", "duration"]));
					return c.createElement(l.VelocityTransitionGroup, u({}, r, {
						ref: function (e) {
							return a.velocityRef = e
						}
					}), o ? this.renderChildren(e) : null)
				}, s.prototype.renderHeader = function (e, t) {
					var a = this.props,
						o = a.node,
						n = a.style;
					return c.createElement(d.default, {
						animations: t,
						decorators: e,
						node: u({}, o),
						onClick: this.onClick,
						onMouseOver: this.onMouseOver,
						style: n
					})
				}, s.prototype.renderChildren = function (e) {
					var a = this,
						t = this.props,
						o = t.animations,
						n = t.decorators,
						r = t.node,
						i = t.style;
					if (r.loading) return this.renderLoading(e);
					var l = r.children;
					return Array.isArray(l) || (l = l ? [l] : []), c.createElement("ul", {
						style: i.subtree,
						ref: function (e) {
							return a.subtreeRef = e
						}
					}, l.map(function (e, t) {
						return c.createElement(s, u({}, a._eventBubbles(), {
							animations: o,
							decorators: n,
							key: e.id || t,
							node: e,
							ref: function (e) {
								return a.childRef = e
							},
							style: i
						}))
					}))
				}, s.prototype.renderLoading = function (e) {
					var t = this.props.style;
					return c.createElement("ul", null, c.createElement("li", null, c.createElement(e.Loading, {
						style: t.loading
					})))
				}, s.prototype._eventBubbles = function () {
					var e = this.props;
					return {
						onToggle: e.onToggle,
						handleMouseOver: e.handleMouseOver
					}
				}, s
			}(c.Component);
		s.propTypes = {
			style: r.object.isRequired,
			node: r.object.isRequired,
			decorators: r.object.isRequired,
			animations: r.oneOfType([r.object, r.bool]).isRequired,
			onToggle: r.func
		}, t.default = s
	},
	80: function (e, t, a) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var o = r(a(1)),
			n = r(a(36));

		function r(e) {
			return e && e.__esModule ? e : {
				default: e
			}
		}
		t.default = function (t) {
			var e = function (e) {
				return o.default.createElement(n.default, e, t)
			};
			return e.muiName = "SvgIcon", e
		}
	},
	83: function (e, t, n) {
		"use strict";
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var r = n(10),
			i = n(39),
			l = n(18),
			s = n(17),
			a = function () {
				function e() {
					this.designs = [], this.currentDesign = {
						ida: null,
						data: null
					}, this.pending = !1, this.queue = []
				}
				return e.prototype.loadDesign = function (t) {
					var a = this;
					if (!0 === this.pending) return this.queue.indexOf(t) < 0 && this.queue.push(t), !1;
					this.pending = !0;
					var e = i.default.create({
						baseURL: _WWW,
						responseType: "json"
					});
					if (this.hasDesign(t)) return this.finishLoading(t), !0;
					var o = {
						id: t.toString(),
						type: l.LOADING_TYPE_DESIGN,
						message: s.default.localization.messages.LOADING_HEADER_IMAGE
					};
					return r.default.setIsLoading(o), e.get(r.default.paths._JSON + "design/" + t + ".json?" + n.h, {
						onDownloadProgress: function (e) {
							o.percent = Math.round(100 * e.loaded / e.total), r.default.setLoadingPercent(o)
						}
					}).then(function (e) {
						a.currentDesign = {
							ida: t,
							data: e.data.design
						}, a.hasDesign(t) || a.designs.push(a.currentDesign), a.finishLoading(t)
					}).catch(function (e) {
						return a.finishLoading(t), !1
					}), !1
				}, e.prototype.finishLoading = function (e) {
					-1 < this.queue.indexOf(e) && this.queue.splice(this.queue.indexOf(e), 1), this.pending = !1, r.default.setNotLoading({
						id: e.toString(),
						type: l.LOADING_TYPE_DESIGN
					}), r.default.emitter.emit(l.DESIGN_LOADED), 0 < this.queue.length ? this.loadDesign(this.queue.shift()) : r.default.emitter.emit(l.DESIGN_QUEUE_FINISHED)
				}, e.prototype.clearQueue = function () {
					this.queue = []
				}, e.prototype.hasDesign = function (t) {
					return 0 < this.designs.filter(function (e) {
						return e.ida == t
					}).length
				}, Object.defineProperty(e.prototype, "current", {
					get: function () {
						return this.currentDesign.data ? this.currentDesign : null
					},
					enumerable: !0,
					configurable: !0
				}), e.prototype.getDesign = function (t) {
					if (this.hasDesign(t)) {
						var e = this.designs.filter(function (e) {
							return e.ida == t
						});
						return r.default.emitter.emit(l.DESIGN_LOADED), e[0]
					}
					this.loadDesign(t)
				}, e
			}();
		t.default = new a
	},
	84: function (e, t, n) {
		"use strict";
		(function (f) {
			Object.defineProperty(t, "__esModule", {
				value: !0
			});
			var a = n(10),
				h = n(48),
				o = n(48),
				i = n(17),
				l = n(19),
				s = n(20),
				u = n(148),
				m = n(30),
				g = n(377);
			f.fn.exists = function () {
				return 0 < this.length
			}, f.fn.jsonObjCount = function () {
				var a = 0;
				return f.each(this, function (e, t) {
					f.each(t, function () {
						a++
					})
				}), a
			};
			var e = function () {
				function e() {
					this.sectorHelperDegrees = [0, 45, 90, 135, 180, 225, 270, 315, 360], this.barScaleDivider = .825, this.symbolScaleDivider = 8750, this.geoSystemDimensionExponent = .625, this.arealOpacityRealValue = 200, this.colorFieldWidth = 1.6, this.colorFieldHeight = 1, this.colorLineHeight = .2, this.colorFieldWidthInPx = 32, this.colorFieldHeightInPx = 20, this.colorLineHeightInPx = 4, this.colorFieldCssFrame = 10, this.arealOpacity = this.arealOpacityRealValue / 255
				}
				return e.prototype.isNull = function (e) {
					return null === e && "object" == typeof e
				}, e.prototype.isZero = function (e) {
					return 0 === e && "number" == typeof e
				}, e.prototype.isNaN = function (e) {
					return !parseFloat(e) && 0 != e && "number" == typeof e
				}, e.prototype.isEmpty = function (e) {
					return "" === e && "string" == typeof e
				}, e.prototype.isUndefined = function (e) {
					return void 0 === e && void 0 === e
				}, e.prototype.ucFirst = function (e) {
					return e.charAt(0).toUpperCase() + e.slice(1)
				}, e.prototype.deg2rad = function (e) {
					return Math.PI / (180 / e)
				}, e.prototype.getViewBox = function (e) {
					var t = f("#" + e)[0].getAttribute("viewBox");
					return t = t.split(" "), {
						x: Number(t[0]),
						y: Number(t[1]),
						width: Number(t[2]),
						height: Number(t[3])
					}
				}, e.prototype.setViewBox = function (e, t) {
					f("#" + e)[0].setAttribute("viewBox", t.x + " " + t.y + " " + t.width + " " + t.height)
				}, e.prototype.getRadius = function (e, t, a, o) {
					if (!1 !== this.isNaN(e) || 0 == e) return 0;
					e = Math.abs(e), 1 == t || 4 == t ? e = Math.sqrt(e / (a / 100) / Math.PI) : 2 == t ? e = Math.sqrt(e / (a / 100) / 2) / Math.SQRT2 : 3 == t && (e = Math.sqrt(e / (a / 100) * 4 / Math.sqrt(3)) / 2);
					var n = this.scaleSymbol(e, !1);
					if (4 == t) {
						var r = s.default.current.visualizations.sym.spec.vectorSymbol;
						n /= u.default.get(r).size / 2
					}
					return !1 === o ? n : this.getRadiusForCurrentScale(n)
				}, e.prototype.getRadiusForCurrentScale = function (e) {
					return e / (a.default.actualZoomFactor / 100)
				}, e.prototype.scaleSymbol = function (e, t) {
					return void 0 === t && (t = !1), e = Math.pow(a.default.initialViewBox.width * a.default.initialViewBox.height, this.geoSystemDimensionExponent) * e / this.symbolScaleDivider, t ? e / this.barScaleDivider : e
				}, e.prototype.betterRounding = function (e, t) {
					if (this.isNull(e) || !1 === e) return e;
					(t < 0 || this.isUndefined(t)) && (t = 0);
					var a = e < 0,
						o = Math.round(Math.abs(e) * Math.pow(10, t)) / Math.pow(10, t);
					return a && 0 !== o && (o *= -1), o
				}, e.prototype.calculateMinimumGap = function (e) {
					return (this.isUndefined(e) || "number" != typeof e) && (e = 0), 1 / Math.pow(10, e)
				}, e.prototype.getDecimalSymbol = function () {
					return i.default.localization.properties.DECIMAL_DELIM
				}, e.prototype.get1000separator = function () {
					return i.default.localization.properties.THOUSAND_DELIM
				}, e.prototype.decreaseValue = function (e, t) {
					return this.isNull(e) || !1 === e ? e : ((this.isUndefined(t) || "number" != typeof t) && (t = 0), e - this.calculateMinimumGap(t))
				}, e.prototype.showValue = function (e, t, a, o) {
					if (void 0 === t && (t = 0), void 0 === a && (a = !0), void 0 === o && (o = !1), this.isNull(e) || !1 === e) return "";
					(this.isUndefined(t) || "number" != typeof t) && (t = 0), (this.isUndefined(a) || "boolean" != typeof a) && (a = !0), (this.isUndefined(o) || "boolean" != typeof o) && (o = !1);
					var n = "";
					for ((n = (e = this.betterRounding(e, t)).toString().replace(".", this.getDecimalSymbol())).split(this.getDecimalSymbol()).length < 2 && (n += this.getDecimalSymbol()); n.split(this.getDecimalSymbol())[1].length < t;) n += "0";
					if (n.substr(n.length - 1, 1) === this.getDecimalSymbol() && (n = n.substr(0, n.length - 1)), !1 === o) {
						var r = !1,
							i = n.split(this.getDecimalSymbol());
						Number(i[0]) < 0 ? (r = !0, i[0] = Number(-1 * Number(i[0])).toString()) : r = !1;
						for (var l = ""; 0 < i[0].length;) l = i[0].substr(Number(i[0].length) - 1, 1) + l, i[0] = i[0].substr(0, Number(i[0].length) - 1), (Number(l.length) - 3) % 4 == 0 && 0 < i[0].length && (l = this.get1000separator() + l);
						n = 1 < i.length && 0 < i[1].length && !1 === this.isNaN(i[1]) ? l + this.getDecimalSymbol() + i[1] : l, r && (n = "-" + n)
					}
					if (a) {
						if (0 < t)
							for (;
								"0" === n.substr(n.length - 1, 1);) n = n.substr(0, n.length - 1);
						n.substr(n.length - 1, 1) === this.getDecimalSymbol() && (n = n.substr(0, n.length - 1))
					}
					return n
				}, e.prototype.createVirtualDataset0 = function (e) {
					m.bajoodooLog("helpers.createVirtualDataset0", e);
					var n = this,
						t = e;
					if (0 != this.isUndefined(t)) return !1;
					h.default.deleteVirtualDataset();
					var a = new g.Dataset({
						ida: 0,
						info: null,
						languages: [],
						meta_ext: {},
						standardVis: {},
						values: {},
						type: null,
						isLoading: !1
					});
					t.spec.decimal_places_sectors;
					0 < f(t.datasets).length ? 0 == t.spec.is_related ? a.type = "total" : a.type = "remainder" : f(t.datasets).length < 1 && (a.type = "total");
					var o = new Array;
					if (f.each(t.sectors, function (e, t) {
							0 != t.dataset && o.push(t.dataset)
						}), f.each(o, function (e, t) {
							f.each(h.default.get(t).values, function (e, t) {
								n.isUndefined(a.values[e]) ? (a.values[e] = {
									value: t.value,
									pseudoId: t.pseudoId
								}, n.isNull(t.value) ? a.values[e].value = 0 : !1 !== t.value && (a.values[e].value = a.values[e].value)) : !1 !== a.values[e].value && !1 === n.isNull(a.values[e].value) && !1 === n.isNull(t.value) && (a.values[e].value -= -t.value)
							})
						}), "remainder" == a.type) {
						var r = parseInt(Object.keys(t.datasets)[0]),
							i = h.default.get(r);
						f.each(i.values, function (e, t) {
							!1 === a.values[e].value || !1 === t.value ? a.values[e].value = !1 : n.isNull(a.values[e].value) || n.isNull(t.value) ? a.values[e].value = null : a.values[e].value = t.value - a.values[e].value
						})
					} else {
						var l = new Array;
						for (var s in a.values) l.push([s, a.values[s].value, a.values[s].pseudoId]);
						for (var u in l.sort(function (e, t) {
								return e[1] - t[1]
							}), a.values = new Object, l) a.values[l[u][0]] = {
							value: l[u][1],
							pseudoId: l[u][2]
						}
					}
					var c = !1,
						d = !1;
					a.info = {
						total: 0,
						totalnopseudo: 0,
						pseudocount: 0,
						valuecount: 0,
						min: null,
						max: null,
						absMin: null,
						absMax: null,
						avg: null,
						avgnopseudo: null,
						crosses_0: !1,
						hasNegative: !1,
						given_total_or_mean: null,
						isAbsolute: !0
					};
					var p = a.info;
					return f.each(a.values, function (e, t) {
						var a = t.value,
							o = Math.abs(a);
						a < 0 ? c = !0 : 0 < a && (d = !0), t.pseudoId && p.pseudocount++, !1 !== a && null !== a && (p.valuecount++, p.total -= -a, t.pseudoId && (p.totalnopseudo -= a), (n.isNull(p.min) || a < p.min) && (p.min = a), (n.isNull(p.max) || a > p.max) && (p.max = a), (n.isNull(p.absMin) || o < p.absMin) && (p.absMin = o), (n.isNull(p.absMax) || o > p.absMax) && (p.absMax = o))
					}), p.totalnopseudo -= -p.total, p.hasNegative = c, d && c && (p.crosses_0 = !0), h.default.addVirtualDataset(a), !0
				}, e.prototype.getVisOrder = function () {
					return {
						areal: ["choro", "quali"],
						symbol: ["sym", "sect", "bar"]
					}
				}, e.prototype.getPrivacy = function (e, t, a) {
					void 0 === e && (e = !1), void 0 === t && (t = !1), void 0 === a && (a = !1);
					var o = i.default.localization,
						n = (s.default.current.properties.missing_data_label, "");
					return !0 === e ? n = o.messages.PRIVACY_SHORT : !0 === t ? n = o.messages.PRIVACY_LONG + " [" + o.messages.PRIVACY_SHORT + "]" : !0 === a && (n = o.messages.PRIVACY_SHORT + " = " + o.messages.PRIVACY_LONG), n
				}, e.prototype.tryToReformatToNumber = function (e) {
					var t = i.default.localization,
						a = l.default.getProject();
					if (a.isMapsProject()) var o = s.default.current.properties.missing_data_label,
						n = t.properties.MISSING_DATA_LABELS[o].NAME,
						r = t.properties.MISSING_DATA_LABELS[o].SHORT;
					else if (a.isIndicatorProject()) t = i.default.localization;
					return e == g.Dataset.DATA_PROTECTION_IDENTIFIER.toString() ? e : !a.isMapsProject() || e != n && e != r ? a.isIndicatorProject() && "NO_DATA" == e ? e : ("." != this.get1000separator() && (e = e.replace(this.get1000separator(), "")), isNaN(parseFloat(e.replace(this.getDecimalSymbol(), "."))) ? e : parseFloat(e.replace(this.getDecimalSymbol(), "."))) : e
				}, e.prototype.getNoData = function (e, t, a) {
					void 0 === e && (e = !1), void 0 === t && (t = !1), void 0 === a && (a = !1);
					var o = i.default.localization,
						n = s.default.current.properties.missing_data_label,
						r = "";
					return !0 === e ? r = o.properties.MISSING_DATA_LABELS[n].SHORT : !0 === t ? r = o.properties.MISSING_DATA_LABELS[n].NAME + " [" + o.properties.MISSING_DATA_LABELS[n].SHORT + "]" : !0 === a && (r = o.properties.MISSING_DATA_LABELS[n].SHORT + " = " + o.properties.MISSING_DATA_LABELS[n].NAME), r
				}, e.prototype.getColorFieldOrLine = function (e, t, a, o) {
					void 0 === t && (t = void 0), void 0 === a && (a = !1), void 0 === o && (o = 0);
					var n = this.isUndefined(t) ? this.arealOpacity : t / 100,
						r = '<div data-dataset-ida="' + o + '" data-min-value="undefined" data-max-value="undefined" class="color' + (!0 === a ? "Line" : "Field") + ' gradient" style="width: ' + this.colorFieldWidthInPx + "px; height: " + (!0 === a ? this.colorLineHeightInPx : this.colorFieldHeightInPx) + 'px;"><canvas class="color_' + e + '"  style="border: 1px solid #444; opacity: ' + n + "; filter: alpha(opacity=" + Math.round(100 * n) + ');"></canvas></div>';
					return f(r)
				}, e.prototype.getColorRemainderValue = function () {
					return "#_R_#"
				}, e.prototype.getInfoLabel = function (e) {
					var t, a = i.default.localization;
					return 1 === e ? t = a.messages.TOTAL : 2 === e ? t = a.messages.AVERAGE : 3 === e ? t = l.default.getProject().geosystems[l.default.getProject().geometries[s.default.current.geometry].realgeosystem].languages[a.ida].name : 4 === e ? t = l.default.getProject().geosystems[l.default.getProject().geometries[s.default.current.geometry].realgeosystem].languages[a.ida].name : 5 === e ? t = a.messages.ALL_CITIES : 6 === e ? t = a.messages.EU27 : 7 === e && (t = l.default.getProject().geosystems[l.default.getProject().geometries[s.default.current.geometry].geosystem].languages[a.ida].name), t
				}, e.prototype.getInfoValue = function (e, t) {
					var a = o.default.get(e).info;
					return !1 === this.isNull(a.given_total_or_mean) ? a.given_total_or_mean : !0 === a.isAbsolute ? !0 === t ? a.totalnopseudo : a.total : !0 === t ? a.avgnopseudo : a.avg
				}, e.prototype.getInfoValueString = function (e, t, a) {
					var o = this.getInfoValue(e, t);
					return this.getInfoValueStringByValue(o, t, a)
				}, e.prototype.getInfoValueStringByValue = function (e, t, a) {
					return !0 === this.isNull(e) ? this.getNoData(!0, !1, !1) : !1 === e ? this.getPrivacy(!0, !1, !1) : this.showValue(e, a, !1, !1)
				}, e.prototype.resizeColorFieldsAndTables = function () {
					m.bajoodooLog("helpers.resizeColorFieldsAndTables");
					var a = this,
						o = f("body").css("font-size");
					o = o.substr(0, o.length - 2), f("canvas[class^='color_']").each(function () {
						var e = f(this).attr("class").split("_")[1],
							t = this.getContext("2d");
						t.fillStyle = "#" + e, t.fillRect(-a.colorFieldCssFrame, -a.colorFieldCssFrame, a.colorFieldWidth * o * 10 - -2 * a.colorFieldCssFrame, a.colorFieldHeight * o * 10 - -2 * a.colorFieldCssFrame)
					})
				}, e
			}();
			t.default = new e
		}).call(this, n(31))
	},
	85: function (e, t, a) {
		e.exports = {
			superlightgrey: "#eee",
			lightgrey: "#ccc",
			mediumgrey: "#999",
			darkgrey: "#666",
			white: "#fff"
		}
	},
	910: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
				return (o = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			}),
			d = this && this.__assign || function () {
				return (d = Object.assign || function (e) {
					for (var t, a = 1, o = arguments.length; a < o; a++)
						for (var n in t = arguments[a]) Object.prototype.hasOwnProperty.call(t, n) && (e[n] = t[n]);
					return e
				}).apply(this, arguments)
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var p = a(1),
			r = a(2),
			i = a(911),
			l = a(914),
			s = function (t) {
				function e(e) {
					return t.call(this, e) || this
				}
				return n(e, t), e.prototype.shouldComponentUpdate = function (e) {
					for (var t = this.props, a = Object.keys(e), o = 0; o < a.length; o++) {
						var n = a[o];
						if ("animations" !== n)
							if (!l(t[n], e[n])) return !0
					}
					return !i(t.animations, e.animations, {
						strict: !0
					})
				}, e.prototype.render = function () {
					var e = this.props,
						t = e.animations,
						a = e.decorators,
						o = e.node,
						n = e.onClick,
						r = e.onMouseOver,
						i = e.style,
						l = o.active,
						s = (o.children, !!o.map_ida),
						u = [i.link, l ? i.activeLink : null],
						c = d({
							container: u
						}, i);
					return p.createElement(a.Container, {
						animations: t,
						decorators: a,
						node: o,
						onClick: n,
						onMouseOver: r,
						style: c,
						terminal: s
					})
				}, e
			}(p.Component);
		s.propTypes = {
			style: r.object.isRequired,
			decorators: r.object.isRequired,
			animations: r.oneOfType([r.object, r.bool]).isRequired,
			node: r.object.isRequired,
			onClick: r.func
		}, t.default = s
	},
	915: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
			return (o = Object.setPrototypeOf || {
					__proto__: []
				}
				instanceof Array && function (e, t) {
					e.__proto__ = t
				} || function (e, t) {
					for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
				})(e, t)
		}, function (e, t) {
			function a() {
				this.constructor = e
			}
			o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
		});
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var l = a(1),
			r = a(2),
			i = a(152),
			s = function (e) {
				var t = e.style;
				return l.createElement("div", {
					style: t
				}, "loading...")
			};
		s.propTypes = {
			style: r.object
		};
		var u = function (e) {
			var t = e.style,
				a = t.height;
			t.width;
			return l.createElement("div", {
				style: t.base
			}, l.createElement("div", {
				style: t.wrapper
			}))
		};
		u.propTypes = {
			style: r.object
		};
		var c = function (e) {
			var t = e.node,
				a = e.style;
			return l.createElement("div", {
				style: a.base
			}, l.createElement("div", {
				style: a.title
			}, t.name))
		};
		c.propTypes = {
			style: r.object,
			node: r.object.isRequired
		};
		var d = function (e) {
			function t() {
				return null !== e && e.apply(this, arguments) || this
			}
			return n(t, e), t.prototype.render = function () {
				var t = this,
					e = this.props,
					a = e.style,
					o = e.decorators,
					n = e.terminal,
					r = e.onClick,
					i = e.node;
				return l.createElement("div", {
					onClick: r,
					ref: function (e) {
						return t.clickableRef = e
					},
					style: a.container
				}, n ? null : this.renderToggle(), l.createElement(o.Header, {
					node: i,
					style: a.header
				}))
			}, t.prototype.renderToggle = function () {
				var t = this,
					e = this.props.animations;
				return e ? l.createElement(i.VelocityComponent, {
					animation: e.toggle.animation,
					duration: e.toggle.duration,
					ref: function (e) {
						return t.velocityRef = e
					}
				}, this.renderToggleDecorator()) : this.renderToggleDecorator()
			}, t.prototype.renderToggleDecorator = function () {
				var e = this.props,
					t = e.style,
					a = e.decorators;
				return l.createElement(a.Toggle, {
					style: t.toggle
				})
			}, t
		}(l.Component);
		d.propTypes = {
			style: r.object.isRequired,
			decorators: r.object.isRequired,
			terminal: r.bool.isRequired,
			onClick: r.func.isRequired,
			animations: r.oneOfType([r.object, r.bool]).isRequired,
			node: r.object.isRequired
		}, t.default = {
			Loading: s,
			Toggle: u,
			Header: c,
			Container: d
		}
	},
	916: function (e, t, a) {
		"use strict";
		a.r(t), t.default = {
			tree: {
				base: {
					listStyle: "none",
					margin: 0,
					padding: 0
				},
				node: {
					base: {
						position: "relative"
					},
					link: {
						cursor: "pointer",
						position: "relative",
						padding: "0px 5px",
						display: "block"
					},
					activeLink: {
						background: "#31363F"
					},
					toggle: {
						base: {
							position: "relative",
							display: "inline-block",
							verticalAlign: "top",
							marginLeft: "-5px",
							height: "24px",
							width: "24px"
						},
						wrapper: {
							position: "absolute",
							top: "50%",
							left: "50%",
							margin: "-7px 0 0 -7px",
							height: "14px"
						},
						height: 14,
						width: 14,
						arrow: {
							fill: "#9DA5AB",
							strokeWidth: 0
						}
					},
					header: {
						base: {
							display: "inline-block",
							verticalAlign: "top",
							color: "#9DA5AB"
						},
						connector: {
							width: "2px",
							height: "12px",
							borderLeft: "solid 2px black",
							borderBottom: "solid 2px black",
							position: "absolute",
							top: "0px",
							left: "-21px"
						},
						title: {
							lineHeight: "24px",
							verticalAlign: "middle"
						}
					},
					subtree: {
						listStyle: "none",
						paddingLeft: "19px"
					},
					loading: {
						marginLeft: "5px",
						fontSize: "0.8em",
						marginTop: 3
					}
				}
			}
		}
	},
	917: function (e, t, a) {
		"use strict";
		a.r(t), t.default = {
			toggle: function (e) {
				return {
					animation: {
						rotateZ: e.node.toggled ? 90 : 0
					},
					duration: 100
				}
			},
			drawer: function () {
				return {
					enter: {
						animation: "slideDown",
						duration: 100
					},
					leave: {
						animation: "slideUp",
						duration: 100
					}
				}
			}
		}
	},
	918: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
			return (o = Object.setPrototypeOf || {
					__proto__: []
				}
				instanceof Array && function (e, t) {
					e.__proto__ = t
				} || function (e, t) {
					for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
				})(e, t)
		}, function (e, t) {
			function a() {
				this.constructor = e
			}
			o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
		});
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var i = a(1),
			r = a(2),
			l = a(152),
			s = a(43),
			u = a(29),
			c = a(17),
			d = u.observer(function (e) {
				var t = e.style;
				return i.createElement("span", {
					style: t
				}, void 0 !== c.default.localization.messages.LOADING_PROJECT_STRUCTURE ? c.default.localization.messages.LOADING_PROJECT_STRUCTURE : "Loading...")
			});
		d.propTypes = {
			style: r.object
		};
		var p = function (e) {
			var t = e.style,
				a = t.height;
			t.width;
			return i.createElement("span", null)
		};
		p.propTypes = {
			style: r.object
		};
		var f = function (e) {
			var t = e.node;
			e.style;
			return i.createElement("p", null, i.createElement(s.default, null, t.name))
		};
		f.propTypes = {
			style: r.object,
			node: r.object.isRequired
		};
		var h = function (e) {
			function t() {
				return null !== e && e.apply(this, arguments) || this
			}
			return n(t, e), t.prototype.render = function () {
				var t = this,
					e = this.props,
					a = (e.style, e.decorators),
					o = (e.terminal, e.onClick),
					n = e.onMouseOver,
					r = e.node;
				return i.createElement("div", {
					onClick: o,
					onMouseOver: n,
					ref: function (e) {
						return t.clickableRef = e
					}
				}, i.createElement(a.Header, {
					node: r
				}))
			}, t.prototype.renderToggle = function () {
				var t = this,
					e = this.props.animations;
				return e ? i.createElement(l.VelocityComponent, {
					animation: e.toggle.animation,
					duration: e.toggle.duration,
					ref: function (e) {
						return t.velocityRef = e
					}
				}, this.renderToggleDecorator()) : this.renderToggleDecorator()
			}, t.prototype.renderToggleDecorator = function () {
				var e = this.props,
					t = e.style,
					a = e.decorators;
				return i.createElement(a.Toggle, {
					style: t.toggle
				})
			}, t
		}(i.Component);
		h.propTypes = {
			style: r.object.isRequired,
			decorators: r.object.isRequired,
			terminal: r.bool.isRequired,
			onClick: r.func.isRequired,
			animations: r.oneOfType([r.object, r.bool]).isRequired,
			node: r.object.isRequired
		}, t.default = {
			Loading: d,
			Toggle: p,
			Header: f,
			Container: h
		}
	},
	919: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
				return (o = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			}),
			r = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var i = a(1),
			l = a(28),
			s = a(29),
			u = a(17),
			c = a(10),
			d = a(40),
			p = a(20),
			f = a(18),
			h = a(149),
			m = a(58),
			g = function (a) {
				function e(e) {
					var t = a.call(this, e) || this;
					return t.state = {
						anchorEl: null
					}, t.handleInfoClick = function (e) {
						t.setState({
							anchorEl: e.currentTarget
						})
					}, t.handleInfoClose = function () {
						t.setState({
							anchorEl: null
						})
					}, t
				}
				return n(e, a), e.prototype.handleMapClick = function (e, t) {
					c.default.emitter.once(f.STRUCTURE_LOADED, function () {
						h.default.triggerCurrentPattern([t]), p.default.loadThematicalMap(t)
					}), m.default.clearCurrentStructureNode(), m.default.prepareRootStructure(), c.default.treeDrawerOpen = !1
				}, e.prototype.renderList = function () {
					var a = this,
						e = this.props.classes;
					return i.createElement(l.List, {
						component: "ul",
						className: e.maps
					}, c.default.foundMapsSearchResult.slice(0, 100).map(function (t, e) {
						return i.createElement(l.ListItem, {
							key: "label" + t.ida
						}, i.createElement(l.ListItemText, {
							primary: t.name,
							secondary: [t.geounit]
						}), i.createElement(l.ListItemSecondaryAction, null, i.createElement(l.IconButton, {
							"aria-label": "MapClick",
							onClick: function (e) {
								return a.handleMapClick(e, t.ida)
							}
						}, i.createElement(d.Map, null))))
					}))
				}, e.prototype.renderHint = function () {
					var e = this.state.anchorEl,
						t = this.props.classes;
					return i.createElement("span", null, " ", i.createElement(d.InformationOutline, {
						onClick: this.handleInfoClick
					}), i.createElement(l.Popover, {
						open: Boolean(e),
						anchorEl: e,
						onClose: this.handleInfoClose,
						anchorOrigin: {
							vertical: "bottom",
							horizontal: "center"
						},
						transformOrigin: {
							vertical: "top",
							horizontal: "center"
						}
					}, i.createElement(l.Typography, {
						variant: "body2",
						className: t.typography
					}, u.default.localization.messages.MAPS_SEARCH_INFO)))
				}, e.prototype.render = function () {
					var e = this.props.classes,
						t = 0 < c.default.foundMapsSearchResult.length;
					return i.createElement("div", null, i.createElement(l.Typography, {
						variant: "h6",
						className: t ? e.active : e.inactive
					}, u.default.localization.messages.MAPS, t ? null : this.renderHint()), t ? this.renderList() : null)
				}, e = r([s.observer], e)
			}(i.Component);
		t.default = l.withStyles(function (e) {
			return {
				typography: {
					margin: 2 * e.spacing.unit
				},
				active: {
					color: "#000000"
				},
				inactive: {
					color: "#CCCCCC"
				}
			}
		})(g)
	},
	920: function (e, T, E) {
		"use strict";
		(function (o) {
			var n, t = this && this.__extends || (n = function (e, t) {
				return (n = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				n(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			});
			Object.defineProperty(T, "__esModule", {
				value: !0
			});
			var r = E(18),
				i = E(921),
				l = E(40),
				s = E(1),
				u = E(122),
				c = E(10),
				d = E(922),
				p = E(923),
				f = E(929),
				h = E(931),
				m = E(20),
				g = E(150),
				y = E(30),
				v = E(28),
				e = E(29),
				b = E(932),
				_ = E(152);
			E(85);
			var a = v.withStyles(function (e) {
				e.typography, e.mixins;
				var t = e.spacing,
					a = e.palette,
					o = e.shadows;
				return {
					button: {
						margin: 0,
						marginBottom: 2,
						minWidth: "auto",
						padding: 8
					},
					buttonContainer: {
						position: "absolute",
						left: 10,
						zIndex: 1001
					},
					mapAutomation: {
						position: "absolute",
						zIndex: 1031,
						bottom: 10,
						width: "100%"
					},
					topoButtonContainer: {},
					mapTitleContainer: {
						textAlign: "center",
						width: "100%",
						position: "absolute",
						zIndex: 1e3,
						bottom: 10
					},
					mapTitle: {
						textAlign: "center",
						display: "inline-block",
						padding: 10
					},
					modal: {
						maxHeight: window.innerHeight,
						maxWidth: window.innerWidth
					},
					paper: {
						backgroundColor: a.background.paper,
						boxShadow: o[5],
						padding: 4 * t.unit,
						position: "absolute"
					},
					initialDisplayNone: {
						display: "none"
					},
					initialDisable: {
						color: "rgba(150, 150, 150, 0.87);"
					},
					ruler: {
						position: "absolute",
						right: 10,
						bottom: 10,
						zIndex: 1002
					}
				}
			})(e.observer(function (a) {
				function e(e) {
					var t = a.call(this, e) || this;
					return t.state = {}, t
				}
				return t(e, a), e.prototype.handleLayersToggleClick = function (e) {
					c.default.modalContent = s.createElement(p.default, null), c.default.modalOpen = !0, c.default.modalWidth = 400
				}, e.prototype.toggleFullscreen = function () {
					null === d.default.fullscreenElement ? (d.default.requestFullscreen(document.body), g.default.setFullScreenState(!0), o("#appHeader>div:first").css("display", "none")) : (d.default.exitFullscreen(), g.default.setFullScreenState(!1), o("#appHeader>div:first").css("display", "block"))
				}, e.prototype.onFullScreenChange = function () {
					null !== d.default.fullscreenElement ? (g.default.setFullScreenState(!0), o("#appHeader>div:first").css("display", "none")) : (g.default.setFullScreenState(!1), o("#appHeader>div:first").css("display", "block"))
				}, e.prototype.renderActivateFullScreenButton = function () {
					var t = this,
						e = this.props.classes;
					return y.bajoodooLog("ThematicalMap.renderActivateFullScreenButton() => " + c.default.isFullScreen), !1 === c.default.isFullScreen ? s.createElement("div", null, s.createElement(v.Fab, {
						size: "small",
						id: "activateFullScreen",
						color: "primary",
						className: e.button,
						onClick: function (e) {
							return t.toggleFullscreen()
						}
					}, s.createElement(l.ArrowExpandAll, null))) : null
				}, e.prototype.renderDeactivateFullScreenButton = function () {
					var t = this,
						e = this.props.classes;
					return y.bajoodooLog("ThematicalMap.renderDeactivateFullScreenButton() => " + c.default.isFullScreen), !0 === c.default.isFullScreen ? s.createElement("div", null, s.createElement(v.Fab, {
						size: "small",
						id: "activateFullScreen",
						color: "primary",
						className: e.button,
						onClick: function (e) {
							return t.toggleFullscreen()
						}
					}, s.createElement(l.ArrowCollapseAll, null))) : null
				}, e.prototype.showAutomation = function () {
					var e = this.props.classes;
					return s.createElement(_.VelocityComponent, {
						animation: {
							bottom: c.default.isBottomLegend ? 10 : c.default.bottomBarHeight + 20
						}
					}, s.createElement("div", {
						className: e.mapAutomation
					}, s.createElement(f.default, null)))
				}, e.prototype.render = function () {
					var t = this;
					y.bajoodooLog("ThematicalMap.render()");
					var e = this.props.classes;
					return s.createElement("div", null, s.createElement(_.VelocityComponent, {
						animation: {
							bottom: c.default.isBottomLegend ? 10 : c.default.bottomBarHeight + 20,
							left: c.default.treeDrawerOpen && !c.default.isBottomLegend ? 410 : 10
						}
					}, s.createElement("div", {
						id: "buttons",
						className: e.buttonContainer
					}, s.createElement("div", {
						className: e.topoButtonContainer
					}, s.createElement(v.Fab, {
						size: "small",
						color: "primary",
						className: e.button,
						onClick: function (e) {
							return t.handleLayersToggleClick(e)
						}
					}, s.createElement(l.LayersOutline, null))), c.default.isFullScreen ? this.renderDeactivateFullScreenButton() : this.renderActivateFullScreenButton(), s.createElement("div", null, s.createElement(v.Fab, {
						size: "small",
						id: "zoomIn",
						color: "primary",
						className: e.button,
						onClick: function () {
							g.default.zoomInButtonClick()
						}
					}, s.createElement(l.Plus, null))), s.createElement("div", null, s.createElement(v.Fab, {
						size: "small",
						id: "restore",
						color: "primary",
						className: e.button + " " + e.initialDisable,
						onClick: function () {
							g.default.zoomBackToInitial()
						}
					}, s.createElement(l.Fullscreen, null))), s.createElement("div", null, s.createElement(v.Fab, {
						size: "small",
						id: "zoomOut",
						color: "primary",
						className: e.button,
						onClick: function () {
							g.default.zoomOutButtonClick()
						}
					}, s.createElement(l.Minus, null))))), s.createElement(_.VelocityComponent, {
						animation: {
							bottom: c.default.isBottomLegend ? 10 : c.default.bottomBarHeight + 20,
							right: c.default.mapLegendDrawer && !c.default.isBottomLegend ? c.default.legendWidth + 10 : 10
						}
					}, s.createElement("div", {
						className: e.ruler
					}, s.createElement(h.default, null))), this.showAutomation(), s.createElement("div", {
						id: "invisibleWorker"
					}), s.createElement("div", {
						id: "containerContainer"
					}, s.createElement("div", {
						id: "mapContainer",
						onWheel: function (e) {
							g.default.zoomInOutWheel(e)
						},
						onContextMenu: function (e) {
							g.default.checkRightClick(e)
						},
						ref: "mapContainer"
					})), s.createElement(u, {
						className: "tooltip",
						type: "null",
						delayShow: 50,
						delayHide: 100,
						place: "right",
						isCapture: !0
					}, s.createElement("div", null, s.createElement(b.RegionInfo, null))), y.bajoodooLog("ThematicalMap.rendered()"))
				}, e.prototype.componentDidMount = function () {
					var e = this;
					y.bajoodooLog("ThematicalMap.componentDidMount()"), c.default.emitter.on(r.THEMATICALMAP_GEOMETRY_CHANGED, function () {
						e.update()
					}), c.default.emitter.on(r.THEMATICALMAP_LOADED, function () {
						e.update()
					}), c.default.emitter.on(r.REGION_ROLLOVER, function () {
						u.rebuild()
					}), c.default.emitter.on(r.THEMATICALMAP_ZOOMED, function () {
						m.default.redrawSymbols()
					}), c.default.emitter.on(r.WINDOW_RESIZED, function () {
						m.default.redrawSymbols(), g.default.checkMapInViewport(), e.updateMapContainer()
					}), d.default.addEventListener("fullscreenchange", function () {
						e.onFullScreenChange()
					}, !1);
					var t = document.getElementById("mapContainer"),
						a = new i(t);
					a.get("pinch").set({
						enable: !0
					}), a.on("doubletap", function (e) {
						g.default.zoomInDoubleClick(e)
					}), a.on("panstart", function (e) {
						o(document.body).css("overflow", "hidden"), g.default.onPanStart(e)
					}), a.on("panmove", function (e) {
						g.default.onPan(e)
					}), a.on("panend", function (e) {
						g.default.onPanEnd(e), o(document.body).css("overflow", "auto")
					}), a.on("pinchstart", function (e) {
						o(document.body).css("overflow", "hidden"), g.default.onPinchStart(e)
					}), a.on("pinchmove", function (e) {
						g.default.onPinch(e)
					}), a.on("pinchend", function (e) {
						g.default.onPinchEnd(e), o(document.body).css("overflow", "auto")
					}), a.on("press", function (e) {})
				}, e.prototype.updateMapContainer = function () {
					o("#mapContainer>svg").css("padding-top", c.default.appHeaderHeight).css("padding-bottom", c.default.bottomBarHeight)
				}, e.prototype.update = function () {
					y.bajoodooLog("ThematicalMap.update()"), this.setState({
						mapId: m.default.current.ida
					}), this.render()
				}, e.prototype.componentWillUnmount = function () {}, e.prototype.componentDidUpdate = function () {}, e
			}(s.Component)));
			T.default = a
		}).call(this, E(31))
	},
	923: function (e, _, T) {
		"use strict";
		(function (r) {
			var o, a = this && this.__extends || (o = function (e, t) {
				return (o = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			});
			Object.defineProperty(_, "__esModule", {
				value: !0
			});
			var u = T(1),
				e = T(41),
				c = T(28),
				p = T(20),
				n = T(17),
				f = T(19),
				h = T(10),
				t = T(29),
				i = T(83),
				l = T(84),
				s = T(214),
				d = T(18),
				m = T(213),
				g = T(408),
				y = T(30),
				v = T(5),
				b = e.withStyles(function (e) {
					e.typography, e.mixins, e.spacing, e.palette, e.shadows;
					return {
						root: {
							flexGrow: 1,
							backgroundColor: "white"
						},
						white: {
							color: "#FFFFFF"
						},
						black: {
							color: "#000000"
						},
						listItem: {
							margin: 0,
							padding: 0,
							paddingLeft: 10
						},
						indented: {
							paddingLeft: 20
						},
						disabled: {
							filter: "Alpha(opacity=50)",
							opacity: .5
						}
					}
				})(t.observer(function (t) {
					function e(e) {
						var n = t.call(this, e) || this;
						return n.handleChange = function (t) {
							return function (e) {
								"symbol" === t ? (h.default.setSymbolState(!h.default.isSymbolOn), h.default.emitter.emit(d.LEGEND_RERENDER)) : "areal" === t ? (h.default.setArealState(!h.default.isArealOn), h.default.emitter.emit(d.LEGEND_RERENDER)) : "geometry" === t ? h.default.setGeometryState(!h.default.isGeometryOn) : "relief" === t && h.default.setReliefState(!h.default.isReliefOn)
							}
						}, n.handleTopoChange = function (e, t, a) {
							void 0 === a && (a = null), y.bajoodooLog("handleTopoChange: " + t);
							var o = parseInt(t.substr(1));
							h.default.setTopoState(e, o, null === a ? !h.default.isTopoOn(e, o) : a), r("#topo_" + e + "_" + o).length ? r("#topo_" + e + "_" + o).attr("visibility", h.default.isTopoOn(e, o) ? "visible" : "hidden") : h.default.isTopoOn(e, o) && (h.default.emitter.once(d.TOPOGRAPHY_LOADED, function () {
								m.default.addTopography(e, o)
							}), s.default.has(o, e)), n.prepareTopoCheckboxes(), n.render()
						}, n.handleContainerChange = function (t, e, a) {
							var o = parseInt(e.substr(1));
							h.default.setTopoState(t, o, !h.default.isTopoOn(t, o)), a.map(function (e) {
								n.handleTopoChange(t, e, h.default.isTopoOn(t, o))
							})
						}, n.state = {
							hasSym: !1,
							symOn: !1,
							topographies: [],
							containers: null
						}, n
					}
					return a(e, t), e.prototype.componentWillMount = function () {
						this.m = p.default.current, this.d = i.default.current, this.l = n.default.localization, this.p = f.default.getProject(), this.prepareSymCheckbox(), this.prepareArealCheckbox(), this.prepareTopoCheckboxes(), this.prepareGeometryCheckbox(), this.prepareReliefCheckbox()
					}, e.prototype.prepareSymCheckbox = function () {
						var e = p.default.current;
						if (e.hasVisualization("sym") || e.hasVisualization("sect") || e.hasVisualization("bar")) {
							var t = "hidden" !== r("#symbolContainer").css("visibility");
							h.default.setSymbolState(t), h.default.hasSymbol = !0
						} else h.default.hasSymbol = !1
					}, e.prototype.prepareArealCheckbox = function () {
						var e = p.default.current;
						if (e.hasVisualization("choro") || e.hasVisualization("quali")) {
							var t = e.geometry,
								a = !1;
							r("#geo_" + t).find("path").each(function () {
								0 < parseFloat(r(this).attr("fill-opacity")) && (a = !0)
							}), h.default.setArealState(a), h.default.hasAreal = !0
						} else h.default.hasAreal = !1
					}, e.prototype.prepareGeometryCheckbox = function () {
						var e = p.default.current.geometry,
							t = !1;
						r("#geo_" + e).find("path").each(function () {
							"0" !== r(this).attr("stroke-opacity") && (t = !0)
						}), h.default.setGeometryState(t)
					}, e.prototype.prepareReliefCheckbox = function () {
						var e = "hidden" !== r("#relief").css("visibility");
						//h.default.setReliefState(e)
					}, e.prototype.renderSymCheckbox = function () {
						var e = this.props.classes,
							t = this.getDesignColor(14);
						return r("#symbolContainer").css("visibility", h.default.isSymbolOn ? "visible" : "hidden"), r("#symbolLegend").fadeTo(500, h.default.isSymbolOn ? 1 : .4), h.default.hasSymbol ? u.createElement(c.ListItem, {
							key: "symbol",
							dense: !0,
							button: !0,
							className: e.listItem,
							style: {
								backgroundColor: "#" + t
							},
							onClick: this.handleChange("symbol")
						}, u.createElement(c.ListItemText, {
							primary: u.createElement(c.Typography, {
								className: v(this.isDarkBackground(t) ? e.white : e.black, h.default.isSymbolOn ? null : e.disabled)
							}, this.l.messages.VIS_SYMBOL)
						}), u.createElement(c.Switch, {
							checked: h.default.isSymbolOn,
							color: "default",
							value: "symbol"
						})) : null
					}, e.prototype.renderGeometryCheckbox = function () {
						var e = this.props.classes,
							t = p.default.current,
							a = this.getDesignColor(10),
							o = t.geometry,
							n = h.default.isGeometryOn;
						return r("#geo_" + o).find("path").each(function () {
							r(this).attr("stroke-opacity", n ? "1" : "0")
						}), u.createElement(c.ListItem, {
							key: "geometry",
							dense: !0,
							button: !0,
							className: e.listItem,
							style: {
								backgroundColor: "#" + a
							},
							onClick: this.handleChange("geometry")
						}, u.createElement(c.ListItemText, {
							primary: u.createElement(c.Typography, {
								className: v(this.isDarkBackground(a) ? e.white : e.black, h.default.isGeometryOn ? null : e.disabled)
							}, this.l.messages.BORDERS)
						}), u.createElement(c.Switch, {
							checked: h.default.isGeometryOn,
							color: "default",
							value: "geometry"
						}))
					}, e.prototype.renderReliefCheckbox = function () {
						// var e = this.props.classes,
						// 	t = this.getDesignColor(10);
						// return r("#relief").css("visibility", h.default.isReliefOn ? "visible" : "hidden"), u.createElement(c.ListItem, {
						// 	key: "relief",
						// 	dense: !0,
						// 	button: !0,
						// 	className: e.listItem,
						// 	style: {
						// 		backgroundColor: "#" + t
						// 	},
						// 	onClick: this.handleChange("relief")
						// 	return r
						// }, u.createElement(c.ListItemText, {
						// 	primary: u.createElement(c.Typography, {
						// 		className: v(this.isDarkBackground(t) ? e.white : e.black, h.default.isReliefOn ? null : e.disabled)
						// 	}, this.l.messages.RELIEF)
						// }), u.createElement(c.Switch, {
						// 	checked: h.default.isReliefOn,
						// 	color: "default",
						// 	value: "relief"
						// }))
					}, e.prototype.isDarkBackground = function (e) {
						return new g("#" + e).luminosity() < .6
					}, e.prototype.renderArealCheckbox = function () {
						var e = this.props.classes,
							t = p.default.current,
							a = this.getDesignColor(14),
							o = t.geometry,
							n = h.default.isArealOn;
						return h.default.hasAreal && (r("#geo_" + o).find("path").each(function () {
							r(this).attr("fill-opacity", n ? l.default.arealOpacity : 0)
						}), r("#arealLegend").fadeTo(500, n ? 1 : .4)), h.default.hasAreal ? u.createElement(c.ListItem, {
							key: "symbol",
							dense: !0,
							button: !0,
							className: e.listItem,
							style: {
								backgroundColor: "#" + a
							},
							onClick: this.handleChange("areal")
						}, u.createElement(c.ListItemText, {
							primary: u.createElement(c.Typography, {
								className: v(this.isDarkBackground(a) ? e.white : e.black, h.default.isArealOn ? null : e.disabled)
							}, this.l.messages.VIS_AREAL)
						}), u.createElement(c.Switch, {
							checked: h.default.isArealOn,
							color: "default",
							value: "areal"
						})) : null
					}, e.prototype.prepareTopoCheckboxes = function () {
						var o, r = this,
							i = p.default.current,
							l = (this.props.classes, f.default.getProject()),
							n = i.geosystem,
							s = l.topographies,
							u = i.topographies,
							c = [];
						Object.keys(u).map(function (e, t) {
							var a = parseInt(e.substr(1));
							s[e].properties.geosystem == n && (1 == s[e].properties.topoclass ? (e, o = r.getDesignColor(12)) : 2 == s[e].properties.topoclass || 7 == s[e].properties.topoclass ? o = r.getDesignColor(17) : 5 == s[e].properties.topoclass ? o = r.getDesignColor(20) : 3 == s[e].properties.topoclass ? o = r.getDesignColor(24) : 8 == s[e].properties.topoclass && (o = r.getDesignColor(13)), h.default.setTopoState(s[e].properties.type, a, void 0 !== h.default.topotypeStates[s[e].properties.type][a] ? h.default.topotypeStates[s[e].properties.type][a] : 1 == u[e]), c["_" + a] = r.buildTopoElement(a, s[e], o, 1 == u[e], !1))
						});
						var d = {};
						Object.keys(l.topographies).reverse().map(function (e) {
							var t = parseInt(e.substr(1));
							if (l.topographies[e].properties.geosystem === i.geosystem)
								if (!0 === l.topographies[e].properties.is_folder && void 0 === d[e]) {
									d[e] = [];
									var a = r.getDesignColor(12);
									c[e] = r.buildTopoElement(t, l.topographies[e], a, !1, !0)
								} else if (void 0 !== l.topographies[e].properties.parent_id) {
								if (void 0 === d["_" + l.topographies[e].properties.parent_id] || !(d["_" + l.topographies[e].properties.parent_id] instanceof Array)) {
									d["_" + l.topographies[e].properties.parent_id] = [];
									var o = parseInt(l.topographies[e].properties.parent_id),
										n = r.getDesignColor(12);
									c["_" + o] = r.buildTopoElement(o, l.topographies["_" + o], n, !1, !0)
								}
								d["_" + l.topographies[e].properties.parent_id].push("_" + t), d[e] = !1
							} else void 0 === d[e] && (d[e] = !0)
						}), this.setState({
							topographies: c,
							containers: d
						})
					}, e.prototype.buildTopoElement = function (e, t, a, o, n) {
						return {
							type: t.properties.type,
							checked: void 0 !== h.default.topotypeStates[t.properties.type][e] ? h.default.topotypeStates[t.properties.type][e] : o,
							ida: e,
							name: t.languages[this.l.ida].name,
							color: a,
							isContainer: n
						}
					}, e.prototype.getDesignColor = function (t) {
						var e = this.d.data.colors.color.filter(function (e) {
							return e["@id"] == t
						});
						return e.length ? e[0].$.substr(2) : "CCCCCC"
					}, e.prototype.renderTopographyCheckboxes = function () {
						for (var r = this, i = this.props.classes, l = [], e = function (e) {
								var t = Object.keys(s.state.containers)[e];
								if (!1 === s.state.containers[t]) return "continue";
								if (!0 === s.state.containers[t]) {
									var a = s.state.topographies[t];
									l.push(u.createElement(c.ListItem, {
										key: "topo" + a.type + a.ida,
										dense: !0,
										button: !0,
										className: i.listItem,
										style: {
											backgroundColor: "#" + a.color
										},
										onClick: function () {
											return r.handleTopoChange(a.type, "_" + a.ida)
										}
									}, u.createElement(c.ListItemText, {
										primary: u.createElement(c.Typography, {
											className: v(s.isDarkBackground(a.color) ? i.white : i.black, h.default.topotypeStates[a.type][a.ida] || a.checked ? null : i.disabled)
										}, a.name)
									}), u.createElement(c.Switch, {
										checked: void 0 !== h.default.topotypeStates[a.type][a.ida] ? h.default.topotypeStates[a.type][a.ida] : a.checked,
										value: "topo" + a.type + a.ida,
										color: "default"
									})))
								}
								if (Array.isArray(s.state.containers[t])) {
									var o, n = s.state.topographies[t];
									o = 0 < s.state.containers[t].filter(function (e) {
										return !0 === r.state.topographies[e].checked
									}).length, l.push(u.createElement(c.ListItem, {
										key: "topo" + n.type + n.ida,
										dense: !0,
										button: !0,
										className: i.listItem,
										style: {
											backgroundColor: "#" + n.color
										},
										onClick: function () {
											return r.handleContainerChange(n.type, "_" + n.ida, r.state.containers[t])
										}
									}, u.createElement(c.ListItemText, {
										primary: u.createElement(c.Typography, {
											className: v(s.isDarkBackground(n.color) ? i.white : i.black, o ? null : i.disabled)
										}, n.name)
									}), u.createElement(c.Switch, {
										checked: o,
										value: "topo" + n.type + n.ida,
										color: "default"
									}))), s.state.containers[t].map(function (e) {
										var t = r.state.topographies[e];
										l.push(u.createElement(c.ListItem, {
											key: "topo" + t.type + t.ida,
											dense: !0,
											button: !0,
											className: v(i.listItem, i.indented),
											style: {
												backgroundColor: "#" + t.color
											},
											onClick: function () {
												return r.handleTopoChange(t.type, "_" + t.ida)
											}
										}, u.createElement(c.ListItemText, {
											primary: u.createElement(c.Typography, {
												className: v(r.isDarkBackground(t.color) ? i.white : i.black, t.checked ? null : i.disabled)
											}, t.name)
										}), u.createElement(c.Switch, {
											checked: void 0 !== h.default.topotypeStates[t.type][t.ida] ? h.default.topotypeStates[t.type][t.ida] : t.checked,
											value: "topo" + t.type + t.ida,
											color: "default"
										})))
									})
								}
							}, s = this, t = Object.keys(this.state.containers).length - 1; 0 <= t; t--) e(t);
						return u.createElement("div", null, l)
					}, e.prototype.render = function () {
						this.props.classes;
						return u.createElement("div", null, u.createElement(c.Typography, {
							variant: "h6",
							component: "h3"
						}, this.l.messages.BUTTON_MAP), u.createElement(c.List, null, this.renderSymCheckbox()), this.renderArealCheckbox(), this.renderTopographyCheckboxes(), this.renderGeometryCheckbox(), this.renderReliefCheckbox())
					}, e
				}(u.Component)));
			_.default = b
		}).call(this, T(31))
	},
	929: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
				return (o = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			}),
			r = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var i = a(5),
			l = a(40),
			s = a(1),
			u = a(122),
			c = a(10),
			d = a(930),
			p = a(17),
			f = a(149),
			h = a(19),
			m = a(211),
			g = a(58),
			y = a(20),
			v = a(18),
			b = a(28),
			_ = a(29),
			T = function (t) {
				function e(e) {
					var o = t.call(this, e) || this;
					return o.interval = 5e3, o.timerRunning = !1, o.animatableMaps = [], o.animatablePatterns = [], o.animationReverse = !1, o.triggeredManually = !1, o._isMounted = !1, o.childrenLoaded = null, o.numberOfChildren = 0, o.onMapSliderChange = function (e, t) {
						var a = p.default.localization;
						c.default.emitter.once(v.SEARCHINDEX_AVAILABLE, function () {
							var e = m.default.searchMap(o.animatableMaps[t]);
							o.setState({
								mapSliderTooltip: e
							}), console.log("rebuild"), u.rebuild()
						}), m.default.getIndex(a.codeShort), o.setState({
							sliderValue: t
						})
					}, o.state = {
						texts: {},
						sliderValue: 1,
						playButton: !0,
						playReverseButton: !0,
						stopButton: !0,
						backButton: !0,
						forwardButton: !0,
						firstButton: !0,
						lastButton: !0,
						settingsVisible: !1,
						intervalSelect: [],
						automationInterval: 3,
						loopAnimation: !0,
						mapTitles: [],
						activeStep: null
					}, o
				}
				return n(e, t), e.prototype.componentDidMount = function () {
					var e = this;
					c.default.emitter.on(v.CURRENT_STRUCTURE_UPDATED, function () {
						e.checkMapAvailable()
					}), c.default.emitter.on(v.AUTOMATION_STOPPED, function () {
						e.stopAnimation()
					}), c.default.emitter.on(v.AUTOMATION_STARTED, function () {
						e.startAnimation()
					}), this._isMounted = !0
				}, e.prototype.componentWillUnmount = function () {
					this._isMounted = !1
				}, e.prototype.checkMapAvailable = function () {
					var e = this,
						t = g.default.getCurrentStructures().slice().pop();
					c.default.emitter.once(v.THEMATICALMAP + t, function () {
						e._isMounted && e.checkForAnimationLevels()
					})
				}, e.prototype.checkForAnimationLevels = function () {
					var r = this;
					if (!this.isAnimationStarted()) {
						var e = y.default.current,
							t = g.default.getCurrentNode();
						if (void 0 !== e && void 0 !== t) {
							var a = g.default.getCurrentStructures();
							if (this.hide(), this.checkIntervalSelect(), this.animatableMaps = [], this.childrenLoaded = null, c.default.isLoadingDisplayed = !1, 2 < a.length && null === this.childrenLoaded) {
								var i = a[a.length - 3];
								c.default.emitter.once("STRUCTURE" + i, function (e) {
									e.structure_ida;
									var t, a, o = g.default.getStructure(i);
									r.numberOfChildren = Object.keys(o.children).length;
									for (var n = r.childrenLoaded = 0; n < r.numberOfChildren; n++) 0 === (t = Object.keys(o.children)[n]).indexOf("_") && (t = t.substr(1)), a = parseInt(t), c.default.emitter.once("STRUCTURE" + a, function (e) {
										var t = e.structure_ida;
										r.onStructureDataAvailable(t)
									}), g.default.loadStructure(a)
								}), g.default.loadStructure(i)
							}
						}
					}
				}, e.prototype.onStructureDataAvailable = function (e) {
					this.childrenLoaded++;
					var t = g.default.getStructure(e);
					if (Object.keys(t.info).length) {
						var a = [];
						for (var o in Object.keys(t.info)) {
							var n = Object.keys(t.info)[o],
								r = t.info[n];
							r.GEOUNIT == h.default.getProject().geometries[y.default.current.geometry].geounit && (-1 == this.animatableMaps.indexOf(r.MAP) && this.animatableMaps.push(r.MAP), a.push({
								ida: r.MAP,
								name: r.MAP
							}))
						}
						this.setState({
							mapTitles: a
						})
					}
					this.isFindPatternsAvailable()
				}, e.prototype.isFindPatternsAvailable = function () {
					this.childrenLoaded >= this.numberOfChildren && (c.default.isLoadingDisplayed = !0), this.childrenLoaded >= this.numberOfChildren && 1 < this.animatableMaps.length ? (c.default.animationAvailable = !0, this.animatableMaps = this.animatableMaps.reverse(), this.setState({
						mapTitles: this.state.mapTitles.reverse()
					}), this.state.settingsVisible && this.onAnimatableMapsComplete(), this.findPatterns(), this.setState({
						sliderValue: this.findAutomationPosition(!this.animationReverse, !1),
						sliderMax: this.animatableMaps.length - 1,
						sliderMin: 0
					}), c.default.animationStarted && this.startAnimation()) : (c.default.animationAvailable = !1, this.hide())
				}, e.prototype.findPatterns = function () {
					var n = this,
						r = g.default.getCurrentStructures().slice().reverse().slice(2).join("_"),
						i = 0;
					if (this.animatablePatterns = [], this.animatableMaps.length) {
						c.default.isLoadingDisplayed = !1;
						for (var e = 0; e < this.animatableMaps.length; e++) {
							var t = this.animatableMaps[e];
							c.default.emitter.once("PATTERN" + t, function (e) {
								var t = e.pattern_ida;
								i++;
								for (var a = f.default.getPattern(t).elements, o = 0; o < a.length; o++) - 1 < a[o].indexOf(r) && (n.animatablePatterns[a[o].substring(0, a[o].indexOf("_"))] = a[o]);
								i == n.animatableMaps.length ? (n.show(), n.setupLabels(), c.default.isLoadingDisplayed = !0) : n.hide()
							}), f.default.getPattern(t)
						}
					}
				}, e.prototype.show = function () {
					c.default.showAutomationControls = !0
				}, e.prototype.hide = function () {
					c.default.showAutomationControls = !1
				}, e.prototype.setupLabels = function () {
					var e = p.default.localization,
						t = {};
					t.backButton = e.messages.BUTTON_PREVIOUS_MAP, t.forwardButton = e.messages.BUTTON_NEXT_MAP, t.firstButton = e.messages.BUTTON_FIRST_MAP, t.lastButton = e.messages.BUTTON_LAST_MAP, t.playButton = e.messages.BUTTON_PLAY_MAP_ANIMATION, t.playReverseButton = e.messages.BUTTON_PLAY_MAP_ANIMATION_REVERSE, t.stopButton = e.messages.BUTTON_STOP_MAP_ANIMATION, t.settingsIcon = e.messages.SETTINGS, t.closeIcon = e.messages.CLOSE, t.closeSettingsIcon = e.messages.CLOSE, t.infoIcon = e.messages.SHOW_INFORMATION, this.setState({
						texts: t
					})
				}, e.prototype.onAnimatableMapsComplete = function () {
					var a = this,
						o = [],
						e = p.default.localization;
					c.default.emitter.once(v.SEARCHINDEX_AVAILABLE, function () {
						for (var e = 0; e < a.animatableMaps.length; e++) {
							var t = m.default.searchMap(a.animatableMaps[e]);
							o.push({
								ida: a.animatableMaps[e],
								name: t
							})
						}
						a.setState({
							mapTitles: o
						})
					}), m.default.getIndex(e.codeShort)
				}, e.prototype.checkIntervalSelect = function () {
					var t = this;
					c.default.validIds.length;
					this.setState({
						intervalSelect: this.getAutomationShortIntervals(),
						automationInterval: this.getAutomationShortIntervals().filter(function (e) {
							return t.state.automationInterval === e.value
						}).length ? this.state.automationInterval : 1
					})
				}, e.prototype.getAutomationShortIntervals = function () {
					var e = p.default.localization;
					return [{
						name: "1 " + e.messages.SECONDS,
						value: 1
					}, {
						name: "2 " + e.messages.SECONDS,
						value: 2
					}, {
						name: "3 " + e.messages.SECONDS,
						value: 3
					}, {
						name: "4 " + e.messages.SECONDS,
						value: 4
					}, {
						name: "5 " + e.messages.SECONDS,
						value: 5
					}, {
						name: "10 " + e.messages.SECONDS,
						value: 10
					}, {
						name: "20 " + e.messages.SECONDS,
						value: 20
					}, {
						name: "30 " + e.messages.SECONDS,
						value: 30
					}]
				}, e.prototype.getAutomationIntervals = function () {
					var e = p.default.localization;
					return [{
						name: "5 " + e.messages.SECONDS,
						value: 5
					}, {
						name: "10 " + e.messages.SECONDS,
						value: 10
					}, {
						name: "20 " + e.messages.SECONDS,
						value: 20
					}, {
						name: "30 " + e.messages.SECONDS,
						value: 30
					}]
				}, e.prototype.findAutomationPosition = function (e, t) {
					void 0 === e && (e = !0), void 0 === t && (t = !0);
					for (var a = y.default.current, o = -1, n = 0; n < this.animatableMaps.length; n++) this.animatableMaps[n] == a.ida && (o = n);
					return t ? this.animatableMaps.length && -1 != o ? (e ? o + 1 == this.animatableMaps.length ? o = 0 : o += 1 : 0 == o ? o = this.animatableMaps.length - 1 : o -= 1, o) : -1 : o
				}, e.prototype.startAnimation = function (e) {
					if (void 0 === e && (e = !0), !this.isAnimationStarted()) {
						c.default.animationStarted = !0, this.setState({
							playButton: !1,
							playReverseButton: !1,
							stopButton: !0,
							backButton: !1,
							forwardButton: !1,
							firstButton: !1,
							lastButton: !1,
							mapSlider: !1
						});
						var t = this.findAutomationPosition(!this.animationReverse);
						0 <= t && this.prepareMap(t)
					}
				}, e.prototype.isAnimationStarted = function () {
					return c.default.animationStarted
				}, e.prototype.stopAnimation = function () {
					this.setState({
						playButton: !0,
						playReverseButton: !0,
						stopButton: !1,
						backButton: !0,
						forwardButton: !0,
						firstButton: !0,
						lastButton: !0,
						mapSlider: !0
					}), c.default.animationStarted = !1, setTimeout(function () {
						c.default.emitter.once(v.STRUCTURE_LOADED, function () {
							f.default.triggerCurrentPattern([y.default.current.ida])
						}), g.default.clearCurrentStructureNode(), g.default.prepareRootStructure()
					}, 1e3)
				}, e.prototype.prepareMap = function (e) {
					var t = this;
					if ((this.isAnimationStarted() || this.triggeredManually) && this.animatableMaps.length) {
						if (this.animationReverse && e < 0) {
							if (e = this.animatableMaps.length - 1, !this.state.loopAnimation) return void this.stopAnimation()
						} else if (!this.animationReverse && e >= this.animatableMaps.length && (e = 0, !this.state.loopAnimation)) return void this.stopAnimation();
						this.triggeredManually = !1, this.setState({
							activeStep: e
						});
						var a = this.animatableMaps[e];
						void 0 !== this.animatablePatterns[a] && this.animatablePatterns[a];
						this.checkIntervalSelect(), this.isAnimationStarted() && c.default.emitter.once(v.THEMATICALMAP + a, function () {
							t.prepareNextMap(e)
						}), y.default.loadThematicalMap(a, !0)
					}
				}, e.prototype.prepareNextMap = function (e) {
					var t = this;
					this.setState({
						isBusy: !1
					}), setTimeout(function () {
						return t.prepareMap(t.animationReverse ? --e : ++e)
					}, 1e3 * this.state.automationInterval)
				}, e.prototype.onPlayButtonClick = function (e) {
					this.isAnimationStarted() || (this.animationReverse = !1, this.startAnimation())
				}, e.prototype.onStopClick = function (e) {
					this.triggeredManually = !1, this.stopAnimation()
				}, e.prototype.onPlayReverseButtonClick = function (e) {
					this.isAnimationStarted() || (this.animationReverse = !0, this.startAnimation())
				}, e.prototype.onBackButtonClick = function (e) {
					var t = this.findAutomationPosition(!1);
					this.triggeredManually = !0, this.prepareMap(t)
				}, e.prototype.onForwardButtonClick = function (e) {
					var t = this.findAutomationPosition(!0);
					this.triggeredManually = !0, this.prepareMap(t)
				}, e.prototype.onFirstButtonClick = function (e) {
					this.triggeredManually = !0, this.prepareMap(0)
				}, e.prototype.onLastButtonClick = function (e) {
					this.triggeredManually = !0, this.prepareMap(this.animatableMaps.length - 1)
				}, e.prototype.onInfoIconClick = function (e) {
					var t = p.default.localization,
						a = t.messages.MAP_AUTOMATION_DESCRIPTION;
					c.default.vars.isPreview && (a += "\n\n" + t.messages.ANIMATION_PREVIEW_WARNING), c.default.setSnackbarText(a), c.default.snackbarOpen = !0
				}, e.prototype.onSettingsIconClick = function (e) {
					this.onAnimatableMapsComplete(), this.setState({
						settingsVisible: !0
					})
				}, e.prototype.onCloseIconClick = function (e) {
					this.setState({
						settingsVisible: !1
					})
				}, e.prototype.onSelectBoxChange = function (e) {
					var t;
					this.setState(((t = {})[e.target.name] = e.target.value, t))
				}, e.prototype.onLoopAnimationCheckboxChange = function (e) {
					this.setState({
						loopAnimation: e.target.checked
					})
				}, e.prototype.onStepLabelClick = function (e) {
					c.default.animationStarted || (this.triggeredManually = !0, this.prepareMap(e))
				}, e.prototype.renderSettingsBar = function () {
					var a = this,
						e = this.props.classes,
						t = s.createElement(b.StepConnector, {
							className: e.stepConnector
						});
					return this.state.settingsVisible ? s.createElement("div", null, s.createElement(b.Stepper, {
						connector: t,
						nonLinear: !0,
						orientation: "vertical",
						activeStep: this.state.activeStep,
						className: e.stepper
					}, this.state.mapTitles.map(function (e, t) {
						return s.createElement(b.Step, {
							key: e.ida,
							completed: !1
						}, s.createElement(b.StepLabel, {
							className: c.default.animationStarted ? null : "href",
							onClick: function (e) {
								return a.onStepLabelClick(t)
							}
						}, e.name))
					})), s.createElement(b.Divider, null), s.createElement("form", {
						className: e.settings
					}, s.createElement("div", null, s.createElement(b.Typography, {
						className: e.intervalTitle
					}, p.default.localization.messages.MAP_ANIMATION_INTERVAL), s.createElement(b.FormControl, {
						className: i(e.formControl, e.intervalSelect)
					}, s.createElement(b.Select, {
						value: this.state.automationInterval,
						onChange: function (e) {
							return a.onSelectBoxChange(e)
						},
						inputProps: {
							name: "automationInterval",
							id: "automationInterval-select"
						}
					}, this.state.intervalSelect.map(function (e) {
						return s.createElement(b.MenuItem, {
							key: "interval" + e.value,
							value: e.value
						}, e.name)
					})))), s.createElement("div", null, s.createElement(b.FormControlLabel, {
						control: s.createElement(b.Checkbox, {
							color: "default",
							checked: this.state.loopAnimation,
							onChange: function (e) {
								return a.onLoopAnimationCheckboxChange(e)
							},
							value: "loop"
						}),
						label: p.default.localization.messages.BUTTON_LOOP_ANIMATION
					})))) : null
				}, e.prototype.render = function () {
					var t = this,
						e = this.props.classes;
					return !c.default.showAutomationControls || c.default.automationControlsMinimized || c.default.treeDrawerOpen ? null : s.createElement(d.default, null, s.createElement(b.Paper, {
						className: e.paper
					}, s.createElement("div", null, s.createElement("table", {
						className: e.table
					}, s.createElement("tbody", null, s.createElement("tr", null, s.createElement("td", null, s.createElement(b.Typography, {
						variant: "h6",
						color: "inherit"
					}, p.default.localization.messages.MAP_AUTOMATION_HEADLINE)), s.createElement("td", {
						className: "right"
					}, s.createElement(l.InformationOutline, {
						onClick: function (e) {
							return t.onInfoIconClick(e)
						}
					}), this.state.settingsVisible ? s.createElement(l.Close, {
						onClick: function (e) {
							return t.onCloseIconClick(e)
						}
					}) : s.createElement(l.Settings, {
						onClick: function (e) {
							return t.onSettingsIconClick(e)
						}
					}), c.default.animationStarted ? null : s.createElement(l.ChevronDown, {
						onClick: function () {
							c.default.automationControlsMinimized = !0
						},
						className: "href"
					}))))), s.createElement("div", {
						className: e.controlButtons
					}, s.createElement(b.Fab, {
						size: "small",
						color: "primary",
						className: e.button,
						disabled: !this.state.backButton,
						onClick: function (e) {
							return t.onBackButtonClick(e)
						},
						title: this.state.texts.backButton
					}, s.createElement(l.ControlsSkipPrevious, null)), s.createElement(b.Fab, {
						size: "small",
						color: "primary",
						disabled: !this.state.playReverseButton,
						className: i(e.button),
						onClick: function (e) {
							return t.onPlayReverseButtonClick(e)
						},
						title: this.state.texts.playReverseButton
					}, s.createElement(l.ControlsPlay, {
						className: e.rotate
					})), s.createElement(b.Fab, {
						size: "small",
						color: "primary",
						className: e.button,
						disabled: !this.state.stopButton,
						onClick: function (e) {
							return t.onStopClick(e)
						},
						title: this.state.texts.stopButton
					}, s.createElement(l.ControlsStop, null)), s.createElement(b.Fab, {
						size: "small",
						color: "primary",
						className: e.button,
						disabled: !this.state.playButton,
						onClick: function (e) {
							return t.onPlayButtonClick(e)
						},
						title: this.state.texts.playButton
					}, s.createElement(l.ControlsPlay, null)), s.createElement(b.Fab, {
						size: "small",
						color: "primary",
						className: e.button,
						disabled: !this.state.forwardButton,
						onClick: function (e) {
							return t.onForwardButtonClick(e)
						},
						title: this.state.texts.forwardButton
					}, s.createElement(l.ControlsSkipNext, null))), c.default.animationStarted ? s.createElement(b.LinearProgress, {
						className: e.progress
					}) : null, this.renderSettingsBar())))
				}, e = r([_.observer], e)
			}(s.Component);
		t.default = b.withStyles(function (e) {
			return {
				paper: {
					padding: 10,
					display: "table",
					margin: "0 auto"
				},
				button: {
					minHeight: "auto",
					minWidth: "auto",
					padding: 4,
					margin: 4
				},
				controlButtons: {
					textAlign: "center"
				},
				rotate: {
					transform: "rotate(180deg)"
				},
				slider: {
					padding: "22px 0px"
				},
				table: {
					width: "100%",
					"& td.right": {
						textAlign: "right"
					}
				},
				settings: {
					textAlign: "center",
					marginTop: 10
				},
				intervalTitle: {
					marginRight: 10,
					verticalAlign: "middle",
					display: "inline-block"
				},
				intervalSelect: {
					verticalAlign: "middle"
				},
				stepper: {
					maxHeight: 400,
					overflowY: "auto"
				},
				stepConnector: {
					"&>span": {
						minHeight: 2
					}
				},
				progress: {
					marginTop: 10
				}
			}
		})(T)
	},
	931: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
				return (o = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			}),
			r = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var i = a(1),
			l = a(29),
			s = a(28),
			c = a(20),
			d = a(10),
			p = a(375),
			u = a(18),
			f = a(84),
			h = a(150),
			m = function (a) {
				function e(e) {
					var t = a.call(this, e) || this;
					return t.state = {}, t.state = {
						wtext: ""
					}, t
				}
				return n(e, a), e.prototype.componentDidMount = function () {
					var e = this;
					d.default.emitter.on(u.THEMATICALMAP_LOADED, function () {
						e.renderNumbers()
					}), d.default.emitter.on(u.THEMATICALMAP_ZOOMED, function () {
						e.renderNumbers()
					}), d.default.emitter.on(u.WINDOW_RESIZED, function () {
						e.renderNumbers()
					})
				}, e.prototype.renderNumbers = function () {
					var e = c.default.current;
					if (void 0 !== e) {
						var t = e.geosystem,
							a = p.default.get(t),
							o = a.xMax - a.xMin,
							n = a.yMin - a.yMax,
							r = o / a.ratioM2PX,
							i = n / a.ratioM2PX,
							l = (document.getElementById("bg").getBoundingClientRect().width, document.getElementById("bg").getBoundingClientRect().height, d.default.rulerWidth / document.getElementById("bg").getBoundingClientRect().width),
							s = h.default.getBetterRulerValue(o * l);
						d.default.adaptedRulerWidth = s / o * document.getElementById("bg").getBoundingClientRect().width;
						var u = "";
						u = 1e4 < s ? f.default.showValue(f.default.betterRounding(s / 1e3, 1)) + " km" : f.default.showValue(f.default.betterRounding(s, 1)) + " m", this.setState({
							wtext: u
						})
					}
				}, e.prototype.render = function () {
					var e = this.props.classes;
					return void 0 !== c.default.current ? i.createElement("div", {
						className: e.ruler,
						style: {
							width: d.default.adaptedRulerWidth
						}
					}, i.createElement("div", {
						className: "row"
					}, i.createElement("div", {
						className: "col"
					}, "0"), i.createElement("div", {
						className: "col right"
					}, this.state.wtext)), i.createElement("div", {
						className: e.rulerGraph
					})) : null
				}, e = r([l.observer], e)
			}(i.Component);
		t.default = s.withStyles(function (e) {
			return {
				ruler: {
					"& .row": {
						margin: 0
					},
					"& .col": {
						padding: 0
					}
				},
				rulerGraph: {
					height: 6,
					border: "1px solid black",
					borderTop: "0px none"
				}
			}
		})(m)
	},
	932: function (e, t, a) {
		"use strict";
		var o, n = this && this.__extends || (o = function (e, t) {
				return (o = Object.setPrototypeOf || {
						__proto__: []
					}
					instanceof Array && function (e, t) {
						e.__proto__ = t
					} || function (e, t) {
						for (var a in t) t.hasOwnProperty(a) && (e[a] = t[a])
					})(e, t)
			}, function (e, t) {
				function a() {
					this.constructor = e
				}
				o(e, t), e.prototype = null === t ? Object.create(t) : (a.prototype = t.prototype, new a)
			}),
			r = this && this.__decorate || function (e, t, a, o) {
				var n, r = arguments.length,
					i = r < 3 ? t : null === o ? o = Object.getOwnPropertyDescriptor(t, a) : o;
				if ("object" == typeof Reflect && "function" == typeof Reflect.decorate) i = Reflect.decorate(e, t, a, o);
				else
					for (var l = e.length - 1; 0 <= l; l--)(n = e[l]) && (i = (r < 3 ? n(i) : 3 < r ? n(t, a, i) : n(t, a)) || i);
				return 3 < r && i && Object.defineProperty(t, a, i), i
			};
		Object.defineProperty(t, "__esModule", {
			value: !0
		});
		var i = a(1),
			l = a(28),
			s = a(29),
			u = a(48),
			c = a(933),
			d = a(934),
			p = a(935),
			f = a(936),
			h = a(937),
			m = a(938),
			g = a(939),
			y = a(940),
			v = a(941),
			b = a(942),
			_ = a(408),
			T = a(20),
			E = a(10);
		c.default.library.add(d, p, f, h, g, m, y, v);
		var S = function (e) {
			function t() {
				return null !== e && e.apply(this, arguments) || this
			}
			return n(t, e), t.prototype.calculateLuminance = function (e) {
				return .85 < new _("#" + e).luminosity() ? "darkbg" : "lightbg"
			}, t.prototype.renderOnAvailable = function () {
				var a = this,
					e = u.default.currentRegionInfo;
				T.default.current;
				return void 0 !== e.data && e.data.length ? i.createElement("div", {
					className: "regionInfo"
				}, i.createElement("div", null, i.createElement("div", {
					className: "title"
				}, i.createElement(l.Typography, {
					variant: "subtitle2"
				}, e.label, " "))), E.default.isLiveMap && !E.default.isLiveActive || !E.default.isSymbolOn && !E.default.isArealOn ? null : i.createElement("div", null, i.createElement("table", {
					className: "dataTable"
				}, i.createElement("tbody", null, e.data.map(function (e, t) {
					return E.default.isSymbolOn && -1 < ["sym", "sect", "bar"].indexOf(e.type) || E.default.isArealOn && -1 < ["choro", "quali"].indexOf(e.type) ? i.createElement("tr", {
						key: "inforow" + t
					}, i.createElement("td", {
						className: a.calculateLuminance(e.color)
					}, i.createElement(b.FontAwesomeIcon, {
						icon: e.symbol,
						color: "#" + e.color,
						transform: {
							rotate: e.rotation ? e.rotation : 0
						}
					})), i.createElement("td", {
						className: "lightbg"
					}, e.label, ":"), i.createElement("td", {
						className: "lightbg fvalue"
					}, e.fvalue), i.createElement("td", {
						className: "lightbg fvalue"
					}, void 0 !== e.fpercentage ? e.fpercentage : null)) : null
				}))))) : null
			}, t.prototype.render = function () {
				return this.renderOnAvailable()
			}, t = r([s.observer], t)
		}(i.Component);
		t.RegionInfo = S
	}
});