const httpsOptions = {
    region: "asia-northeast1" // Tokyo
    // region: "asia-northeast2" // Osaka
};

const { onRequest } = require("firebase-functions/v2/https");
const express = require('express');
const app = express();

app.get('/:serve-key', (req, res) => res.send({
    publishableKey: process.env.PUBLISHABLE_KEY
}));

app.post("/:create-session", async (req, res) => {
    const {
        amount,
        currency,
        language
    } = req.body;
    const secretKey = process.env.SECRET_KEY;

    try {
        const sessionId = await createSession({
            amount,
            currency,
            language,
            secretKey,
        });
        res.send({
            sessionId
        });
        console.log(sessionId)
    } catch (error) {
        res.status(500);
        res.send({
            error: error.message
        })
    }
});

async function createSession({
    amount,
    currency,
    language,
    secretKey
}) {
    if (!secretKey) {
        throw new Error("Secret Key Required");
    }

    const url = "https://komoju.com/api/v1/sessions";
    const options = {
        method: "POST",
        headers: {
            accept: "application/json",
            "content-type": "application/json",
            Authorization: `Basic ${Buffer.from(secretKey + ":").toString("base64")}`,
            "X-KOMOJU-API-VERSION": "2024-07-15",
        },
        body: JSON.stringify({
            default_locale: language ?? "ja",
            amount,
            currency,
            return_url: process.env.RETURN_URL,
        }),
    };
    const response = await fetch(url, options);
    if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
    }
    const {
        id
    } = await response.json();
    return id;
}
exports.komoju = onRequest(httpsOptions, app);
