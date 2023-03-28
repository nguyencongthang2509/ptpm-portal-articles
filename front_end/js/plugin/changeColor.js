!(function (t, r) {
  "object" == typeof exports && "undefined" != typeof module
    ? (module.exports = r())
    : "function" == typeof define && define.amd
    ? define(r)
    : ((t || self).ColorThief = r());
})(this, function () {
  if (!t)
    var t = {
      map: function (t, r) {
        var n = {};
        return r
          ? t.map(function (t, o) {
              return (n.index = o), r.call(n, t);
            })
          : t.slice();
      },
      naturalOrder: function (t, r) {
        return t < r ? -1 : t > r ? 1 : 0;
      },
      sum: function (t, r) {
        var n = {};
        return t.reduce(
          r
            ? function (t, o, e) {
                return (n.index = e), t + r.call(n, o);
              }
            : function (t, r) {
                return t + r;
              },
          0
        );
      },
      max: function (r, n) {
        return Math.max.apply(null, n ? t.map(r, n) : r);
      },
    };
  var r = (function () {
      var r = 5,
        n = 8 - r,
        o = 1e3;
      function e(t, n, o) {
        return (t << (2 * r)) + (n << r) + o;
      }
      function i(t) {
        var r = [],
          n = !1;
        function o() {
          r.sort(t), (n = !0);
        }
        return {
          push: function (t) {
            r.push(t), (n = !1);
          },
          peek: function (t) {
            return n || o(), void 0 === t && (t = r.length - 1), r[t];
          },
          pop: function () {
            return n || o(), r.pop();
          },
          size: function () {
            return r.length;
          },
          map: function (t) {
            return r.map(t);
          },
          debug: function () {
            return n || o(), r;
          },
        };
      }
      function u(t, r, n, o, e, i, u) {
        var a = this;
        (a.r1 = t),
          (a.r2 = r),
          (a.g1 = n),
          (a.g2 = o),
          (a.b1 = e),
          (a.b2 = i),
          (a.histo = u);
      }
      function a() {
        this.vboxes = new i(function (r, n) {
          return t.naturalOrder(
            r.vbox.count() * r.vbox.volume(),
            n.vbox.count() * n.vbox.volume()
          );
        });
      }
      function c(r, n) {
        if (n.count()) {
          var o = n.r2 - n.r1 + 1,
            i = n.g2 - n.g1 + 1,
            u = t.max([o, i, n.b2 - n.b1 + 1]);
          if (1 == n.count()) return [n.copy()];
          var a,
            c,
            f,
            s,
            l = 0,
            h = [],
            v = [];
          if (u == o)
            for (a = n.r1; a <= n.r2; a++) {
              for (s = 0, c = n.g1; c <= n.g2; c++)
                for (f = n.b1; f <= n.b2; f++) s += r[e(a, c, f)] || 0;
              h[a] = l += s;
            }
          else if (u == i)
            for (a = n.g1; a <= n.g2; a++) {
              for (s = 0, c = n.r1; c <= n.r2; c++)
                for (f = n.b1; f <= n.b2; f++) s += r[e(c, a, f)] || 0;
              h[a] = l += s;
            }
          else
            for (a = n.b1; a <= n.b2; a++) {
              for (s = 0, c = n.r1; c <= n.r2; c++)
                for (f = n.g1; f <= n.g2; f++) s += r[e(c, f, a)] || 0;
              h[a] = l += s;
            }
          return (
            h.forEach(function (t, r) {
              v[r] = l - t;
            }),
            (function (t) {
              var r,
                o,
                e,
                i,
                u,
                c = t + "1",
                f = t + "2",
                s = 0;
              for (a = n[c]; a <= n[f]; a++)
                if (h[a] > l / 2) {
                  for (
                    e = n.copy(),
                      i = n.copy(),
                      u =
                        (r = a - n[c]) <= (o = n[f] - a)
                          ? Math.min(n[f] - 1, ~~(a + o / 2))
                          : Math.max(n[c], ~~(a - 1 - r / 2));
                    !h[u];

                  )
                    u++;
                  for (s = v[u]; !s && h[u - 1]; ) s = v[--u];
                  return (e[f] = u), (i[c] = e[f] + 1), [e, i];
                }
            })(u == o ? "r" : u == i ? "g" : "b")
          );
        }
      }
      return (
        (u.prototype = {
          volume: function (t) {
            var r = this;
            return (
              (r._volume && !t) ||
                (r._volume =
                  (r.r2 - r.r1 + 1) * (r.g2 - r.g1 + 1) * (r.b2 - r.b1 + 1)),
              r._volume
            );
          },
          count: function (t) {
            var r = this,
              n = r.histo;
            if (!r._count_set || t) {
              var o,
                i,
                u,
                a = 0;
              for (o = r.r1; o <= r.r2; o++)
                for (i = r.g1; i <= r.g2; i++)
                  for (u = r.b1; u <= r.b2; u++) a += n[e(o, i, u)] || 0;
              (r._count = a), (r._count_set = !0);
            }
            return r._count;
          },
          copy: function () {
            var t = this;
            return new u(t.r1, t.r2, t.g1, t.g2, t.b1, t.b2, t.histo);
          },
          avg: function (t) {
            var n = this,
              o = n.histo;
            if (!n._avg || t) {
              var i,
                u,
                a,
                c,
                f = 0,
                s = 1 << (8 - r),
                l = 0,
                h = 0,
                v = 0;
              for (u = n.r1; u <= n.r2; u++)
                for (a = n.g1; a <= n.g2; a++)
                  for (c = n.b1; c <= n.b2; c++)
                    (f += i = o[e(u, a, c)] || 0),
                      (l += i * (u + 0.5) * s),
                      (h += i * (a + 0.5) * s),
                      (v += i * (c + 0.5) * s);
              n._avg = f
                ? [~~(l / f), ~~(h / f), ~~(v / f)]
                : [
                    ~~((s * (n.r1 + n.r2 + 1)) / 2),
                    ~~((s * (n.g1 + n.g2 + 1)) / 2),
                    ~~((s * (n.b1 + n.b2 + 1)) / 2),
                  ];
            }
            return n._avg;
          },
          contains: function (t) {
            var r = this,
              o = t[0] >> n;
            return (
              (gval = t[1] >> n),
              (bval = t[2] >> n),
              o >= r.r1 &&
                o <= r.r2 &&
                gval >= r.g1 &&
                gval <= r.g2 &&
                bval >= r.b1 &&
                bval <= r.b2
            );
          },
        }),
        (a.prototype = {
          push: function (t) {
            this.vboxes.push({ vbox: t, color: t.avg() });
          },
          palette: function () {
            return this.vboxes.map(function (t) {
              return t.color;
            });
          },
          size: function () {
            return this.vboxes.size();
          },
          map: function (t) {
            for (var r = this.vboxes, n = 0; n < r.size(); n++)
              if (r.peek(n).vbox.contains(t)) return r.peek(n).color;
            return this.nearest(t);
          },
          nearest: function (t) {
            for (var r, n, o, e = this.vboxes, i = 0; i < e.size(); i++)
              ((n = Math.sqrt(
                Math.pow(t[0] - e.peek(i).color[0], 2) +
                  Math.pow(t[1] - e.peek(i).color[1], 2) +
                  Math.pow(t[2] - e.peek(i).color[2], 2)
              )) < r ||
                void 0 === r) &&
                ((r = n), (o = e.peek(i).color));
            return o;
          },
          forcebw: function () {
            var r = this.vboxes;
            r.sort(function (r, n) {
              return t.naturalOrder(t.sum(r.color), t.sum(n.color));
            });
            var n = r[0].color;
            n[0] < 5 && n[1] < 5 && n[2] < 5 && (r[0].color = [0, 0, 0]);
            var o = r.length - 1,
              e = r[o].color;
            e[0] > 251 &&
              e[1] > 251 &&
              e[2] > 251 &&
              (r[o].color = [255, 255, 255]);
          },
        }),
        {
          quantize: function (f, s) {
            if (!f.length || s < 2 || s > 256) return !1;
            var l = (function (t) {
              var o,
                i = new Array(1 << (3 * r));
              return (
                t.forEach(function (t) {
                  (o = e(t[0] >> n, t[1] >> n, t[2] >> n)),
                    (i[o] = (i[o] || 0) + 1);
                }),
                i
              );
            })(f);
            l.forEach(function () {});
            var h = (function (t, r) {
                var o,
                  e,
                  i,
                  a = 1e6,
                  c = 0,
                  f = 1e6,
                  s = 0,
                  l = 1e6,
                  h = 0;
                return (
                  t.forEach(function (t) {
                    (o = t[0] >> n) < a ? (a = o) : o > c && (c = o),
                      (e = t[1] >> n) < f ? (f = e) : e > s && (s = e),
                      (i = t[2] >> n) < l ? (l = i) : i > h && (h = i);
                  }),
                  new u(a, c, f, s, l, h, r)
                );
              })(f, l),
              v = new i(function (r, n) {
                return t.naturalOrder(r.count(), n.count());
              });
            function g(t, r) {
              for (var n, e = t.size(), i = 0; i < o; ) {
                if (e >= r) return;
                if (i++ > o) return;
                if ((n = t.pop()).count()) {
                  var u = c(l, n),
                    a = u[0],
                    f = u[1];
                  if (!a) return;
                  t.push(a), f && (t.push(f), e++);
                } else t.push(n), i++;
              }
            }
            v.push(h), g(v, 0.75 * s);
            for (
              var p = new i(function (r, n) {
                return t.naturalOrder(
                  r.count() * r.volume(),
                  n.count() * n.volume()
                );
              });
              v.size();

            )
              p.push(v.pop());
            g(p, s);
            for (var b = new a(); p.size(); ) b.push(p.pop());
            return b;
          },
        }
      );
    })().quantize,
    n = function (t) {
      (this.canvas = document.createElement("canvas")),
        (this.context = this.canvas.getContext("2d")),
        (this.width = this.canvas.width = t.naturalWidth),
        (this.height = this.canvas.height = t.naturalHeight),
        this.context.drawImage(t, 0, 0, this.width, this.height);
    };
  n.prototype.getImageData = function () {
    return this.context.getImageData(0, 0, this.width, this.height);
  };
  var o = function () {};
  return (
    (o.prototype.getColor = function (t, r) {
      return void 0 === r && (r = 10), this.getPalette(t, 5, r)[0];
    }),
    (o.prototype.getPalette = function (t, o, e) {
      var i = (function (t) {
          var r = t.colorCount,
            n = t.quality;
          if (void 0 !== r && Number.isInteger(r)) {
            if (1 === r)
              throw new Error(
                "colorCount should be between 2 and 20. To get one color, call getColor() instead of getPalette()"
              );
            (r = Math.max(r, 2)), (r = Math.min(r, 20));
          } else r = 10;
          return (
            (void 0 === n || !Number.isInteger(n) || n < 1) && (n = 10),
            { colorCount: r, quality: n }
          );
        })({ colorCount: o, quality: e }),
        u = new n(t),
        a = (function (t, r, n) {
          for (var o, e, i, u, a, c = t, f = [], s = 0; s < r; s += n)
            (e = c[0 + (o = 4 * s)]),
              (i = c[o + 1]),
              (u = c[o + 2]),
              (void 0 === (a = c[o + 3]) || a >= 125) &&
                ((e > 250 && i > 250 && u > 250) || f.push([e, i, u]));
          return f;
        })(u.getImageData().data, u.width * u.height, i.quality),
        c = r(a, i.colorCount);
      return c ? c.palette() : null;
    }),
    (o.prototype.getColorFromUrl = function (t, r, n) {
      var o = this,
        e = document.createElement("img");
      e.addEventListener("load", function () {
        var i = o.getPalette(e, 5, n);
        r(i[0], t);
      }),
        (e.src = t);
    }),
    (o.prototype.getImageData = function (t, r) {
      var n = new XMLHttpRequest();
      n.open("GET", t, !0),
        (n.responseType = "arraybuffer"),
        (n.onload = function () {
          if (200 == this.status) {
            var t = new Uint8Array(this.response);
            i = t.length;
            for (var n = new Array(i), o = 0; o < t.length; o++)
              n[o] = String.fromCharCode(t[o]);
            var e = n.join(""),
              u = window.btoa(e);
            r("data:image/png;base64," + u);
          }
        }),
        n.send();
    }),
    (o.prototype.getColorAsync = function (t, r, n) {
      var o = this;
      this.getImageData(t, function (t) {
        var e = document.createElement("img");
        e.addEventListener("load", function () {
          var t = o.getPalette(e, 5, n);
          r(t[0], this);
        }),
          (e.src = t);
      });
    }),
    o
  );
});
1;
