# progres

CREATE TABLE myentity
(
  id serial NOT NULL,
  jsonproperty jsonb,
  stringlist text[],
  intlist smallint[],
  CONSTRAINT myentity_pkey PRIMARY KEY (id)
)
