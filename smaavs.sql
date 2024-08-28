
    create table alerta (
       id bigint not null,
        fecha_y_hora datetime,
        tipo_alerta varchar(255),
        heladera_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table apertura (
       id bigint not null,
        fecha_y_hora datetime,
        tarjeta_colaborador_id varchar(255),
        heladera_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table area_de_cobertura (
       id bigint not null,
        primary key (id)
    ) engine=MyISAM;

    create table barrio_por_area (
       id bigint not null,
        barrio_id bigint,
        area_cobertura_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table barrrio (
       id bigint not null,
        codigo_postal INT,
        nombre VARCHAR(255),
        ciudad_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table ciudad (
       id bigint not null,
        nombre VARCHAR(255),
        partio_o_departamento_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table colaborador (
       id bigint not null,
        apellido VARCHAR(255),
        nombre VARCHAR(255),
        puntos_canjeados FLOAT(10,2),
        puntos_disponibles FLOAT(10,2),
        tipo_colaborador varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table colaborador_forma_de_colaborar (
       colaborador_id bigint not null,
        forma_de_colaborar integer
    ) engine=MyISAM;

    create table documento (
       id bigint not null,
        numero varchar(20),
        tipo_documento varchar(10),
        primary key (id)
    ) engine=MyISAM;

    create table falla_tecnica (
       id bigint not null,
        descripcion TEXT,
        fecha_y_hora datetime,
        foto VARCHAR(255),
        heladera_id bigint,
        colaborador_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table formulario (
       id bigint not null,
        primary key (id)
    ) engine=MyISAM;

    create table formulario_respondido (
       id bigint not null,
        formulario_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table heladera (
       id bigint not null,
        capacidad int,
        direccion TEXT,
        estado varchar(255),
        fecha_y_hora_inicio datetime,
        stock int,
        tiempo_para_visitar_en_minutos int,
        modelo_heladera_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table hibernate_sequence (
       next_val bigint
    ) engine=MyISAM;

    insert into hibernate_sequence values ( 1 );

    create table medicion (
       id bigint not null,
        fecha_y_hora datetime,
        temperatura FLOAT(5,2),
        receptor_sensor_temperatura_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table medio_de_contacto (
       id bigint not null,
        tipo varchar(8),
        valor varchar(255),
        colaborador_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table ModeloHeladera (
       id bigint not null,
        nombre VARCHAR(255),
        temperatura_maxima FLOAT(5,2),
        temperatura_minima FLOAT(5,2),
        primary key (id)
    ) engine=MyISAM;

    create table oferta (
       id bigint not null,
        nombre VARCHAR(255),
        puntaje_minimo FLOAT(10,2),
        rubro_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table ofrecer_producto (
       id bigint not null,
        colaborador_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table partido_o_departamento (
       id bigint not null,
        nombre VARCHAR(255),
        provincia_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table persona_vulnerable (
       id bigint not null,
        domicilio TEXT,
        fecha_de_nacimiento datetime,
        fecha_de_registro datetime,
        nombre VARCHAR(255),
        persona_vulnerable_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table pregunta (
       id bigint not null,
        pregunta varchar(255),
        primary key (id)
    ) engine=MyISAM;

    create table pregunta_formulario (
       id bigint not null,
        pregunta_id bigint,
        formulario_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table provincia_o_estado (
       id bigint not null,
        nombre VARCHAR(255),
        primary key (id)
    ) engine=MyISAM;

    create table punto (
       id bigint not null,
        latitud FLOAT(9,6),
        longitud FLOAT(9,6),
        primary key (id)
    ) engine=MyISAM;

    create table puntuable (
       tipo_puntuable varchar(31) not null,
        id bigint not null,
        cantidad_de_viandas int,
        fecha datetime,
        motivo_distribucion varchar(255),
        multiplicador float,
        fecha_de_inicio datetime,
        frecuencia varchar(255),
        monto int,
        colaborador_id bigint,
        heladera_destino_id bigint,
        heladera_origen_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table receptor_sensor_temperatura (
       id bigint not null,
        primary key (id)
    ) engine=MyISAM;

    create table registro (
       id bigint not null,
        fecha_de_registro datetime,
        primary key (id)
    ) engine=MyISAM;

    create table respuesta (
       id bigint not null,
        respuesta varchar(255),
        pregunta_id bigint,
        formulario_respondido_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table rubro (
       id bigint not null,
        nombre VARCHAR(255),
        primary key (id)
    ) engine=MyISAM;

    create table solicitud_apertura (
       id bigint not null,
        fecha_y_hora datetime,
        tarjeta_colaborador_id varchar(255),
        heladera_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table suscripcion (
       id bigint not null,
        configurableN int,
        colaborador_id bigint,
        topic_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table tarjeta_colaborador (
       id varchar(255) not null,
        fecha_emision datetime,
        primary key (id)
    ) engine=MyISAM;

    create table tarjeta_persona_vulnerable (
       codigo VARCHAR(12) not null,
        usos_maximo_base INT,
        usos_maximo_por_menor INT,
        primary key (codigo)
    ) engine=MyISAM;

    create table tecnico (
       id bigint not null,
        apellido varchar(255),
        cuil varchar(255),
        nombre varchar(255),
        area_de_cobertura_id bigint,
        documento_id bigint,
        medio_de_contacto_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table topic (
       id bigint not null,
        condicion_suscripcion_heladera varchar(255),
        mensaje TEXT,
        heladera_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table uso (
       id bigint not null,
        fecha_de_uso datetime,
        heladera_id bigint,
        tarjeta_persona_vulnerable_id VARCHAR(12),
        primary key (id)
    ) engine=MyISAM;

    create table vianda (
       id bigint not null,
        calorias int,
        comida VARCHAR(50),
        entregada smallint,
        fecha_caducidad datetime,
        peso int,
        colaborador_id bigint,
        heladera_id bigint,
        vianda_id bigint,
        primary key (id)
    ) engine=MyISAM;

    create table visita_tecnica (
       id bigint not null,
        descripcion text,
        fecha_visita datetime,
        foto text,
        fue_solucionado smallint,
        tecnico_id bigint,
        heladera_id bigint,
        primary key (id)
    ) engine=MyISAM;

    alter table alerta 
       add constraint FK19y77ub5hwtlh9g2e364kww3d 
       foreign key (heladera_id) 
       references heladera (id);

    alter table apertura 
       add constraint FKq6sowvegixkvminx98t5b44aw 
       foreign key (tarjeta_colaborador_id) 
       references tarjeta_colaborador (id);

    alter table apertura 
       add constraint FKsukc4h3qt55ta77xynd18h294 
       foreign key (heladera_id) 
       references heladera (id);

    alter table barrio_por_area 
       add constraint FK3wagu4jeceq04oj0mucs95mhy 
       foreign key (barrio_id) 
       references barrrio (id);

    alter table barrio_por_area 
       add constraint FKf9dhlvgdtv7tdy5jitrhtasyn 
       foreign key (area_cobertura_id) 
       references area_de_cobertura (id);

    alter table barrrio 
       add constraint FKg2dylin4iohtmfoexwf0ltw13 
       foreign key (ciudad_id) 
       references ciudad (id);

    alter table ciudad 
       add constraint FKl3ahh0x0gign2ddedeg3ef40b 
       foreign key (partio_o_departamento_id) 
       references partido_o_departamento (id);

    alter table colaborador_forma_de_colaborar 
       add constraint FKnlnk3uh3playh9qwndfe3ogy1 
       foreign key (colaborador_id) 
       references colaborador (id);

    alter table falla_tecnica 
       add constraint FK87egwx5vtgfbkv3vl9lh85e9x 
       foreign key (heladera_id) 
       references heladera (id);

    alter table falla_tecnica 
       add constraint FKdf6q68y8k83podi6rsw0ud787 
       foreign key (colaborador_id) 
       references colaborador (id);

    alter table formulario_respondido 
       add constraint FK6c14quy9qarkbsqkkfnaro5n8 
       foreign key (formulario_id) 
       references formulario (id);

    alter table heladera 
       add constraint FK95kopsprf1tl48ccu936w3axm 
       foreign key (modelo_heladera_id) 
       references ModeloHeladera (id);

    alter table medicion 
       add constraint FKj2ajro528e430iuw2bm9uqnt2 
       foreign key (receptor_sensor_temperatura_id) 
       references receptor_sensor_temperatura (id);

    alter table medio_de_contacto 
       add constraint FKmgr27flakm2emyg0evb4hq45i 
       foreign key (colaborador_id) 
       references colaborador (id);

    alter table oferta 
       add constraint FK1kmsh1pyxql9xnhs4ky0inlr9 
       foreign key (rubro_id) 
       references rubro (id);

    alter table ofrecer_producto 
       add constraint FKf7y90ad74w4njfuaaykwgto7 
       foreign key (colaborador_id) 
       references colaborador (id);

    alter table partido_o_departamento 
       add constraint FKjtug9mtgayjksvyb0u0yvd5ty 
       foreign key (provincia_id) 
       references provincia_o_estado (id);

    alter table persona_vulnerable 
       add constraint FK5fgs8kep569nm7lho0q9sno52 
       foreign key (persona_vulnerable_id) 
       references persona_vulnerable (id);

    alter table pregunta_formulario 
       add constraint FK2aknko49y8fl9pmpi0t0akhyx 
       foreign key (pregunta_id) 
       references pregunta (id);

    alter table pregunta_formulario 
       add constraint FKjhh2dv9qrjmapxwddlbu3lmbn 
       foreign key (formulario_id) 
       references formulario (id);

    alter table puntuable 
       add constraint FK7dx4b955os716dsqdv1bb835b 
       foreign key (colaborador_id) 
       references colaborador (id);

    alter table puntuable 
       add constraint FKf62krnfxqyo297kmwrcshf0pm 
       foreign key (heladera_destino_id) 
       references heladera (id);

    alter table puntuable 
       add constraint FKsq37ctn1n86ouo5tl70o6cko0 
       foreign key (heladera_origen_id) 
       references heladera (id);

    alter table respuesta 
       add constraint FKd9oyrwyjw1otr38btjeevanif 
       foreign key (pregunta_id) 
       references pregunta (id);

    alter table respuesta 
       add constraint FKoc0d476ofukpkq9yicpmcnh58 
       foreign key (formulario_respondido_id) 
       references formulario_respondido (id);

    alter table solicitud_apertura 
       add constraint FKm3cdlkfaa6mjli4upe07mso23 
       foreign key (tarjeta_colaborador_id) 
       references tarjeta_colaborador (id);

    alter table solicitud_apertura 
       add constraint FK2efmn8os1fio421d4uej31pck 
       foreign key (heladera_id) 
       references heladera (id);

    alter table suscripcion 
       add constraint FK10g3xpputf4vx9y9wpj274ra8 
       foreign key (colaborador_id) 
       references colaborador (id);

    alter table suscripcion 
       add constraint FKngf3eiu8drg6xrpjm29e0sv6k 
       foreign key (topic_id) 
       references topic (id);

    alter table tecnico 
       add constraint FKev95kmgahga2jnslks4g5mr7r 
       foreign key (area_de_cobertura_id) 
       references area_de_cobertura (id);

    alter table tecnico 
       add constraint FKl26jdgdqv6ckueymj9x1u9bty 
       foreign key (documento_id) 
       references documento (id);

    alter table tecnico 
       add constraint FKpt4a9meo4fsbrdm1srk07w688 
       foreign key (medio_de_contacto_id) 
       references medio_de_contacto (id);

    alter table topic 
       add constraint FK2sjj9oqgycbygq867p8vek1st 
       foreign key (heladera_id) 
       references heladera (id);

    alter table uso 
       add constraint FKrgtxqqiu10oaimtalissyomq8 
       foreign key (heladera_id) 
       references heladera (id);

    alter table uso 
       add constraint FK85mlww9xcbh7og9bwj7j4baap 
       foreign key (tarjeta_persona_vulnerable_id) 
       references tarjeta_persona_vulnerable (codigo);

    alter table vianda 
       add constraint FK4ohq0d2qrvlrjhsshk3vw00k1 
       foreign key (colaborador_id) 
       references colaborador (id);

    alter table vianda 
       add constraint FK32818o0yemdyhuc87ph6brd5q 
       foreign key (heladera_id) 
       references heladera (id);

    alter table vianda 
       add constraint FKrcfwju443cba4y7ay26igb2wx 
       foreign key (vianda_id) 
       references puntuable (id);

    alter table visita_tecnica 
       add constraint FK9qlgbrjnvk2no5a1wqo80wm9u 
       foreign key (tecnico_id) 
       references tecnico (id);

    alter table visita_tecnica 
       add constraint FKro093h86d1ovfakupu6pgi8d5 
       foreign key (heladera_id) 
       references heladera (id);
