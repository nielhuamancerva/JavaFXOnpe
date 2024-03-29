PGDMP         	                {            SCE    15.2    15.2 2    K           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            L           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            M           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            N           1262    16398    SCE    DATABASE     w   CREATE DATABASE "SCE" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'Spanish_Peru.1252';
    DROP DATABASE "SCE";
                postgres    false            O           0    0    DATABASE "SCE"    COMMENT     >   COMMENT ON DATABASE "SCE" IS 'Sistema de Cómputo Electoral';
                   postgres    false    3406            �            1259    16399    acta    TABLE        CREATE TABLE public.acta (
);
    DROP TABLE public.acta;
       public         heap    postgres    false            P           0    0 
   TABLE acta    COMMENT     ?   COMMENT ON TABLE public.acta IS 'Contiene actas electorales ';
          public          postgres    false    214            �            1259    16453    actainstalacion    TABLE     0  CREATE TABLE public.actainstalacion (
    ncodacta integer NOT NULL,
    dhorainicio date,
    bestadomaterial character(1),
    nnumcedulas integer,
    sobservaciones character varying(500),
    bfirma1 character(1),
    bfirma2 character(1),
    bfirma3 character(1),
    nnromesa integer NOT NULL
);
 #   DROP TABLE public.actainstalacion;
       public         heap    postgres    false            �            1259    16460    actasufragio    TABLE     ;  CREATE TABLE public.actasufragio (
    ncodacta integer NOT NULL,
    scodbarra character varying(20),
    dhorainiescrutinio date,
    sobservaciones character varying(500),
    dhorafinescrutinio date,
    bfirma1 character(1),
    bfirma2 character(1),
    bfirma3 character(1),
    nnromesa integer NOT NULL
);
     DROP TABLE public.actasufragio;
       public         heap    postgres    false            �            1259    16467    centrocomputo    TABLE     w   CREATE TABLE public.centrocomputo (
    ncodcentrocomputo integer NOT NULL,
    sdescripcion character varying(200)
);
 !   DROP TABLE public.centrocomputo;
       public         heap    postgres    false            �            1259    16472    eleccionesxubigeo    TABLE     �   CREATE TABLE public.eleccionesxubigeo (
    ncodprocesoelectoral integer NOT NULL,
    ncodtipoeleccion integer NOT NULL,
    scodubigeo character(6) NOT NULL
);
 %   DROP TABLE public.eleccionesxubigeo;
       public         heap    postgres    false            �            1259    16477    localvotación    TABLE     �   CREATE TABLE public."localvotación" (
    ncodlv integer NOT NULL,
    sdeslv character varying(250),
    scodubigeo character(6) NOT NULL,
    "sdirecciónlv" character varying(250)
);
 $   DROP TABLE public."localvotación";
       public         heap    postgres    false            �            1259    16484    mesaelectoral    TABLE     z   CREATE TABLE public.mesaelectoral (
    nnromesa integer NOT NULL,
    nelectores integer,
    ncodlv integer NOT NULL
);
 !   DROP TABLE public.mesaelectoral;
       public         heap    postgres    false            �            1259    16489    odpe    TABLE     a   CREATE TABLE public.odpe (
    ncododpe integer NOT NULL,
    sdesodpe character varying(100)
);
    DROP TABLE public.odpe;
       public         heap    postgres    false            �            1259    16494    procesoelectoral    TABLE     �   CREATE TABLE public.procesoelectoral (
    ncodprocesoelectoral integer NOT NULL,
    ssiglas character varying(10),
    dfechajornada date
);
 $   DROP TABLE public.procesoelectoral;
       public         heap    postgres    false            �            1259    16499    tipoeleccion    TABLE     y   CREATE TABLE public.tipoeleccion (
    ncodtipoeleccion integer NOT NULL,
    sdestipoeleccion character varying(200)
);
     DROP TABLE public.tipoeleccion;
       public         heap    postgres    false            �            1259    16504    tramasrecibidas    TABLE     �   CREATE TABLE public.tramasrecibidas (
    ncodtrama integer NOT NULL,
    strama text,
    dfechahora date,
    nestado integer
);
 #   DROP TABLE public.tramasrecibidas;
       public         heap    postgres    false            �            1259    16511    ubigeo    TABLE     �   CREATE TABLE public.ubigeo (
    scodubigeo character(6) NOT NULL,
    scoddepartamento character(2),
    scodprovincia character(2),
    scoddistrito character(2),
    ncododpe integer NOT NULL,
    ncodcentrocomputo integer NOT NULL
);
    DROP TABLE public.ubigeo;
       public         heap    postgres    false            =          0    16399    acta 
   TABLE DATA              COPY public.acta  FROM stdin;
    public          postgres    false    214   �<       >          0    16453    actainstalacion 
   TABLE DATA           �   COPY public.actainstalacion (ncodacta, dhorainicio, bestadomaterial, nnumcedulas, sobservaciones, bfirma1, bfirma2, bfirma3, nnromesa) FROM stdin;
    public          postgres    false    215   �<       ?          0    16460    actasufragio 
   TABLE DATA           �   COPY public.actasufragio (ncodacta, scodbarra, dhorainiescrutinio, sobservaciones, dhorafinescrutinio, bfirma1, bfirma2, bfirma3, nnromesa) FROM stdin;
    public          postgres    false    216   =       @          0    16467    centrocomputo 
   TABLE DATA           H   COPY public.centrocomputo (ncodcentrocomputo, sdescripcion) FROM stdin;
    public          postgres    false    217   4=       A          0    16472    eleccionesxubigeo 
   TABLE DATA           _   COPY public.eleccionesxubigeo (ncodprocesoelectoral, ncodtipoeleccion, scodubigeo) FROM stdin;
    public          postgres    false    218   Q=       B          0    16477    localvotación 
   TABLE DATA           W   COPY public."localvotación" (ncodlv, sdeslv, scodubigeo, "sdirecciónlv") FROM stdin;
    public          postgres    false    219   n=       C          0    16484    mesaelectoral 
   TABLE DATA           E   COPY public.mesaelectoral (nnromesa, nelectores, ncodlv) FROM stdin;
    public          postgres    false    220   �=       D          0    16489    odpe 
   TABLE DATA           2   COPY public.odpe (ncododpe, sdesodpe) FROM stdin;
    public          postgres    false    221   �=       E          0    16494    procesoelectoral 
   TABLE DATA           X   COPY public.procesoelectoral (ncodprocesoelectoral, ssiglas, dfechajornada) FROM stdin;
    public          postgres    false    222   �=       F          0    16499    tipoeleccion 
   TABLE DATA           J   COPY public.tipoeleccion (ncodtipoeleccion, sdestipoeleccion) FROM stdin;
    public          postgres    false    223   �=       G          0    16504    tramasrecibidas 
   TABLE DATA           Q   COPY public.tramasrecibidas (ncodtrama, strama, dfechahora, nestado) FROM stdin;
    public          postgres    false    224   �=       H          0    16511    ubigeo 
   TABLE DATA           x   COPY public.ubigeo (scodubigeo, scoddepartamento, scodprovincia, scoddistrito, ncododpe, ncodcentrocomputo) FROM stdin;
    public          postgres    false    225   >       �           2606    16459     actainstalacion actaelectoral_pk 
   CONSTRAINT     d   ALTER TABLE ONLY public.actainstalacion
    ADD CONSTRAINT actaelectoral_pk PRIMARY KEY (ncodacta);
 J   ALTER TABLE ONLY public.actainstalacion DROP CONSTRAINT actaelectoral_pk;
       public            postgres    false    215            �           2606    16466    actasufragio actasufragio_pk 
   CONSTRAINT     `   ALTER TABLE ONLY public.actasufragio
    ADD CONSTRAINT actasufragio_pk PRIMARY KEY (ncodacta);
 F   ALTER TABLE ONLY public.actasufragio DROP CONSTRAINT actasufragio_pk;
       public            postgres    false    216            �           2606    16471    centrocomputo centrocomputo_pk 
   CONSTRAINT     k   ALTER TABLE ONLY public.centrocomputo
    ADD CONSTRAINT centrocomputo_pk PRIMARY KEY (ncodcentrocomputo);
 H   ALTER TABLE ONLY public.centrocomputo DROP CONSTRAINT centrocomputo_pk;
       public            postgres    false    217            �           2606    16476 '   eleccionesxubigeo eleccionesxproceso_pk 
   CONSTRAINT     w   ALTER TABLE ONLY public.eleccionesxubigeo
    ADD CONSTRAINT eleccionesxproceso_pk PRIMARY KEY (ncodprocesoelectoral);
 Q   ALTER TABLE ONLY public.eleccionesxubigeo DROP CONSTRAINT eleccionesxproceso_pk;
       public            postgres    false    218            �           2606    16483     localvotación localvotación_pk 
   CONSTRAINT     f   ALTER TABLE ONLY public."localvotación"
    ADD CONSTRAINT "localvotación_pk" PRIMARY KEY (ncodlv);
 N   ALTER TABLE ONLY public."localvotación" DROP CONSTRAINT "localvotación_pk";
       public            postgres    false    219            �           2606    16488    mesaelectoral mesaelectoral_pk 
   CONSTRAINT     b   ALTER TABLE ONLY public.mesaelectoral
    ADD CONSTRAINT mesaelectoral_pk PRIMARY KEY (nnromesa);
 H   ALTER TABLE ONLY public.mesaelectoral DROP CONSTRAINT mesaelectoral_pk;
       public            postgres    false    220            �           2606    16493    odpe odpe_pk 
   CONSTRAINT     P   ALTER TABLE ONLY public.odpe
    ADD CONSTRAINT odpe_pk PRIMARY KEY (ncododpe);
 6   ALTER TABLE ONLY public.odpe DROP CONSTRAINT odpe_pk;
       public            postgres    false    221            �           2606    16498 $   procesoelectoral procesoelectoral_pk 
   CONSTRAINT     t   ALTER TABLE ONLY public.procesoelectoral
    ADD CONSTRAINT procesoelectoral_pk PRIMARY KEY (ncodprocesoelectoral);
 N   ALTER TABLE ONLY public.procesoelectoral DROP CONSTRAINT procesoelectoral_pk;
       public            postgres    false    222            �           2606    16503    tipoeleccion tipoeleccion_pk 
   CONSTRAINT     h   ALTER TABLE ONLY public.tipoeleccion
    ADD CONSTRAINT tipoeleccion_pk PRIMARY KEY (ncodtipoeleccion);
 F   ALTER TABLE ONLY public.tipoeleccion DROP CONSTRAINT tipoeleccion_pk;
       public            postgres    false    223            �           2606    16510 "   tramasrecibidas tramasrecibidas_pk 
   CONSTRAINT     g   ALTER TABLE ONLY public.tramasrecibidas
    ADD CONSTRAINT tramasrecibidas_pk PRIMARY KEY (ncodtrama);
 L   ALTER TABLE ONLY public.tramasrecibidas DROP CONSTRAINT tramasrecibidas_pk;
       public            postgres    false    224            �           2606    16515    ubigeo ubigeo_pk 
   CONSTRAINT     V   ALTER TABLE ONLY public.ubigeo
    ADD CONSTRAINT ubigeo_pk PRIMARY KEY (scodubigeo);
 :   ALTER TABLE ONLY public.ubigeo DROP CONSTRAINT ubigeo_pk;
       public            postgres    false    225            �           2606    16516 (   actainstalacion actainstalación_mesa_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.actainstalacion
    ADD CONSTRAINT "actainstalación_mesa_fk" FOREIGN KEY (nnromesa) REFERENCES public.mesaelectoral(nnromesa);
 T   ALTER TABLE ONLY public.actainstalacion DROP CONSTRAINT "actainstalación_mesa_fk";
       public          postgres    false    215    3227    220            �           2606    16521 *   actasufragio actasufragio_mesaelectoral_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.actasufragio
    ADD CONSTRAINT actasufragio_mesaelectoral_fk FOREIGN KEY (nnromesa) REFERENCES public.mesaelectoral(nnromesa);
 T   ALTER TABLE ONLY public.actasufragio DROP CONSTRAINT actasufragio_mesaelectoral_fk;
       public          postgres    false    216    3227    220            �           2606    16526 /   eleccionesxubigeo eleccionesxproceso_proceso_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.eleccionesxubigeo
    ADD CONSTRAINT eleccionesxproceso_proceso_fk FOREIGN KEY (ncodprocesoelectoral) REFERENCES public.procesoelectoral(ncodprocesoelectoral);
 Y   ALTER TABLE ONLY public.eleccionesxubigeo DROP CONSTRAINT eleccionesxproceso_proceso_fk;
       public          postgres    false    218    222    3231            �           2606    16531 ,   eleccionesxubigeo eleccionesxproceso_tipo_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.eleccionesxubigeo
    ADD CONSTRAINT eleccionesxproceso_tipo_fk FOREIGN KEY (ncodtipoeleccion) REFERENCES public.tipoeleccion(ncodtipoeleccion);
 V   ALTER TABLE ONLY public.eleccionesxubigeo DROP CONSTRAINT eleccionesxproceso_tipo_fk;
       public          postgres    false    223    218    3233            �           2606    16536 -   eleccionesxubigeo eleccionesxubigeo_ubigeo_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.eleccionesxubigeo
    ADD CONSTRAINT eleccionesxubigeo_ubigeo_fk FOREIGN KEY (scodubigeo) REFERENCES public.ubigeo(scodubigeo);
 W   ALTER TABLE ONLY public.eleccionesxubigeo DROP CONSTRAINT eleccionesxubigeo_ubigeo_fk;
       public          postgres    false    218    3237    225            �           2606    16541 '   localvotación localvotación_ubigeo_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public."localvotación"
    ADD CONSTRAINT "localvotación_ubigeo_fk" FOREIGN KEY (scodubigeo) REFERENCES public.ubigeo(scodubigeo);
 U   ALTER TABLE ONLY public."localvotación" DROP CONSTRAINT "localvotación_ubigeo_fk";
       public          postgres    false    225    219    3237            �           2606    16546 -   mesaelectoral mesaelectoral_localvotación_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.mesaelectoral
    ADD CONSTRAINT "mesaelectoral_localvotación_fk" FOREIGN KEY (ncodlv) REFERENCES public."localvotación"(ncodlv);
 Y   ALTER TABLE ONLY public.mesaelectoral DROP CONSTRAINT "mesaelectoral_localvotación_fk";
       public          postgres    false    220    3225    219            �           2606    16551    ubigeo ubigeo_centrocomputo_fk    FK CONSTRAINT     �   ALTER TABLE ONLY public.ubigeo
    ADD CONSTRAINT ubigeo_centrocomputo_fk FOREIGN KEY (ncodcentrocomputo) REFERENCES public.centrocomputo(ncodcentrocomputo);
 H   ALTER TABLE ONLY public.ubigeo DROP CONSTRAINT ubigeo_centrocomputo_fk;
       public          postgres    false    217    225    3221            �           2606    16556    ubigeo ubigeo_odpe_fk    FK CONSTRAINT     z   ALTER TABLE ONLY public.ubigeo
    ADD CONSTRAINT ubigeo_odpe_fk FOREIGN KEY (ncododpe) REFERENCES public.odpe(ncododpe);
 ?   ALTER TABLE ONLY public.ubigeo DROP CONSTRAINT ubigeo_odpe_fk;
       public          postgres    false    225    3229    221            =      x������ � �      >      x������ � �      ?      x������ � �      @      x������ � �      A      x������ � �      B      x������ � �      C      x������ � �      D      x������ � �      E      x������ � �      F      x������ � �      G      x������ � �      H      x������ � �     