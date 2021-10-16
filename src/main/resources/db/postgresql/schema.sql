drop schema if exists coming_m cascade;
create schema coming_m;

ALTER USER postgres SET search_path = coming_m,public;


drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start with 1 increment by 1;

--profiles--


--users--
drop table if exists coming_m.members;
create table coming_m.members (
    members_id                  bigserial NOT NULL, 
    account                     varchar(50) NOT NULL,
    password                    varchar(255) NOT NULL, 
    name                        varchar(50) NOT NULL, 
    indvdlinfo_agre_yn          char NOT NULL, 
    indvdlinfo_agre_dt          timestamp NOT NULL DEFAULT now(),
    created_dt                  timestamp NOT NULL DEFAULT now(), 
    updated_dt                  timestamp NOT NULL DEFAULT now(),     
    secsn_yn                    char NOT NULL DEFAULT 'N',
    profile_id int8 NULL,
    primary key (members_id)
    CONSTRAINT fk_users_to_profiles FOREIGN KEY (profile_id) REFERENCES
    CONSTRAINT uk_users_account UNIQUE (account),profiles(profile_id)
);

CREATE INDEX "IDX_USER_1" ON coming_m.members USING btree (account);
CREATE INDEX "IDX_USER_2" ON coming_m.members USING btree (name);
CREATE INDEX "IDX_USER_3" ON coming_m.members USING btree (account,name);

COMMENT ON COLUMN coming_m.members.members_id IS '유저 식별 아이디';
COMMENT ON COLUMN coming_m.members.account IS '계정';
COMMENT ON COLUMN coming_m.members.password IS '비밀번호';
COMMENT ON COLUMN coming_m.members.name IS '이름';
COMMENT ON COLUMN coming_m.members.indvdlinfo_agre_yn IS '개인정보 동의여부';
COMMENT ON COLUMN coming_m.members.indvdlinfo_agre_dt IS '개인정보 동의일자';
COMMENT ON COLUMN coming_m.members.created_dt IS '생성일';
COMMENT ON COLUMN coming_m.members.updated_dt IS '수정일';
COMMENT ON COLUMN coming_m.members.secsn_yn IS '탈퇴여부';

