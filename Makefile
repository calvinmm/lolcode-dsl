BT=sbt

run:
	@echo not implemented

test:
	$(BT) "run-main lolcode.loltest"

console:
	$(BT) console

eclipse:
	$(BT) "eclipse with-source=true"