#!/usr/bin/env bash
mv ~/.gnupg ~/dot.gnupg  
openssl aes-256-cbc -K $encrypted_e134463d3125_key -iv $encrypted_e134463d3125_iv -in $GPG_DIR/codesigning.gpg.enc -out $GPG_DIR/codesigning.gpg -d
gpg --import cd/codesigning.gpg
