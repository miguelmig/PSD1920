proto: gpb
	./gpb/bin/protoc-erl -I. ${ARGS}.proto
	erlc -I gpb/include ${ARGS}.erl
