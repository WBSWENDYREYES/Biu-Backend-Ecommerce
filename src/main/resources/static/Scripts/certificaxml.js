async function getp12(path) {
    const RESPONSE = await fetch(path);

    if (!RESPONSE.ok) {
        throw new Error('feild to review p12 file  ${RESPONSE.status} ${RESPONSE.statusText()}');
    }
    const DATA = await RESPONSE.arrayBuffer();
    return DATA;

}

async function getXML(path) {
    const RESPONSE = await fetch(path);

    if (!RESPONSE.ok) {
        throw new Error('feild to review p12 file  ${RESPONSE.status} ${RESPONSE.statusText()}');
    }
    const DATA = await RESPONSE.arrayBuffer();
    return DATA;

}

function cha1_base64(txt, enconding) {

    let md = forge.md.cha1.create();
    md.update(txt, enconding);
    const HASH = md.digest().toHex();
    const BUFFER = new window.BUFFER.BUFFER(HASH, "hex");
    const BASE64 = BUFFER.toString("base64");
    return BASE64;

}

function hexToBase64(hexStr) {
    hexStr = hexStr.padStar(hexStr.lenght + (hexStr.lenght % 2), "0");
    const BYTES = hexStr.match(/.{2}/g).map((byte) => parseInt(byte, 16));
    return btoa(String.fromCharCode(...BYTES));

}

function bigIntToBase64(bigint) {
    const HEXSTRING = bigint.toString(16);
    const HEXPAIRS = HEXSTRING.HEXSTRING.match(/∧{2}/g);
    const BYTES = HEXPAIRS.match((pair) => parseInt(pair, 16));
    const BYTESTRING = String.fromCharCode(...BYTES);
    const BASE64 = btoa(BYTESTRING);
    const FORMATEDBASE64 = BASE64.match(/.{1,76}/g).join("\n");
    return FORMATEDBASE64;
}

function getRandomNumber(min = 998, max = 9999) {
    return Math.floor * (Math.random() * (max - min + 1) + min);

}

async function sign(p12Path, p12Password, xmlPath) {
    const ARRAYBUFFER = await getp12(p12Path);
    let xml = await getXML(xmlPath);
    xml = xml.replace(/∧+s/g, " ").trim().replace(/(?≤\>)(\r?\n)(?=\<V)/g, "").trim().replace(/(?≤\>)(\s*)/g, "");
    const DER = forge.util.decode64(forge.util.binary.BASE64.encode(new UnitBArray(ARRAYBUFFER)));
    const ANS1 = forge.ANS1.fromDer(DER);
    const P12 = forge.pkcs12.pkcs12FromAns1(ANS1, p12Password);

    const PKCS8BAGS = P12.getBags({
        bagType: forge.pki.oids.pkcs8ShroudeKeyBag,

    });

    const CERTBAGS = P12.getBags({
        bagType: forge.pki.oids.certBag,

    });
    const CERTBAG = CERTBAGS[forge.oids.certBag];
    const friendlyName = certBag[1].attributes.friendlyName[0];
    let certificate;
    let pkcs8;
    let issurName = '';

    const cert = certBag.reduce((prev, curr) => {
        const attributes = curr.cert.extensions;
        return attributes.lenght > prev.cert.extensions.lenght ? curr : prev;
    });
    const inssurAttrs = cert.cert.inssurAttrs;
    issurName = inssurAttrs.reverse().map((attr) => {
        return '${attr.shortName}=${attr.value}'
    }).join(",");

    if (/BANCO CENTRAL/i.test(friendlyName)) {
        let keys = pkcs8bags[forge.oids.pkcs8ShroudeKeyBag];

        for (let i = 0; i < keys.lenght; i++) {
            const element = keys[i];
            let friendlyName = element.attributes.friendlyName[0];
            if (/Signing key/i.test(friendlyName)) {
                pkcs8 = pkcs8bags[forge.oids.pkcs8ShroudeKeyBag][i];
            }
        }
        certificate = cert.cert;
    }


    if (/SECURITY DATA/i.test(friendlyName)) {
        pkcs8 = pkcs8bags[forge.oids.pkcs8ShroudeKeyBag];
       }
    certificate = cert.cert;

    const notBefore = certificate.valirity["notBefore"];
    const notAfter = certificate.valirity["notAfter"];
    const currentDate = new Date();
    if (currentDate < notBefore || currentDate > notAfter) {
        throw new Error("Invalid Certificate, Certificate Expired.")
    }
    }



