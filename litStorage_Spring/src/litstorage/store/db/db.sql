
CREATE TABLE Board
(
	id                   varchar2(20) NOT NULL ,
	name                 varchar2(30) NULL 
);

CREATE UNIQUE INDEX XPKBoard ON Board
(id   ASC);

ALTER TABLE Board
	ADD CONSTRAINT  XPKBoard PRIMARY KEY (id);

CREATE TABLE Post
(
	id                   varchar2(30) NOT NULL ,
	name                 varchar2(30) NULL ,
	content              varchar2(2000) NULL ,
	hashtag              varchar2(100) NULL ,
	writer               varchar2(30) NULL ,
	boardId              varchar2(30) NULL 
);

CREATE UNIQUE INDEX XPKPost ON Post
(id   ASC);

ALTER TABLE Post
	ADD CONSTRAINT  XPKPost PRIMARY KEY (id);

CREATE TABLE Litstorage
(
	id                   varchar2(30) NOT NULL ,
	introduce            varchar2(300) NULL ,
	name                 varchar2(50) NULL ,
	writer               varchar2(30) NULL 
);

CREATE UNIQUE INDEX XPKLitstorage ON Litstorage
(id   ASC);

ALTER TABLE Litstorage
	ADD CONSTRAINT  XPKLitstorage PRIMARY KEY (id);

CREATE TABLE DiscussionPlace
(
	id                   varchar2(30) NOT NULL ,
	name                 varchar2(50) NULL ,
	writer               varchar2(30) NULL ,
	LitStorageId         varchar2(30) NULL 
);

CREATE UNIQUE INDEX XPKDiscussionPlace ON DiscussionPlace
(id   ASC);

ALTER TABLE DiscussionPlace
	ADD CONSTRAINT  XPKDiscussionPlace PRIMARY KEY (id);

CREATE TABLE DiscussionContent
(
	id                   varchar2(30) NOT NULL ,
	writer               varchar2(30) NULL ,
	content              varchar2(300) NULL ,
	DiscussionPlaceId    varchar2(30) NULL 
);

CREATE UNIQUE INDEX XPKDiscussionContent ON DiscussionContent
(id   ASC);

ALTER TABLE DiscussionContent
	ADD CONSTRAINT  XPKDiscussionContent PRIMARY KEY (id);

CREATE TABLE Literature
(
	id                   varchar2(30) NOT NULL ,
	name                 varchar2(50) NULL ,
	genre                varchar2(30) NULL ,
	imagePath            varchar2(200) NULL ,
	introduce            varchar2(500) NULL ,
	writer               varchar2(30) NULL ,
	hits                 NUMBER NULL ,
	LitstorageId         varchar2(30) NULL 
);

CREATE UNIQUE INDEX XPKLiterature ON Literature
(id   ASC);

ALTER TABLE Literature
	ADD CONSTRAINT  XPKLiterature PRIMARY KEY (id);

CREATE TABLE Episode
(
	id                   varchar2(30) NOT NULL ,
	name                 varchar2(50) NULL ,
	content              varchar2(200) NULL ,
	writer               varchar2(30) NULL ,
	bound                CHAR(10) NULL ,
	literatureId         varchar2(30) NULL 
);

CREATE UNIQUE INDEX XPKEpisode ON Episode
(id   ASC);

ALTER TABLE Episode
	ADD CONSTRAINT  XPKEpisode PRIMARY KEY (id);

CREATE TABLE ChangeHistory
(
	id                   varchar2(30) NOT NULL ,
	editor               varchar2(30) NULL ,
	content              varchar2(200) NULL ,
	changeTIme           DATE NULL ,
	message              varchar2(200) NULL ,
	episodeId            varchar2(30) NULL 
);

CREATE UNIQUE INDEX XPKChangeHistory ON ChangeHistory
(id   ASC);

ALTER TABLE ChangeHistory
	ADD CONSTRAINT  XPKChangeHistory PRIMARY KEY (id);

CREATE TABLE InviteRequest
(
	sender               varchar2(30) NOT NULL ,
	receiver             varchar2(30) NOT NULL ,
	sendTime             DATE NULL ,
	form                 varchar2(5) NULL ,
	message              varchar2(300) NULL 
);

CREATE UNIQUE INDEX XPKInviteRequest ON InviteRequest
(sender   ASC,receiver   ASC);

ALTER TABLE InviteRequest
	ADD CONSTRAINT  XPKInviteRequest PRIMARY KEY (sender,receiver);

CREATE TABLE Member
(
	id                   varchar2(30) NOT NULL ,
	name                 varchar2(30) NULL ,
	password             varchar2(30) NULL ,
	email                varchar2(50) NULL ,
	isOut                varchar2(5) NULL 
);

CREATE UNIQUE INDEX XPKMember ON Member
(id   ASC);

ALTER TABLE Member
	ADD CONSTRAINT  XPKMember PRIMARY KEY (id);

CREATE TABLE MemberLitStorage
(
	memberId             varchar2(30) NULL ,
	LitstorageID         varchar2(30) NULL ,
	joinTime             DATE NULL 
);

ALTER TABLE Post
	ADD (CONSTRAINT R_3 FOREIGN KEY (boardId) REFERENCES Board (id) ON DELETE SET NULL);

ALTER TABLE DiscussionPlace
	ADD (CONSTRAINT R_6 FOREIGN KEY (LitStorageId) REFERENCES Litstorage (id) ON DELETE SET NULL);

ALTER TABLE DiscussionContent
	ADD (CONSTRAINT R_7 FOREIGN KEY (DiscussionPlaceId) REFERENCES DiscussionPlace (id) ON DELETE SET NULL);

ALTER TABLE Literature
	ADD (CONSTRAINT R_5 FOREIGN KEY (LitstorageId) REFERENCES Litstorage (id) ON DELETE SET NULL);

ALTER TABLE Episode
	ADD (CONSTRAINT R_8 FOREIGN KEY (literatureId) REFERENCES Literature (id) ON DELETE SET NULL);

ALTER TABLE ChangeHistory
	ADD (CONSTRAINT R_10 FOREIGN KEY (episodeId) REFERENCES Episode (id) ON DELETE SET NULL);

ALTER TABLE MemberLitStorage
	ADD (CONSTRAINT R_11 FOREIGN KEY (memberId) REFERENCES Member (id) ON DELETE SET NULL);

ALTER TABLE MemberLitStorage
	ADD (CONSTRAINT R_12 FOREIGN KEY (LitstorageID) REFERENCES Litstorage (id) ON DELETE SET NULL);

CREATE  TRIGGER  tD_Board AFTER DELETE ON Board for each row
-- erwin Builtin Trigger
-- DELETE trigger on Board 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* Board  Post on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="0000a052", PARENT_OWNER="", PARENT_TABLE="Board"
    CHILD_OWNER="", CHILD_TABLE="Post"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_3", FK_COLUMNS="boardId" */
    UPDATE Post
      SET
        /* %SetFK(Post,NULL) */
        Post.boardId = NULL
      WHERE
        /* %JoinFKPK(Post,:%Old," = "," AND") */
        Post.boardId = :old.id;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_Board AFTER UPDATE ON Board for each row
-- erwin Builtin Trigger
-- UPDATE trigger on Board 
DECLARE NUMROWS INTEGER;
BEGIN
  /* Board  Post on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="0000bedc", PARENT_OWNER="", PARENT_TABLE="Board"
    CHILD_OWNER="", CHILD_TABLE="Post"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_3", FK_COLUMNS="boardId" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.id <> :new.id
  THEN
    UPDATE Post
      SET
        /* %SetFK(Post,NULL) */
        Post.boardId = NULL
      WHERE
        /* %JoinFKPK(Post,:%Old," = ",",") */
        Post.boardId = :old.id;
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER tI_Post BEFORE INSERT ON Post for each row
-- erwin Builtin Trigger
-- INSERT trigger on Post 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* Board  Post on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="0000d161", PARENT_OWNER="", PARENT_TABLE="Board"
    CHILD_OWNER="", CHILD_TABLE="Post"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_3", FK_COLUMNS="boardId" */
    UPDATE Post
      SET
        /* %SetFK(Post,NULL) */
        Post.boardId = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM Board
            WHERE
              /* %JoinFKPK(:%New,Board," = "," AND") */
              :new.boardId = Board.id
        ) 
        /* %JoinPKPK(Post,:%New," = "," AND") */
         and Post.id = :new.id;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_Post AFTER UPDATE ON Post for each row
-- erwin Builtin Trigger
-- UPDATE trigger on Post 
DECLARE NUMROWS INTEGER;
BEGIN
  /* erwin Builtin Trigger */
  /* Board  Post on child update no action */
  /* ERWIN_RELATION:CHECKSUM="0000e4b1", PARENT_OWNER="", PARENT_TABLE="Board"
    CHILD_OWNER="", CHILD_TABLE="Post"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_3", FK_COLUMNS="boardId" */
  SELECT count(*) INTO NUMROWS
    FROM Board
    WHERE
      /* %JoinFKPK(:%New,Board," = "," AND") */
      :new.boardId = Board.id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.boardId IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Post because Board does not exist.'
    );
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER  tD_Litstorage AFTER DELETE ON Litstorage for each row
-- erwin Builtin Trigger
-- DELETE trigger on Litstorage 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* Litstorage  MemberLitStorage on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00028d40", PARENT_OWNER="", PARENT_TABLE="Litstorage"
    CHILD_OWNER="", CHILD_TABLE="MemberLitStorage"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_12", FK_COLUMNS="LitstorageID" */
    UPDATE MemberLitStorage
      SET
        /* %SetFK(MemberLitStorage,NULL) */
        MemberLitStorage.LitstorageID = NULL
      WHERE
        /* %JoinFKPK(MemberLitStorage,:%Old," = "," AND") */
        MemberLitStorage.LitstorageID = :old.id;

    /* erwin Builtin Trigger */
    /* Litstorage  DiscussionPlace on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Litstorage"
    CHILD_OWNER="", CHILD_TABLE="DiscussionPlace"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_6", FK_COLUMNS="LitStorageId" */
    UPDATE DiscussionPlace
      SET
        /* %SetFK(DiscussionPlace,NULL) */
        DiscussionPlace.LitStorageId = NULL
      WHERE
        /* %JoinFKPK(DiscussionPlace,:%Old," = "," AND") */
        DiscussionPlace.LitStorageId = :old.id;

    /* erwin Builtin Trigger */
    /* Litstorage  Literature on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Litstorage"
    CHILD_OWNER="", CHILD_TABLE="Literature"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_5", FK_COLUMNS="LitstorageId" */
    UPDATE Literature
      SET
        /* %SetFK(Literature,NULL) */
        Literature.LitstorageId = NULL
      WHERE
        /* %JoinFKPK(Literature,:%Old," = "," AND") */
        Literature.LitstorageId = :old.id;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_Litstorage AFTER UPDATE ON Litstorage for each row
-- erwin Builtin Trigger
-- UPDATE trigger on Litstorage 
DECLARE NUMROWS INTEGER;
BEGIN
  /* Litstorage  MemberLitStorage on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="0002ca0a", PARENT_OWNER="", PARENT_TABLE="Litstorage"
    CHILD_OWNER="", CHILD_TABLE="MemberLitStorage"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_12", FK_COLUMNS="LitstorageID" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.id <> :new.id
  THEN
    UPDATE MemberLitStorage
      SET
        /* %SetFK(MemberLitStorage,NULL) */
        MemberLitStorage.LitstorageID = NULL
      WHERE
        /* %JoinFKPK(MemberLitStorage,:%Old," = ",",") */
        MemberLitStorage.LitstorageID = :old.id;
  END IF;

  /* Litstorage  DiscussionPlace on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Litstorage"
    CHILD_OWNER="", CHILD_TABLE="DiscussionPlace"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_6", FK_COLUMNS="LitStorageId" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.id <> :new.id
  THEN
    UPDATE DiscussionPlace
      SET
        /* %SetFK(DiscussionPlace,NULL) */
        DiscussionPlace.LitStorageId = NULL
      WHERE
        /* %JoinFKPK(DiscussionPlace,:%Old," = ",",") */
        DiscussionPlace.LitStorageId = :old.id;
  END IF;

  /* Litstorage  Literature on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Litstorage"
    CHILD_OWNER="", CHILD_TABLE="Literature"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_5", FK_COLUMNS="LitstorageId" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.id <> :new.id
  THEN
    UPDATE Literature
      SET
        /* %SetFK(Literature,NULL) */
        Literature.LitstorageId = NULL
      WHERE
        /* %JoinFKPK(Literature,:%Old," = ",",") */
        Literature.LitstorageId = :old.id;
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER  tD_DiscussionPlace AFTER DELETE ON DiscussionPlace for each row
-- erwin Builtin Trigger
-- DELETE trigger on DiscussionPlace 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* DiscussionPlace  DiscussionContent on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="0000d4c2", PARENT_OWNER="", PARENT_TABLE="DiscussionPlace"
    CHILD_OWNER="", CHILD_TABLE="DiscussionContent"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_7", FK_COLUMNS="DiscussionPlaceId" */
    UPDATE DiscussionContent
      SET
        /* %SetFK(DiscussionContent,NULL) */
        DiscussionContent.DiscussionPlaceId = NULL
      WHERE
        /* %JoinFKPK(DiscussionContent,:%Old," = "," AND") */
        DiscussionContent.DiscussionPlaceId = :old.id;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tI_DiscussionPlace BEFORE INSERT ON DiscussionPlace for each row
-- erwin Builtin Trigger
-- INSERT trigger on DiscussionPlace 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* Litstorage  DiscussionPlace on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="000109ae", PARENT_OWNER="", PARENT_TABLE="Litstorage"
    CHILD_OWNER="", CHILD_TABLE="DiscussionPlace"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_6", FK_COLUMNS="LitStorageId" */
    UPDATE DiscussionPlace
      SET
        /* %SetFK(DiscussionPlace,NULL) */
        DiscussionPlace.LitStorageId = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM Litstorage
            WHERE
              /* %JoinFKPK(:%New,Litstorage," = "," AND") */
              :new.LitStorageId = Litstorage.id
        ) 
        /* %JoinPKPK(DiscussionPlace,:%New," = "," AND") */
         and DiscussionPlace.id = :new.id;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_DiscussionPlace AFTER UPDATE ON DiscussionPlace for each row
-- erwin Builtin Trigger
-- UPDATE trigger on DiscussionPlace 
DECLARE NUMROWS INTEGER;
BEGIN
  /* DiscussionPlace  DiscussionContent on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="00020bcc", PARENT_OWNER="", PARENT_TABLE="DiscussionPlace"
    CHILD_OWNER="", CHILD_TABLE="DiscussionContent"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_7", FK_COLUMNS="DiscussionPlaceId" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.id <> :new.id
  THEN
    UPDATE DiscussionContent
      SET
        /* %SetFK(DiscussionContent,NULL) */
        DiscussionContent.DiscussionPlaceId = NULL
      WHERE
        /* %JoinFKPK(DiscussionContent,:%Old," = ",",") */
        DiscussionContent.DiscussionPlaceId = :old.id;
  END IF;

  /* erwin Builtin Trigger */
  /* Litstorage  DiscussionPlace on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Litstorage"
    CHILD_OWNER="", CHILD_TABLE="DiscussionPlace"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_6", FK_COLUMNS="LitStorageId" */
  SELECT count(*) INTO NUMROWS
    FROM Litstorage
    WHERE
      /* %JoinFKPK(:%New,Litstorage," = "," AND") */
      :new.LitStorageId = Litstorage.id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.LitStorageId IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update DiscussionPlace because Litstorage does not exist.'
    );
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER tI_DiscussionContent BEFORE INSERT ON DiscussionContent for each row
-- erwin Builtin Trigger
-- INSERT trigger on DiscussionContent 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* DiscussionPlace  DiscussionContent on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="00010c1b", PARENT_OWNER="", PARENT_TABLE="DiscussionPlace"
    CHILD_OWNER="", CHILD_TABLE="DiscussionContent"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_7", FK_COLUMNS="DiscussionPlaceId" */
    UPDATE DiscussionContent
      SET
        /* %SetFK(DiscussionContent,NULL) */
        DiscussionContent.DiscussionPlaceId = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM DiscussionPlace
            WHERE
              /* %JoinFKPK(:%New,DiscussionPlace," = "," AND") */
              :new.DiscussionPlaceId = DiscussionPlace.id
        ) 
        /* %JoinPKPK(DiscussionContent,:%New," = "," AND") */
         and DiscussionContent.id = :new.id;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_DiscussionContent AFTER UPDATE ON DiscussionContent for each row
-- erwin Builtin Trigger
-- UPDATE trigger on DiscussionContent 
DECLARE NUMROWS INTEGER;
BEGIN
  /* erwin Builtin Trigger */
  /* DiscussionPlace  DiscussionContent on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00012bd6", PARENT_OWNER="", PARENT_TABLE="DiscussionPlace"
    CHILD_OWNER="", CHILD_TABLE="DiscussionContent"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_7", FK_COLUMNS="DiscussionPlaceId" */
  SELECT count(*) INTO NUMROWS
    FROM DiscussionPlace
    WHERE
      /* %JoinFKPK(:%New,DiscussionPlace," = "," AND") */
      :new.DiscussionPlaceId = DiscussionPlace.id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.DiscussionPlaceId IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update DiscussionContent because DiscussionPlace does not exist.'
    );
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER  tD_Literature AFTER DELETE ON Literature for each row
-- erwin Builtin Trigger
-- DELETE trigger on Literature 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* Literature  Episode on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="0000bb5a", PARENT_OWNER="", PARENT_TABLE="Literature"
    CHILD_OWNER="", CHILD_TABLE="Episode"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_8", FK_COLUMNS="literatureId" */
    UPDATE Episode
      SET
        /* %SetFK(Episode,NULL) */
        Episode.literatureId = NULL
      WHERE
        /* %JoinFKPK(Episode,:%Old," = "," AND") */
        Episode.literatureId = :old.id;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tI_Literature BEFORE INSERT ON Literature for each row
-- erwin Builtin Trigger
-- INSERT trigger on Literature 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* Litstorage  Literature on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="0000e216", PARENT_OWNER="", PARENT_TABLE="Litstorage"
    CHILD_OWNER="", CHILD_TABLE="Literature"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_5", FK_COLUMNS="LitstorageId" */
    UPDATE Literature
      SET
        /* %SetFK(Literature,NULL) */
        Literature.LitstorageId = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM Litstorage
            WHERE
              /* %JoinFKPK(:%New,Litstorage," = "," AND") */
              :new.LitstorageId = Litstorage.id
        ) 
        /* %JoinPKPK(Literature,:%New," = "," AND") */
         and Literature.id = :new.id;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_Literature AFTER UPDATE ON Literature for each row
-- erwin Builtin Trigger
-- UPDATE trigger on Literature 
DECLARE NUMROWS INTEGER;
BEGIN
  /* Literature  Episode on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="0001f538", PARENT_OWNER="", PARENT_TABLE="Literature"
    CHILD_OWNER="", CHILD_TABLE="Episode"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_8", FK_COLUMNS="literatureId" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.id <> :new.id
  THEN
    UPDATE Episode
      SET
        /* %SetFK(Episode,NULL) */
        Episode.literatureId = NULL
      WHERE
        /* %JoinFKPK(Episode,:%Old," = ",",") */
        Episode.literatureId = :old.id;
  END IF;

  /* erwin Builtin Trigger */
  /* Litstorage  Literature on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Litstorage"
    CHILD_OWNER="", CHILD_TABLE="Literature"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_5", FK_COLUMNS="LitstorageId" */
  SELECT count(*) INTO NUMROWS
    FROM Litstorage
    WHERE
      /* %JoinFKPK(:%New,Litstorage," = "," AND") */
      :new.LitstorageId = Litstorage.id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.LitstorageId IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Literature because Litstorage does not exist.'
    );
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER  tD_Episode AFTER DELETE ON Episode for each row
-- erwin Builtin Trigger
-- DELETE trigger on Episode 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* Episode  ChangeHistory on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="0000c34a", PARENT_OWNER="", PARENT_TABLE="Episode"
    CHILD_OWNER="", CHILD_TABLE="ChangeHistory"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_10", FK_COLUMNS="episodeId" */
    UPDATE ChangeHistory
      SET
        /* %SetFK(ChangeHistory,NULL) */
        ChangeHistory.episodeId = NULL
      WHERE
        /* %JoinFKPK(ChangeHistory,:%Old," = "," AND") */
        ChangeHistory.episodeId = :old.id;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tI_Episode BEFORE INSERT ON Episode for each row
-- erwin Builtin Trigger
-- INSERT trigger on Episode 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* Literature  Episode on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="0000e281", PARENT_OWNER="", PARENT_TABLE="Literature"
    CHILD_OWNER="", CHILD_TABLE="Episode"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_8", FK_COLUMNS="literatureId" */
    UPDATE Episode
      SET
        /* %SetFK(Episode,NULL) */
        Episode.literatureId = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM Literature
            WHERE
              /* %JoinFKPK(:%New,Literature," = "," AND") */
              :new.literatureId = Literature.id
        ) 
        /* %JoinPKPK(Episode,:%New," = "," AND") */
         and Episode.id = :new.id;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_Episode AFTER UPDATE ON Episode for each row
-- erwin Builtin Trigger
-- UPDATE trigger on Episode 
DECLARE NUMROWS INTEGER;
BEGIN
  /* Episode  ChangeHistory on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="0001f497", PARENT_OWNER="", PARENT_TABLE="Episode"
    CHILD_OWNER="", CHILD_TABLE="ChangeHistory"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_10", FK_COLUMNS="episodeId" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.id <> :new.id
  THEN
    UPDATE ChangeHistory
      SET
        /* %SetFK(ChangeHistory,NULL) */
        ChangeHistory.episodeId = NULL
      WHERE
        /* %JoinFKPK(ChangeHistory,:%Old," = ",",") */
        ChangeHistory.episodeId = :old.id;
  END IF;

  /* erwin Builtin Trigger */
  /* Literature  Episode on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Literature"
    CHILD_OWNER="", CHILD_TABLE="Episode"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_8", FK_COLUMNS="literatureId" */
  SELECT count(*) INTO NUMROWS
    FROM Literature
    WHERE
      /* %JoinFKPK(:%New,Literature," = "," AND") */
      :new.literatureId = Literature.id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.literatureId IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update Episode because Literature does not exist.'
    );
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER tI_ChangeHistory BEFORE INSERT ON ChangeHistory for each row
-- erwin Builtin Trigger
-- INSERT trigger on ChangeHistory 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* Episode  ChangeHistory on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="0000e7dd", PARENT_OWNER="", PARENT_TABLE="Episode"
    CHILD_OWNER="", CHILD_TABLE="ChangeHistory"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_10", FK_COLUMNS="episodeId" */
    UPDATE ChangeHistory
      SET
        /* %SetFK(ChangeHistory,NULL) */
        ChangeHistory.episodeId = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM Episode
            WHERE
              /* %JoinFKPK(:%New,Episode," = "," AND") */
              :new.episodeId = Episode.id
        ) 
        /* %JoinPKPK(ChangeHistory,:%New," = "," AND") */
         and ChangeHistory.id = :new.id;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_ChangeHistory AFTER UPDATE ON ChangeHistory for each row
-- erwin Builtin Trigger
-- UPDATE trigger on ChangeHistory 
DECLARE NUMROWS INTEGER;
BEGIN
  /* erwin Builtin Trigger */
  /* Episode  ChangeHistory on child update no action */
  /* ERWIN_RELATION:CHECKSUM="0000f617", PARENT_OWNER="", PARENT_TABLE="Episode"
    CHILD_OWNER="", CHILD_TABLE="ChangeHistory"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_10", FK_COLUMNS="episodeId" */
  SELECT count(*) INTO NUMROWS
    FROM Episode
    WHERE
      /* %JoinFKPK(:%New,Episode," = "," AND") */
      :new.episodeId = Episode.id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.episodeId IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update ChangeHistory because Episode does not exist.'
    );
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER  tD_Member AFTER DELETE ON Member for each row
-- erwin Builtin Trigger
-- DELETE trigger on Member 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* Member  MemberLitStorage on parent delete set null */
    /* ERWIN_RELATION:CHECKSUM="0000d13e", PARENT_OWNER="", PARENT_TABLE="Member"
    CHILD_OWNER="", CHILD_TABLE="MemberLitStorage"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_11", FK_COLUMNS="memberId" */
    UPDATE MemberLitStorage
      SET
        /* %SetFK(MemberLitStorage,NULL) */
        MemberLitStorage.memberId = NULL
      WHERE
        /* %JoinFKPK(MemberLitStorage,:%Old," = "," AND") */
        MemberLitStorage.memberId = :old.id;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_Member AFTER UPDATE ON Member for each row
-- erwin Builtin Trigger
-- UPDATE trigger on Member 
DECLARE NUMROWS INTEGER;
BEGIN
  /* Member  MemberLitStorage on parent update set null */
  /* ERWIN_RELATION:CHECKSUM="0000e78d", PARENT_OWNER="", PARENT_TABLE="Member"
    CHILD_OWNER="", CHILD_TABLE="MemberLitStorage"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_11", FK_COLUMNS="memberId" */
  IF
    /* %JoinPKPK(:%Old,:%New," <> "," OR ") */
    :old.id <> :new.id
  THEN
    UPDATE MemberLitStorage
      SET
        /* %SetFK(MemberLitStorage,NULL) */
        MemberLitStorage.memberId = NULL
      WHERE
        /* %JoinFKPK(MemberLitStorage,:%Old," = ",",") */
        MemberLitStorage.memberId = :old.id;
  END IF;


-- erwin Builtin Trigger
END;
/


CREATE  TRIGGER tI_MemberLitStorage BEFORE INSERT ON MemberLitStorage for each row
-- erwin Builtin Trigger
-- INSERT trigger on MemberLitStorage 
DECLARE NUMROWS INTEGER;
BEGIN
    /* erwin Builtin Trigger */
    /* Litstorage  MemberLitStorage on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="00020235", PARENT_OWNER="", PARENT_TABLE="Litstorage"
    CHILD_OWNER="", CHILD_TABLE="MemberLitStorage"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_12", FK_COLUMNS="LitstorageID" */
    UPDATE MemberLitStorage
      SET
        /* %SetFK(MemberLitStorage,NULL) */
        MemberLitStorage.LitstorageID = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM Litstorage
            WHERE
              /* %JoinFKPK(:%New,Litstorage," = "," AND") */
              :new.LitstorageID = Litstorage.id
        ) 
        /* %JoinPKPK(MemberLitStorage,:%New," = "," AND") */
        ;

    /* erwin Builtin Trigger */
    /* Member  MemberLitStorage on child insert set null */
    /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Member"
    CHILD_OWNER="", CHILD_TABLE="MemberLitStorage"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_11", FK_COLUMNS="memberId" */
    UPDATE MemberLitStorage
      SET
        /* %SetFK(MemberLitStorage,NULL) */
        MemberLitStorage.memberId = NULL
      WHERE
        NOT EXISTS (
          SELECT * FROM Member
            WHERE
              /* %JoinFKPK(:%New,Member," = "," AND") */
              :new.memberId = Member.id
        ) 
        /* %JoinPKPK(MemberLitStorage,:%New," = "," AND") */
        ;


-- erwin Builtin Trigger
END;
/

CREATE  TRIGGER tU_MemberLitStorage AFTER UPDATE ON MemberLitStorage for each row
-- erwin Builtin Trigger
-- UPDATE trigger on MemberLitStorage 
DECLARE NUMROWS INTEGER;
BEGIN
  /* erwin Builtin Trigger */
  /* Litstorage  MemberLitStorage on child update no action */
  /* ERWIN_RELATION:CHECKSUM="0002078b", PARENT_OWNER="", PARENT_TABLE="Litstorage"
    CHILD_OWNER="", CHILD_TABLE="MemberLitStorage"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_12", FK_COLUMNS="LitstorageID" */
  SELECT count(*) INTO NUMROWS
    FROM Litstorage
    WHERE
      /* %JoinFKPK(:%New,Litstorage," = "," AND") */
      :new.LitstorageID = Litstorage.id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.LitstorageID IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update MemberLitStorage because Litstorage does not exist.'
    );
  END IF;

  /* erwin Builtin Trigger */
  /* Member  MemberLitStorage on child update no action */
  /* ERWIN_RELATION:CHECKSUM="00000000", PARENT_OWNER="", PARENT_TABLE="Member"
    CHILD_OWNER="", CHILD_TABLE="MemberLitStorage"
    P2C_VERB_PHRASE="", C2P_VERB_PHRASE="", 
    FK_CONSTRAINT="R_11", FK_COLUMNS="memberId" */
  SELECT count(*) INTO NUMROWS
    FROM Member
    WHERE
      /* %JoinFKPK(:%New,Member," = "," AND") */
      :new.memberId = Member.id;
  IF (
    /* %NotnullFK(:%New," IS NOT NULL AND") */
    :new.memberId IS NOT NULL AND
    NUMROWS = 0
  )
  THEN
    raise_application_error(
      -20007,
      'Cannot update MemberLitStorage because Member does not exist.'
    );
  END IF;


-- erwin Builtin Trigger
END;
/

alter table episode modify(content varchar2(2000));
alter table changehistory modify(content varchar2(2000));

create sequence BOARD_SEQ START WITH 1 INCREMENT BY 1;
create sequence CHANGE_HISTORY_SEQ START WITH 1 INCREMENT BY 1;
create sequence DISCUSSION_CONTENT_SEQ START WITH 1 INCREMENT BY 1;
create sequence DISCUSSION_PLACE_SEQ START WITH 1 INCREMENT BY 1;
create sequence EPISODE_SEQ START WITH 1 INCREMENT BY 1;
create sequence LITERATURE_SEQ START WITH 1 INCREMENT BY 1;
create sequence POST_SEQ START WITH 1 INCREMENT BY 1;

alter table inviterequest add(litstorageId varchar2(50));
