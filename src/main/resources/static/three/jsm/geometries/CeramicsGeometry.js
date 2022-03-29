import {
    BufferAttribute,
    BufferGeometry,
    Matrix4,
    Vector3,
    Vector4
} from "/three/build/three.modul.min.js";

var VaseBufferGeometry = function (dataparams) {
    let size = 400;
    let segments = 15;
    let blinn = false;

    if (dataparams.b2) {
        segments = 2
    }

    // 32 * 4 * 4 Bezier spline patches
    let vasePatches = [
        /*rim*/
        0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15,
        3, 16, 17, 18, 7, 19, 20, 21, 11, 22, 23, 24, 15, 25, 26, 27,
        18, 28, 29, 30, 21, 31, 32, 33, 24, 34, 35, 36, 27, 37, 38, 39,
        30, 40, 41, 0, 33, 42, 43, 4, 36, 44, 45, 8, 39, 46, 47, 12,
        /*body*/
        12, 13, 14, 15, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59,
        15, 25, 26, 27, 51, 60, 61, 62, 55, 63, 64, 65, 59, 66, 67, 68,
        27, 37, 38, 39, 62, 69, 70, 71, 65, 72, 73, 74, 68, 75, 76, 77,
        39, 46, 47, 12, 71, 78, 79, 48, 74, 80, 81, 52, 77, 82, 83, 56,
        56, 57, 58, 59, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95,
        59, 66, 67, 68, 87, 96, 97, 98, 91, 99, 100, 101, 95, 102, 103, 104,
        68, 75, 76, 77, 98, 105, 106, 107, 101, 108, 109, 110, 104, 111, 112, 113,
        77, 82, 83, 56, 107, 114, 115, 84, 110, 116, 117, 88, 113, 118, 119, 92,
        /*bottom*/
        120, 120, 120, 120, 121, 122, 123, 124, 125, 126, 127, 128, 92, 119, 118, 113,
        120, 120, 120, 120, 124, 129, 130, 131, 128, 132, 133, 134, 113, 112, 111, 104,
        120, 120, 120, 120, 131, 135, 136, 137, 134, 138, 139, 140, 104, 103, 102, 95,
        120, 120, 120, 120, 137, 141, 142, 121, 140, 143, 144, 125, 95, 94, 93, 92
    ];

    let vaseVertices = [
        1.4, 0, 2.4,//rim
        1.4, -0.784, 2.4,
        0.784, -1.4, 2.4,
        0, -1.4, 2.4,
        1.3375, 0, 2.53125,
        1.3375, -0.749, 2.53125,
        0.749, -1.3375, 2.53125,
        0, -1.3375, 2.53125,
        1.4375, 0, 2.53125,
        1.4375, -0.805, 2.53125,
        0.805, -1.4375, 2.53125,
        0, -1.4375, 2.53125,
        1.5, 0, 2.4,
        1.5, -0.84, 2.4,
        0.84, -1.5, 2.4,
        0, -1.5, 2.4,//body
        -0.784, -1.4, 2.4,
        -1.4, -0.784, 2.4,
        -1.4, 0, 2.4,
        -0.749, -1.3375, 2.53125,
        -1.3375, -0.749, 2.53125,
        -1.3375, 0, 2.53125,
        -0.805, -1.4375, 2.53125,
        -1.4375, -0.805, 2.53125,
        -1.4375, 0, 2.53125,
        -0.84, -1.5, 2.4,
        -1.5, -0.84, 2.4,
        -1.5, 0, 2.4,
        -1.4, 0.784, 2.4,
        -0.784, 1.4, 2.4,
        0, 1.4, 2.4,
        -1.3375, 0.749, 2.53125,
        -0.749, 1.3375, 2.53125,
        0, 1.3375, 2.53125,
        -1.4375, 0.805, 2.53125,
        -0.805, 1.4375, 2.53125,
        0, 1.4375, 2.53125,
        -1.5, 0.84, 2.4,
        -0.84, 1.5, 2.4,
        0, 1.5, 2.4,
        0.784, 1.4, 2.4,
        1.4, 0.784, 2.4,
        0.749, 1.3375, 2.53125,
        1.3375, 0.749, 2.53125,
        0.805, 1.4375, 2.53125,
        1.4375, 0.805, 2.53125,
        0.84, 1.5, 2.4,
        1.5, 0.84, 2.4,
        1.75, 0, 1.875,
        1.75, -0.98, 1.875,
        0.98, -1.75, 1.875,
        0, -1.75, 1.875,
        2, 0, 1.35,
        2, -1.12, 1.35,
        1.12, -2, 1.35,
        0, -2, 1.35,
        2, 0, 0.9,
        2, -1.12, 0.9,
        1.12, -2, 0.9,
        0, -2, 0.9,
        -0.98, -1.75, 1.875,
        -1.75, -0.98, 1.875,
        -1.75, 0, 1.875,
        -1.12, -2, 1.35,
        -2, -1.12, 1.35,
        -2, 0, 1.35,
        -1.12, -2, 0.9,
        -2, -1.12, 0.9,
        -2, 0, 0.9,
        -1.75, 0.98, 1.875,
        -0.98, 1.75, 1.875,
        0, 1.75, 1.875,
        -2, 1.12, 1.35,
        -1.12, 2, 1.35,
        0, 2, 1.35,
        -2, 1.12, 0.9,
        -1.12, 2, 0.9,
        0, 2, 0.9,
        0.98, 1.75, 1.875,
        1.75, 0.98, 1.875,
        1.12, 2, 1.35,
        2, 1.12, 1.35,
        1.12, 2, 0.9,
        2, 1.12, 0.9,
        2, 0, 0.45,
        2, -1.12, 0.45,
        1.12, -2, 0.45,
        0, -2, 0.45,
        1.5, 0, 0.225,
        1.5, -0.84, 0.225,
        0.84, -1.5, 0.225,
        0, -1.5, 0.225,
        1.5, 0, 0.15,
        1.5, -0.84, 0.15,
        0.84, -1.5, 0.15,
        0, -1.5, 0.15,
        -1.12, -2, 0.45,
        -2, -1.12, 0.45,
        -2, 0, 0.45,
        -0.84, -1.5, 0.225,
        -1.5, -0.84, 0.225,
        -1.5, 0, 0.225,
        -0.84, -1.5, 0.15,
        -1.5, -0.84, 0.15,
        -1.5, 0, 0.15,
        -2, 1.12, 0.45,
        -1.12, 2, 0.45,
        0, 2, 0.45,
        -1.5, 0.84, 0.225,
        -0.84, 1.5, 0.225,
        0, 1.5, 0.225,
        -1.5, 0.84, 0.15,
        -0.84, 1.5, 0.15,
        0, 1.5, 0.15,
        1.12, 2, 0.45,
        2, 1.12, 0.45,
        0.84, 1.5, 0.225,
        1.5, 0.84, 0.225,
        0.84, 1.5, 0.15,
        1.5, 0.84, 0.15,
        0, 0, 0,//bottom
        1.425, 0, 0,
        1.425, 0.798, 0,
        0.798, 1.425, 0,
        0, 1.425, 0,
        1.5, 0, 0.075,
        1.5, 0.84, 0.075,
        0.84, 1.5, 0.075,
        0, 1.5, 0.075,
        -0.798, 1.425, 0,
        -1.425, 0.798, 0,
        -1.425, 0, 0,
        -0.84, 1.5, 0.075,
        -1.5, 0.84, 0.075,
        -1.5, 0, 0.075,
        -1.425, -0.798, 0,
        -0.798, -1.425, 0,
        0, -1.425, 0,
        -1.5, -0.84, 0.075,
        -0.84, -1.5, 0.075,
        0, -1.5, 0.075,
        0.798, -1.425, 0,
        1.425, -0.798, 0,
        0.84, -1.5, 0.075,
        1.5, -0.84, 0.075
    ];

    BufferGeometry.call(this);

    this.faces = [];


    let blinnScale = 1.3;
    if (dataparams.b2) {
        blinnScale = 1.2
    }

    let maxHeight = 3.15 * blinnScale;
    let maxHeight2 = maxHeight / 2;
    let trueSize = size / maxHeight2;


    let numTriangles = (8 * segments - 4) * segments;//bottom
    numTriangles += 40 * segments * segments;//body

    let indices = new Uint32Array(numTriangles * 3);//length 552 0

    let numVertices = 4;//bottom
    numVertices += 12;//body+rim
    numVertices *= (segments + 1) * (segments + 1);//216

    let vertices = new Float32Array(numVertices * 3);//length 648
    let normals = new Float32Array(numVertices * 3);//length 648
    let uvs = new Float32Array(numVertices * 2); //length 432

    // Bezier form
    let ms = new Matrix4();
    ms.set(
        -1.0, 3.0, -3.0, 1.0,
        3.0, -6.0, 3.0, 0.0,
        -3.0, 3.0, 0.0, 0.0,
        1.0, 0.0, 0.0, 0.0);

    let mgm = [];
    let norm = new Vector3();
    var tcoord;
    var vertPerRow;
    var normOut = new Vector3();
    var v1, v2, v3, v4;
    let tmtx = new Matrix4();
    var vsdir = new Vector3();
    var vtdir = new Vector3();

    let mst = ms.clone();
    mst.transpose();
    var notDegenerate = function (vtx1, vtx2, vtx3) {
        return !(((vertices[vtx1 * 3] === vertices[vtx2 * 3]) &&
            (vertices[vtx1 * 3 + 1] === vertices[vtx2 * 3 + 1]) &&
            (vertices[vtx1 * 3 + 2] === vertices[vtx2 * 3 + 2])) ||
            ((vertices[vtx1 * 3] === vertices[vtx3 * 3]) &&
                (vertices[vtx1 * 3 + 1] === vertices[vtx3 * 3 + 1]) &&
                (vertices[vtx1 * 3 + 2] === vertices[vtx3 * 3 + 2])) ||
            ((vertices[vtx2 * 3] === vertices[vtx3 * 3]) &&
                (vertices[vtx2 * 3 + 1] === vertices[vtx3 * 3 + 1]) &&
                (vertices[vtx2 * 3 + 2] === vertices[vtx3 * 3 + 2])));
    };


    for (let i = 0; i < 3; i++) {
        mgm[i] = new Matrix4();
    }


    let minPatches = 0;
    let maxPatches = 16;

    vertPerRow = segments + 1;

    var surfCount = 0;
    var vertCount = 0;
    var normCount = 0;
    var uvCount = 0;

    var indexCount = 0;

    let data = [];

    this.dataPoint = data;
    if (dataparams && dataparams.ps) {
        data[0] = (dataparams.p1 + 6) * 0.1;
        data[1] = (dataparams.p2 + 6) * 0.1;
        data[2] = (dataparams.p3 + 6) * 0.1;
        data[3] = (dataparams.p3 + dataparams.p5 + 12) / 20;
        data[4] = (dataparams.p5 + 6) * 0.1;
        data[5] = (dataparams.p6 + 6) * 0.1;
        data[6] = (dataparams.p7 + 6) * 0.1;
        data[7] = (dataparams.p8 + 6) * 0.1;
    }


    let g = [];
    let i, r, c;
    let gmx = new Matrix4();
    for (let surf = minPatches; surf < maxPatches; surf++) {
        for (i = 0; i < 3; i++) {//xyz
            for (r = 0; r < 4; r++) {//row
                for (c = 0; c < 4; c++) {//column
                    g[c * 4 + r] = vaseVertices[vasePatches[surf * 16 + r * 4 + c] * 3 + i];

                    if (i !== 2) {//xy

                        if (surf >= 12 && surf <= 16) {//
                            g[c * 4 + r] *= data[0];
                        } else if (surf >= 8 && surf <= 11) {//body bottom
                            if (r == 0) {
                                g[c * 4 + r] *= data[3];
                            } else if (r == 1) {
                                g[c * 4 + r] *= data[2];
                            } else if (r == 2) {
                                g[c * 4 + r] *= data[1];
                            } else if (r == 3) {
                                g[c * 4 + r] *= data[0];
                            }
                        } else if (surf <= 7 && surf >= 4) {//body top
                            if (r == 0) {
                                g[c * 4 + r] *= data[6];
                            } else if (r == 1) {
                                g[c * 4 + r] *= data[5];
                            } else if (r == 2) {
                                g[c * 4 + r] *= data[4];
                            } else if (r == 3) {
                                g[c * 4 + r] *= data[3];
                            }

                        } else if (surf >= 0 && surf <= 3) {//rim

                            g[c * 4 + r] *= data[6];
                        }

                    }

                    if (i == 2) {//z
                        g[c * 4 + r] *= data[7];
                    }

                    if (!blinn && (i === 2)) {
                        blinnScale = 1.2;
                        g[c * 4 + r] *= blinnScale;//
                    }

                }

            }


            gmx.set(g[0], g[1], g[2], g[3], g[4], g[5], g[6], g[7], g[8], g[9], g[10], g[11], g[12], g[13], g[14], g[15]);

            tmtx.multiplyMatrices(gmx, ms);//GM

            mgm[i].multiplyMatrices(mst, tmtx);//MtGM

        }


        let s, t, sval, tval, p;
        let dsval = 0;
        let dtval = 0;
        let sstep, tstep;
        let sp = [];
        let tp = [];
        let dsp = [];
        let dtp = [];

        let vsp = new Vector4();
        let vtp = new Vector4();
        let vdsp = new Vector4();
        let vdtp = new Vector4();

        for (sstep = 0; sstep <= segments; sstep++) {
            s = sstep / (segments);// 0 1

            for (tstep = 0; tstep <= segments; tstep++) {
                t = tstep / (segments); // 0 1

                for (p = 4, sval = tval = 1.0; p--;) {

                    sp[p] = sval;
                    tp[p] = tval;

                    sval *= s;
                    tval *= t;
                    if (p === 3) {
                        dsp[p] = dtp[p] = 0.0;
                        dsval = dtval = 1.0;
                    } else {
                        dsp[p] = dsval * (3 - p);
                        dtp[p] = dtval * (3 - p);
                        dsval *= s;
                        dtval *= t;
                    }


                }


                vsp.fromArray(sp);
                vtp.fromArray(tp);
                vdsp.fromArray(dsp);
                vdtp.fromArray(dtp);

                let vert = [];
                let sdir = [];
                let tdir = [];

                for (i = 0; i < 3; i++) {

                    tcoord = vsp.clone();
                    tcoord.applyMatrix4(mgm[i]);

                    vert[i] = tcoord.dot(vtp);
                    tcoord = vdsp.clone();
                    tcoord.applyMatrix4(mgm[i]);
                    sdir[i] = tcoord.dot(vtp);

                    tcoord = vsp.clone();
                    tcoord.applyMatrix4(mgm[i]);
                    tdir[i] = tcoord.dot(vdtp);

                }

                // find normal
                vsdir.fromArray(sdir);
                vtdir.fromArray(tdir);
                norm.crossVectors(vtdir, vsdir);
                norm.normalize();

                if (vert[0] === 0 && vert[1] === 0) {

                    normOut.set(0, vert[2] > maxHeight2 ? 1 : -1, 0);
                } else {

                    normOut.set(norm.x, norm.z, -norm.y);
                }

                vertices[vertCount++] = trueSize * vert[0];
                vertices[vertCount++] = trueSize * (vert[2] - maxHeight2);
                vertices[vertCount++] = -trueSize * vert[1];

                normals[normCount++] = normOut.x;
                normals[normCount++] = normOut.y;
                normals[normCount++] = normOut.z;


                uvs[uvCount++] = (1 - t);
                uvs[uvCount++] = (1 - s);


            }

        }


        for (sstep = 0; sstep < segments; sstep++) {//r

            for (tstep = 0; tstep < segments; tstep++) {//c

                v1 = surfCount * vertPerRow * vertPerRow + sstep * vertPerRow + tstep;
                v2 = v1 + 1;
                v3 = v2 + vertPerRow;
                v4 = v1 + vertPerRow;
                // Normals and UVs cannot be shared. Without clone(), you can see the consequences
                // of sharing if you call geometry.applyMatrix( matrix ).
                if (notDegenerate(v1, v2, v3)) {
                    indices[indexCount++] = v1;
                    indices[indexCount++] = v2;
                    indices[indexCount++] = v3;
                }
                if (notDegenerate(v1, v3, v4)) {
                    indices[indexCount++] = v1;
                    indices[indexCount++] = v3;
                    indices[indexCount++] = v4;
                }
            }
        }

        surfCount++;


    }


    var uvcount2 = 0;
    var uvs2 = new Float32Array(numVertices * 2);
    var s2, t2, sstep2, tstep2;
    var puvdata = [2.95, 5.125, 4.1000000000000005, 0.626, 0.761, 0];
    if (dataparams && dataparams.ps && dataparams.puv1) {
        puvdata[0] = dataparams.puv1;
        puvdata[1] = dataparams.puv2;
        puvdata[2] = dataparams.puv3;
        puvdata[3] = dataparams.puv4;
        puvdata[4] = dataparams.puv5;
        puvdata[5] = dataparams.rotate;
    }


    for (var surf2 = minPatches; surf2 < maxPatches; surf2++) {

        if (surf2 <= 7 && surf2 >= 4) {//body top

            for (sstep2 = 0; sstep2 <= segments; sstep2++) {
                s2 = sstep2 / (segments);
                for (tstep2 = 0; tstep2 <= segments; tstep2++) {
                    t2 = tstep2 / (segments);

                    if (surf2 == 4 || surf2 == 6) {
                        let face4 = [2.0500000000000003, 1, 1, -1.008, 0.063, 0];

                        s2 = sstep2 / (segments * face4[0]);
                        t2 = tstep2 / (segments * face4[0]);

                        let tt = ((1 - t2) - face4[3]) * face4[2];
                        let ss = ((1 - s2) - face4[4]) * face4[1];
                        let t = tt * Math.cos(face4[5]) + ss * Math.sin(face4[5]);
                        let s = ss * Math.cos(face4[5]) - tt * Math.sin(face4[5]);
                        uvs2[uvcount2++] = t;
                        uvs2[uvcount2++] = s;
                    } else if (surf2 == 5 || surf2 == 7) {
                        let face4 = [2.0500000000000003, 1, 1, -0.523, -0.937, 0];
                        //face4[0]=puvdata[0];
                        s2 = sstep2 / (segments * face4[0]);
                        t2 = tstep2 / (segments * face4[0]);

                        let tt = ((1 - t2) - face4[3]) * face4[2];
                        let ss = ((1 - s2) - face4[4]) * face4[1];
                        let t = tt * Math.cos(face4[5]) + ss * Math.sin(face4[5]);
                        let s = ss * Math.cos(face4[5]) - tt * Math.sin(face4[5]);
                        uvs2[uvcount2++] = t;
                        uvs2[uvcount2++] = s;
                    } else {
                        uvs2[uvcount2++] = 0;
                        uvs2[uvcount2++] = 0;
                    }

                }

            }


        } else if (surf2 >= 8 && surf2 <= 11) {//body bottom

            for (sstep2 = 0; sstep2 <= segments; sstep2++) {
                s2 = sstep2 / (segments);
                for (tstep2 = 0; tstep2 <= segments; tstep2++) {
                    t2 = tstep2 / (segments);
                    if (surf2 == 8 || surf2 == 10) {
                        let face4 = [2.0500000000000003, 1, 1, -0.01, 0.55, 0];
                        s2 = sstep2 / (segments * face4[0]);
                        t2 = tstep2 / (segments * face4[0]);
                        let tt = ((1 - t2) - face4[3]) * face4[2];
                        let ss = ((1 - s2) - face4[4]) * face4[1];
                        let t = tt * Math.cos(face4[5]) + ss * Math.sin(face4[5]);
                        let s = ss * Math.cos(face4[5]) - tt * Math.sin(face4[5]);
                        uvs2[uvcount2++] = t;
                        uvs2[uvcount2++] = s;
                    } else if (surf2 == 9 || surf2 == 11) {
                        let face4 = [2.0500000000000003, 1, 1, 1.475, 0.55, 0];
                        s2 = sstep2 / (segments * face4[0]);
                        t2 = tstep2 / (segments * face4[0]);
                        let tt = ((1 - t2) - face4[3]) * face4[2];
                        let ss = ((1 - s2) - face4[4]) * face4[1];
                        let t = tt * Math.cos(face4[5]) + ss * Math.sin(face4[5]);
                        let s = ss * Math.cos(face4[5]) - tt * Math.sin(face4[5]);
                        uvs2[uvcount2++] = t;
                        uvs2[uvcount2++] = s;
                    } else {
                        let face4 = [2.0500000000000003, 1, 1, 1.475, 0.55, 0];
                        s2 = sstep2 / (segments * face4[0]);
                        t2 = tstep2 / (segments * face4[0]);
                        let tt = ((1 - t2) - face4[3]) * face4[2];
                        let ss = ((1 - s2) - face4[4]) * face4[1];
                        let t = tt * Math.cos(face4[5]) + ss * Math.sin(face4[5]);
                        let s = ss * Math.cos(face4[5]) - tt * Math.sin(face4[5]);
                        uvs2[uvcount2++] = t;
                        uvs2[uvcount2++] = s;
                    }

                }

            }

        } else if (surf2 >= 12 && surf2 <= 16) {
            var ris = 1;
            for (sstep2 = 0; sstep2 <= segments; sstep2++) {
                s2 = (sstep2 / (segments));

                for (tstep2 = 0; tstep2 <= segments; tstep2++) {
                    t2 = tstep2 / (segments);
                    if (surf2 == 12) {
                        let face4 = puvdata;
                        s2 = sstep2 / (segments * face4[0]);
                        t2 = tstep2 / (segments * face4[0]);
                        let tt = ((1 - t2) - face4[3]) * face4[2];
                        let ss = ((1 - s2) - face4[4]) * face4[1];
                        let t = tt * Math.cos(face4[5]) + ss * Math.sin(face4[5]);
                        let s = ss * Math.cos(face4[5]) - tt * Math.sin(face4[5]);
                        //

                        uvs2[uvcount2++] = t;
                        uvs2[uvcount2++] = s;
                    } else if (surf2 == 13) {
                        let face4 = [1, 1, 1, 1, 0, 0];
                        s2 = sstep2 / (segments * face4[0]);
                        t2 = tstep2 / (segments * face4[0]);
                        let tt = ((1 - t2) - face4[3]) * face4[2];
                        let ss = ((1 - s2) - face4[4]) * face4[1];
                        let t = tt * Math.cos(face4[5]) + ss * Math.sin(face4[5]);
                        let s = ss * Math.cos(face4[5]) - tt * Math.sin(face4[5]);
                        uvs2[uvcount2++] = t;
                        uvs2[uvcount2++] = s;
                    } else {
                        let face4 = [1, 1, 1, 1, 0, 0];
                        s2 = sstep2 / (segments * face4[0]);
                        t2 = tstep2 / (segments * face4[0]);
                        let tt = ((1 - t2) - face4[3]) * face4[2];
                        let ss = ((1 - s2) - face4[4]) * face4[1];
                        let t = tt * Math.cos(face4[5]) + ss * Math.sin(face4[5]);
                        let s = ss * Math.cos(face4[5]) - tt * Math.sin(face4[5]);
                        uvs2[uvcount2++] = t;
                        uvs2[uvcount2++] = s;
                    }

                }

            }
        } else {
            for (sstep2 = 0; sstep2 <= segments; sstep2++) {

                if (surf2 <= 7 && surf2 >= 4) {

                    if (surf2 == 4) {
                        s2 = (sstep2 / (segments * puvdata[0])) * puvdata[1];
                    } else if (surf2 == 5) {
                        s2 = (sstep2 / (segments));
                    } else {
                        s2 = (sstep2 / segments);
                    }

                } else {
                    s2 = sstep2 / (segments * 1.2);//  1/15 2/15  3/15
                }

                for (tstep2 = 0; tstep2 <= segments; tstep2++) {

                    if (surf2 <= 7 && surf2 >= 4) {//body top
                        if (surf2 == 4) {
                            t2 = tstep2 / (segments * puvdata[2]) * puvdata[3];
                        } else if (surf2 == 5) {
                            t2 = tstep2 / (segments);
                        } else {
                            t2 = tstep2 / (segments);
                        }
                        uvs2[uvcount2++] = (1 - t2);
                        uvs2[uvcount2++] = (1 - s2);
                    } else if (surf2 >= 8 && surf2 <= 11) {
                        if (surf2 == 8) {
                            t2 = tstep2 / (segments);
                        } else {
                            t2 = tstep2 / (segments);
                        }

                        uvs2[uvcount2++] = (1 - t2);
                        uvs2[uvcount2++] = (1 - s2);
                    } else {
                        t2 = tstep2 / (segments);
                        uvs2[uvcount2++] = (1 - t2);
                        uvs2[uvcount2++] = (1 - s2);
                    }
                    //uvcount2++;
                }

            }
        }


    }


    this.setIndex(new BufferAttribute(indices, 1));

    this.setAttribute('position', new BufferAttribute(vertices, 3));
    this.setAttribute('normal', new BufferAttribute(normals, 3));
    this.setAttribute('uv', new BufferAttribute(uvs2, 2));

    this.addGroup(0, 5400, 0);
    this.addGroup(5400, 16200, 1);
    this.addGroup(16200, 18432, 2);

    this.addGroup(0, 5400, 3);
    this.addGroup(5400, 16200, 4);
    this.addGroup(16200, 18432, 5);
    this.computeBoundingSphere();
};


VaseBufferGeometry.prototype = Object.create(BufferGeometry.prototype);
VaseBufferGeometry.prototype.constructor = VaseBufferGeometry;

export {VaseBufferGeometry};
